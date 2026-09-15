package strategies.botPlayingStrategies;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;
import models.enums.CellState;

public class HardBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move getMove(Board board, Player player) {
        Player opponent = findOpponent(board, player);

        // when the board is empty bot player chance, at the center or top-left
        if (opponent == null) {
            return new Move(player, board.getCell(board.getSize() / 2, board.getSize() / 2));
        }

        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;

        // for all empty cells we calculate their Minimax score
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Cell cell = board.getCell(i, j);
                if (cell.getCellState() == CellState.EMPTY) {


                    cell.setCellState(CellState.FILLED);
                    cell.setPlayer(player);

                    // call Minimax functions
                    int score = minimax(board, 0, false, player, opponent);

                    // undo Simulation
                    cell.clearCell();

                    // track the best move
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Move(player, cell);
                    }
                }
            }
        }

        // default strategy
        if (bestMove == null) {
            return new EasyBotPlayingStrategy().getMove(board, player);
        }

        return bestMove;
    }
    private int minimax(Board board, int depth, boolean isMaximizing, Player bot, Player opponent) {
        // to evaluate current board state
        int score = evaluate(board, bot, opponent);

        // Terminal states (Win, Loss, or max depth reached for performance)
        if (score == 10) return score - depth; // Prefer faster wins
        if (score == -10) return score + depth; // Prefer slower losses
        if (isBoardFull(board) || depth >= 5) return 0; // Draw or depth limit

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    Cell cell = board.getCell(i, j);
                    if (cell.getCellState() == CellState.EMPTY) {
                        cell.setCellState(CellState.FILLED);
                        cell.setPlayer(bot);
                        best = Math.max(best, minimax(board, depth + 1, false, bot, opponent));
                        cell.clearCell();
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    Cell cell = board.getCell(i, j);
                    if (cell.getCellState() == CellState.EMPTY) {
                        cell.setCellState(CellState.FILLED);
                        cell.setPlayer(opponent);
                        best = Math.min(best, minimax(board, depth + 1, true, bot, opponent));
                        cell.clearCell();
                    }
                }
            }
            return best;
        }
    }

    private int evaluate(Board board, Player bot, Player opponent) {
        // Reusing the win-checking logic to evaluate the board for Minimax
        if (checkWinForPlayer(board, bot)) return 10;
        if (checkWinForPlayer(board, opponent)) return -10;
        return 0;
    }
    private boolean checkWinForPlayer(Board board, Player player) {
        int size = board.getSize();
        char symbol = player.getSymbol().getCharacter();

        // we are checking for rows and columns
        for (int i = 0; i < size; i++) {
            boolean rowWin = true;
            boolean colWin = true;
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j).getCellState() == CellState.EMPTY || board.getCell(i, j).getPlayer().getSymbol().getCharacter() != symbol) rowWin = false;
                if (board.getCell(j, i).getCellState() == CellState.EMPTY || board.getCell(j, i).getPlayer().getSymbol().getCharacter() != symbol) colWin = false;
            }
            if (rowWin || colWin) return true;
        }

        // we are checking for diagonals
        boolean diag1Win = true, diag2Win = true;
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, i).getCellState() == CellState.EMPTY || board.getCell(i, i).getPlayer().getSymbol().getCharacter() != symbol) diag1Win = false;
            if (board.getCell(i, size - 1 - i).getCellState() == CellState.EMPTY || board.getCell(i, size - 1 - i).getPlayer().getSymbol().getCharacter() != symbol) diag2Win = false;
        }
        return diag1Win || diag2Win;
    }

    private boolean isBoardFull(Board board) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j).getCellState() == CellState.EMPTY) return false;
            }
        }
        return true;
    }

    private Player findOpponent(Board board, Player bot) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Cell cell = board.getCell(i, j);
                if (cell.getCellState() == CellState.FILLED && cell.getPlayer() != bot) {
                    return cell.getPlayer();
                }
            }
        }
        return null;
    }
}
