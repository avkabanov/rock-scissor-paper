package com.imc.kabanov.strategy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.imc.kabanov.data.PlayerSymbolHolder;
import com.imc.kabanov.data.Symbol;
import com.imc.kabanov.game.player.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * @author Kabanov Alexey
 */
@RunWith(MockitoJUnitRunner.class)
public class TwoPlayersStrategyTest {
    
    @Mock private Player first; 
    @Mock private Player second; 
    private TwoPlayersStrategy sut = new TwoPlayersStrategy();
    
    @Test
    public void shouldReturnFirstPlayerWhenFirstIsTheWinner() {
        PlayerSymbolHolder firstHolder = new PlayerSymbolHolder(first, Symbol.PAPER);
        PlayerSymbolHolder secondHolder = new PlayerSymbolHolder(second, Symbol.ROCK);

        PlayerSymbolHolder result = sut.findWinner(firstHolder, secondHolder);

        Assert.assertNotNull(result);
        assertSame(first, result.getPlayer());
        assertEquals(Symbol.PAPER, result.getSymbol());
    }

    @Test
    public void shouldReturnSecondPlayerWhenSecondIsTheWinner() {
        PlayerSymbolHolder firstHolder = new PlayerSymbolHolder(first, Symbol.PAPER);
        PlayerSymbolHolder secondHolder = new PlayerSymbolHolder(second, Symbol.SCISSORS);

        PlayerSymbolHolder result = sut.findWinner(firstHolder, secondHolder);

        Assert.assertNotNull(result);
        assertSame(second, result.getPlayer());
        assertEquals(Symbol.SCISSORS, result.getSymbol());
    }

    @Test
    public void shouldReturnNullWhenNotPossibleToComputeTheWinner() {
        PlayerSymbolHolder firstHolder = new PlayerSymbolHolder(first, Symbol.PAPER);
        PlayerSymbolHolder secondHolder = new PlayerSymbolHolder(second, Symbol.PAPER);

        PlayerSymbolHolder result = sut.findWinner(firstHolder, secondHolder);

        assertNull(result);
    }
}