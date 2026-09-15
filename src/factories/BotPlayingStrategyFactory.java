package factories;

import models.enums.BotDifficultyLevel;
import strategies.botPlayingStrategies.BotPlayingStrategy;
import strategies.botPlayingStrategies.EasyBotPlayingStrategy;
import strategies.botPlayingStrategies.HardBotPlayingStrategy;
import strategies.botPlayingStrategies.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy createStrategy(BotDifficultyLevel botDifficultyLevel){
        return switch (botDifficultyLevel) {
            case EASY -> new EasyBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
            case HARD -> new HardBotPlayingStrategy();
        };
    }
}
