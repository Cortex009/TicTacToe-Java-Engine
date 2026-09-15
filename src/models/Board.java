package models;

import models.enums.CellState;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> cells;

    public List<List<Cell>> getCells() {
        return cells;
    }

    public Board(int size){
        this.size = size;
        this.cells = new ArrayList<>();
        for(int i=0;i<size;i++){
            List<Cell> row = new ArrayList<>();
            for(int j=0;j<size;j++){
                row.add(new Cell(i,j));
            }
            cells.add(row);
        }
    }

    public int getSize() {
        return size;
    }
    public Cell getCell(int row, int column){
        return cells.get(row).get(column);
    }
    public void printBoard(){
        for(List<Cell> row: cells){
            for(Cell cell: row){
                if( cell.getCellState() == CellState.EMPTY){
                    System.out.print(" . ");
                }
                else{
                    System.out.print(" "+cell.getPlayer().getSymbol().getCharacter()+" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
