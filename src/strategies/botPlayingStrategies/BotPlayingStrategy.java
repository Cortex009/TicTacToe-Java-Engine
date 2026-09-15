package strategies.botPlayingStrategies;

import models.Board;
import models.Move;
import models.Player;

public interface BotPlayingStrategy {
    Move getMove(Board board, Player player);
}
