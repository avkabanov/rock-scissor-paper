package com.imc.kabanov.game.symbol;

import java.util.Random;

import javax.annotation.Nonnull;

import com.imc.kabanov.data.Symbol;

/**
 * @author Kabanov Alexey
 */
public class RandomSymbolGetter implements SymbolGetter {
    
    @Nonnull
    @Override
    public Symbol getSymbol() {
        int symbolsCount = Symbol.values().length;

        Random r = new Random();
        int randomIndex = r.nextInt(symbolsCount);
        return Symbol.values()[randomIndex];
    }
}
