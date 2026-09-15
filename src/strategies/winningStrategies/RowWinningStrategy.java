package strategies.winningStrategies;

import models.Board;
import models.Cell;
import models.Move;
import models.enums.CellState;

import java.util.List;

public class RowWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Move lastMove) {
        int row = lastMove.getCell().getRow();
        List<Cell> rowCells = board.getCells().get(row);
        char symbol = lastMove.getPlayer().getSymbol().getCharacter();
        for(Cell cell : rowCells){
            if(cell.getCellState()== CellState.EMPTY || cell.getPlayer().getSymbol().getCharacter()!= symbol){
                return false;
            }
        }
        return true;
    }
}
