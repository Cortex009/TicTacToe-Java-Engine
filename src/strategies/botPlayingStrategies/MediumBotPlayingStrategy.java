package strategies.botPlayingStrategies;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;
import models.enums.CellState;

public class MediumBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move getMove(Board board, Player player) {

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Cell cell = board.getCell(i, j);
                if (cell.getCellState() == CellState.EMPTY) {
                    if (isWinningMove(board, cell, player)) {
                        return new Move(player, cell);
                    }
                }
            }
        }
        return new EasyBotPlayingStrategy().getMove(board,player);
    }

    private boolean isWinningMove(Board board, Cell targetCell, Player player) {
        int row = targetCell.getRow();
        int col = targetCell.getColumn();
        int size = board.getSize();
        char symbol = player.getSymbol().getCharacter();

        // Check Row
        boolean win = true;
        for (int j = 0; j < size; j++) {
            Cell c = board.getCell(row, j);
            if (c != targetCell && (c.getCellState() == CellState.EMPTY || c.getPlayer().getSymbol().getCharacter() != symbol)) {
                win = false;
                break;
            }
        }
        if (win) return true;

        win = true;
        for (int i = 0; i < size; i++) {
            Cell c = board.getCell(i, col);
            if (c != targetCell && (c.getCellState() == CellState.EMPTY || c.getPlayer().getSymbol().getCharacter() != symbol)) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Check Left Diagonal (only if cell is on it)
        if (row == col) {
            win = true;
            for (int i = 0; i < size; i++) {
                Cell c = board.getCell(i, i);
                if (c != targetCell && (c.getCellState() == CellState.EMPTY || c.getPlayer().getSymbol().getCharacter() != symbol)) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Check Right Diagonal (only if cell is on it)
        if (row + col == size - 1) {
            win = true;
            for (int i = 0; i < size; i++) {
                Cell c = board.getCell(i, size - 1 - i);
                if (c != targetCell && (c.getCellState() == CellState.EMPTY || c.getPlayer().getSymbol().getCharacter() != symbol)) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        return false;
    }
}
