package controllers;

import models.Cell;
import models.Game;
import models.Player;
import models.enums.GameState;
import services.GameService;

public class GameController {
    private final GameService gameService;
    public GameController(GameService gameService){
        this.gameService = gameService;
    }
    public void makeMove(Game game, Player player, Cell cell){
        gameService.makeMove(game,player,cell);
    }
    public void undoMove(Game game){
        gameService.undoMove(game);
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
    public GameState getGameState(Game game){
        return game.getGameState();
    }
    public void printBoard(Game game){
        game.getBoard().printBoard();
    }

}
