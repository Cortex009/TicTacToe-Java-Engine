package models;

public class Move {
    private Player player;
    private Cell cell;
    public Move(Player player, Cell cell){
        this.cell = cell;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Cell getCell() {
        return cell;
    }
}
