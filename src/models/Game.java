package models;

import exceptions.InvalidGameException;
import models.enums.GameState;
import models.enums.PlayerType;
import strategies.winningStrategies.WinningStrategy;

import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;
    private Game(){}

    public Game(Builder builder){
        this.board = new Board(builder.boardSize);
        this.players = builder.players;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = builder.winningStrategies;
        Collections.shuffle(players);
    }
    public static Builder getGameBuilder(){
        return new Builder();
    }
    public static class Builder{
        private int boardSize;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        /*
        public Builder(int boardSize, List<Player>players){
            this.boardSize = boardSize;
            this.players = players;
        }
        WE DON'T NEED THESE... INSTEAD BUILDER WILL CREATE THESE!!
         */
        public Builder setBoardSize(int boardSize){
            this.boardSize = boardSize;
            return this;
        }
        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }
        public Game Build() throws InvalidGameException {
            validate();
            return new Game(this);
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validate() throws InvalidGameException {

//            Validate player-count : should be board-size - 1
            if(players.size()!=boardSize-1){
                throw new InvalidGameException("Players' count must be "+(boardSize-1)+"for a "+ boardSize + "x"+ boardSize+" board !!");
            }

//            Validate Unique Symbols
            Set<Character> symbols = new HashSet<>();
            for (Player player : players){
                char symbol = player.getSymbol().getCharacter();
                if(symbols.contains(symbol)){
                    throw new InvalidGameException("Duplicate symbol found !!"+ symbol);
                }
                symbols.add(symbol);
            }

//            Validate at most 1 bot is allowed
            long botCount = players.stream().filter(p -> p.getPlayerType().equals(PlayerType.BOT)).count();
            if (botCount>1){
                throw new InvalidGameException("At most 1 bot is allowed per game.");
            }
        }
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }
}
