package models;

import factories.BotPlayingStrategyFactory;
import models.enums.BotDifficultyLevel;
import models.enums.PlayerType;
import strategies.botPlayingStrategies.BotPlayingStrategy;

public class BotPlayer extends Player{
    private BotPlayingStrategy botPlayingStrategy;
    private BotDifficultyLevel botDifficultyLevel;
    public BotPlayer(String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol,PlayerType.BOT);
        this.botPlayingStrategy = BotPlayingStrategyFactory.createStrategy(botDifficultyLevel);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }
}
