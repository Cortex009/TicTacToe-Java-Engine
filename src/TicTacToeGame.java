import controllers.GameController;
import exceptions.InvalidGameException;
import models.*;
import models.enums.BotDifficultyLevel;
import models.enums.CellState;
import models.enums.GameState;
import models.enums.PlayerType;
import services.GameService;
import strategies.winningStrategies.ColumnWinningStrategy;
import strategies.winningStrategies.DiagonalWinningStrategy;
import strategies.winningStrategies.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TicTacToeGame {
    public static void main(String[] args) throws InvalidGameException {
        int boardSize = 3;
        Player player1 = new HumanPlayer("Player 1:", new Symbol('X'));
        Player player2 = new HumanPlayer("Player 2:", new Symbol('O'));
//        Player player3 = new BotPlayer("robot : ", new Symbol('@'), BotDifficultyLevel.EASY);

//        ----------Bot Player---------
        /*
        List<Player> currentPlayers = new ArrayList<>();
        currentPlayers.add(player1);
        currentPlayers.add(player3);

         */

//        /*
//        ------------HUMAN PLAYERS--------
        List<Player> currentPlayers = new ArrayList<>();
        currentPlayers.add(player1);
        currentPlayers.add(player2);

//         */

        Game game = Game.getGameBuilder()
                .setBoardSize(boardSize)
                .setPlayers(currentPlayers)
                .setWinningStrategies(List.of(new RowWinningStrategy(), new ColumnWinningStrategy(), new DiagonalWinningStrategy()))
                .Build();

        System.out.println("==== TIC TAC TOE GAME =====");
        GameController gameController = new GameController(new GameService());
        Scanner scanner = new Scanner(System.in);

        while (gameController.getGameState(game) == GameState.IN_PROGRESS) {
            gameController.printBoard(game);
            Player currentPlayer = game.getPlayers().get(game.getNextPlayerIndex());
            System.out.println(currentPlayer.getName() + "'s Turn [" + currentPlayer.getSymbol().getCharacter() + "] : ");

            Move move;

            if(currentPlayer.getPlayerType()== PlayerType.BOT){
                BotPlayer currentBot = (BotPlayer) currentPlayer;
                move = currentBot.getBotPlayingStrategy().getMove(game.getBoard(),currentBot);
                System.out.println("Bot plays: ("+move.getCell().getRow()+","+move.getCell().getColumn()+")");
            }
            else {
                System.out.println(" Enter row and column (e.g. 0,1) : ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("undo")) {
                    gameController.undoMove(game);
                    continue; // Skip the loop and re-start the next iteration
                }

                String[] parts = input.split(",");
                if (parts.length < 2) {
                    System.out.println("Invalid input!, please enter in the format row,column");
                    continue;
                }
                int row = Integer.parseInt(parts[0]);
                int column = Integer.parseInt(parts[1]);
                if (row < 0 || row >= boardSize || column < 0 || column >= boardSize) {
                    System.out.println("Invalid Cell. Please try again in a valid cell");
                    continue;
                }
                Cell cell = game.getBoard().getCell(row, column);
                if (cell.getCellState() != CellState.EMPTY) {
                    System.out.println("Cell is already occupied!!");
                    System.out.println("Tyr again.\n");
                    continue;
                }
                move = new Move(currentPlayer, cell);
            }
            gameController.makeMove(game, move.getPlayer(), move.getCell());

        }
        gameController.printBoard(game);
        if (game.getGameState() == GameState.ENDED) {
            System.out.println("Hurrah!!  " + game.getWinner().getName() + " wins!!");
        } else {
            System.out.println(" It's a Draw!!");
            game.setGameState(GameState.DRAW);
        }
        scanner.close();
    }
}