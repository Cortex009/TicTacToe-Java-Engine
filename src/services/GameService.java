package services;

import models.Cell;
import models.Game;
import models.Move;
import models.Player;
import models.enums.CellState;
import models.enums.GameState;
import strategies.winningStrategies.WinningStrategy;

public class GameService {
    public void makeMove(Game game, Player player, Cell cell){
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(player);
        Move move = new Move(player,cell);
        game.getMoves().add(move);
        if(checkWinner(game,move)){
            game.setWinner(player);
            game.setGameState(GameState.ENDED);
        }
        if (checkDraw(game)){
            game.setGameState(GameState.DRAW);
        }
        int nextPlayer = (game.getNextPlayerIndex()+1) % game.getPlayers().size();
        game.setNextPlayerIndex(nextPlayer);
    }
    private boolean checkWinner(Game game,Move lastMove){
        for(WinningStrategy winningStrategy : game.getWinningStrategies()){
            if(winningStrategy.checkWinner(game.getBoard(),lastMove)){
                return true;
            }
        }

        return false;
    }
    private boolean checkDraw(Game game){
        int size = game.getBoard().getSize();
        int totalCells = size*size;
        return game.getMoves().size() == totalCells;
    }
    public void undoMove(Game game){
        if (game.getMoves().isEmpty()) {
            System.out.println("No moves to undo!");
            return;
        }

        // removing the last move from the list
        int lastMoveIndex = game.getMoves().size() - 1;
        Move lastMove = game.getMoves().remove(lastMoveIndex);

        //Clear the cell on the board
        lastMove.getCell().clearCell();

        // Revert the turn to the previous player
        int previousPlayerIndex = game.getNextPlayerIndex() - 1;
        if (previousPlayerIndex < 0) {
            previousPlayerIndex = game.getPlayers().size() - 1;
        }
        game.setNextPlayerIndex(previousPlayerIndex);

        //Revert Game State if the game had finished
        if (game.getGameState() == GameState.ENDED || game.getGameState() == GameState.DRAW) {
            game.setGameState(GameState.IN_PROGRESS);
            game.setWinner(null);
        }
    }
}
