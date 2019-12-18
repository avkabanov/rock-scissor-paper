package com.imc.kabanov.game;

import com.imc.kabanov.data.GameResult;
import com.imc.kabanov.game.player.Player;

/**
 * @author Kabanov Alexey
 */
public interface TwoPlayersGame {

    GameResult play(Player firstPlayer, Player secondPlayer);

    /**
     * Method can be used to cleanup resources
     */
    void finishGame();
}
