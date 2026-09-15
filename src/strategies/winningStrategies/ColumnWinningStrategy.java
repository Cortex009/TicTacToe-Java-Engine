package strategies.winningStrategies;

import models.Board;
import models.Cell;
import models.Move;
import models.enums.CellState;

import java.util.List;

public class ColumnWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Move lastMove) {
        int column = lastMove.getCell().getColumn();
        char symbol = lastMove.getPlayer().getSymbol().getCharacter();
        for(int i=0;i<board.getSize();i++){
            var cell = board.getCell(i,column);
            if(cell.getCellState()== CellState.EMPTY || cell.getPlayer().getSymbol().getCharacter()!= symbol){
                return false;
            }
        }
        return true;
    }
}
