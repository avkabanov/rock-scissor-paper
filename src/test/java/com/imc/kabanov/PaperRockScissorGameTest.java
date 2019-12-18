package com.imc.kabanov;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.imc.kabanov.data.GameParameters;
import com.imc.kabanov.data.GameResult;
import com.imc.kabanov.game.TwoPlayersGame;
import com.imc.kabanov.game.player.Player;
import com.imc.kabanov.io.GameEventNotificator;

/**
 * @author Kabanov Alexey
 */
@RunWith(MockitoJUnitRunner.class)
public class PaperRockScissorGameTest {
    @Mock private Player firstPlayer;
    @Mock private Player secondPlayer;
    @Mock private TwoPlayersGame game;
    @Mock private GameEventNotificator eventNotificator;
    @Mock private GameResult gameResult;

    @Before
    public void setup() {
        Mockito.when(game.play(Mockito.any(Player.class), Mockito.any(Player.class))).thenReturn(gameResult);
    }

    @Test
    public void shouldGameRunNTimes() {
        GameParameters parameters = new GameParameters(3);
        PaperRockScissorGame gameSeries = new PaperRockScissorGame(firstPlayer, secondPlayer, game, parameters,
                eventNotificator);

        gameSeries.start();

        // check that game has played 3 times, according to the number in game parameters
        Mockito.verify(game, Mockito.times(3))
                .play(Mockito.any(Player.class), Mockito.any(Player.class));
    }
}