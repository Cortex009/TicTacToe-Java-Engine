package models;

import models.enums.CellState;

public class Cell {
    private int row;
    private int column;
    private CellState cellState;
    private Player player;

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        this.cellState = CellState.EMPTY;
        this.player = null;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public CellState getCellState() {
        return cellState;
    }

    public Player getPlayer() {
        return player;
    }
    public void clearCell(){
        this.cellState = CellState.EMPTY;
        this.player= null;
    }
}
