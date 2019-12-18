package com.imc.kabanov.game.symbol;

import javax.annotation.Nonnull;

import com.imc.kabanov.data.Symbol;

/**
 * @author Kabanov Alexey
 */
public interface SymbolGetter {
    
    @Nonnull
    Symbol getSymbol();
}
