package com.imc.kabanov.io;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.imc.kabanov.data.PlayerSymbolHolder;
import com.imc.kabanov.data.Symbol;
import com.imc.kabanov.game.player.Player;

/**
 * @author Kabanov Alexey
 */
@RunWith(MockitoJUnitRunner.class)
public class OutputFormatterImplTest {

    @Mock private Player player;
    private Symbol symbol = Symbol.PAPER;
    private OutputFormatter formatter = new OutputFormatterImpl();

    @Before
    public void setup() {
        Mockito.when(player.getName()).thenReturn("new_player");
    }

    @Test
    public void shouldPlayerAndDisplayedSymbolBeFormattedByPattern() {
        Assert.assertEquals("Player new_player displayed Paper",
                formatter.formatDisplayedSymbol(new PlayerSymbolHolder(player, symbol)));

    }

    @Test
    public void formatWinner() {
        Assert.assertEquals("The winner is new_player with symbol Paper",
                formatter.formatWinner(new PlayerSymbolHolder(player, symbol)));
    }
}