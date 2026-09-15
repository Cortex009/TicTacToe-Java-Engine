package strategies.botPlayingStrategies;

import models.Board;
import models.Cell;
import models.Move;
import models.Player;
import models.enums.CellState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move getMove(Board board, Player player) {
        List<Cell> emptyCells = new ArrayList<>();
        for(var row: board.getCells()){ //iterating through ---
            for(var cell : row){ // --- a 2-D Matrix
                if(cell.getCellState() == CellState.EMPTY){ //check if a cell is empty
                    emptyCells.add(cell); //add the cell to the cells
                }
            }
        }
        Random random = new Random(); //random value generator
        Cell chosenCell = emptyCells.get(random.nextInt(emptyCells.size()));
        return new Move(player,chosenCell);
    }
}
