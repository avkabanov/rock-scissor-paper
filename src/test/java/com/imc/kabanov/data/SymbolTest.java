package com.imc.kabanov.data;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Kabanov Alexey
 */
public class SymbolTest {
    
    public static final String INVALID_SYMBOL_ALIAS = "Z";

    @Test
    public void shouldPaperBeatRockOnly() {
        Assert.assertFalse(Symbol.PAPER.beats(Symbol.PAPER));
        Assert.assertTrue(Symbol.PAPER.beats(Symbol.ROCK));
        Assert.assertFalse(Symbol.PAPER.beats(Symbol.SCISSORS));
    }

    @Test
    public void shouldRockBeatScissorsOnly() {
        Assert.assertFalse(Symbol.ROCK.beats(Symbol.PAPER));
        Assert.assertFalse(Symbol.ROCK.beats(Symbol.ROCK));
        Assert.assertTrue(Symbol.ROCK.beats(Symbol.SCISSORS));
    }

    @Test
    public void shouldScissorsBeatPaperOnly() {
        Assert.assertTrue(Symbol.SCISSORS.beats(Symbol.PAPER));
        Assert.assertFalse(Symbol.SCISSORS.beats(Symbol.ROCK));
        Assert.assertFalse(Symbol.SCISSORS.beats(Symbol.SCISSORS));

    }

    @Test
    public void shouldReturnValidSymbolForAlias() {
        Assert.assertEquals(Symbol.ROCK, Symbol.valueOfAlias("R"));
        Assert.assertEquals(Symbol.SCISSORS, Symbol.valueOfAlias("S"));
        Assert.assertEquals(Symbol.PAPER, Symbol.valueOfAlias("P"));
    }

    @Test
    public void shouldReturnNullForInvalidSymbol() {
        Assert.assertNull(Symbol.valueOfAlias(INVALID_SYMBOL_ALIAS));
    }
}