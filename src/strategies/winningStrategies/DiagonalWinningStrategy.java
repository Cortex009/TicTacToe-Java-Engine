package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.enums.CellState;

public class DiagonalWinningStrategy implements WinningStrategy{
    @Override
    public boolean checkWinner(Board board, Move lastMove) {
        int row = lastMove.getCell().getRow();
        int column = lastMove.getCell().getColumn();
        char symbol = lastMove.getPlayer().getSymbol().getCharacter();
        int size = board.getSize();
//        Left Diagonal
        if(row==column){ // top left cell
            boolean won = true;
            for(int i=0;i<size;i++){
                var cell = board.getCell(i,i); // cells from left to right
                if(cell.getCellState()== CellState.EMPTY||cell.getPlayer().getSymbol().getCharacter()!=symbol){
                    won = false;
                    break;
                }
            }
            return won;
        }
//        Right Diagonal
        if(row+column==size-1){ // top right cell
            for(int i=0;i<size;i++){
                var cell = board.getCell(i,size-1-i); //cells from right to left
                if(cell.getCellState()==CellState.EMPTY || cell.getPlayer().getSymbol().getCharacter()!= symbol){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
