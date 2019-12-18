package com.imc.kabanov.game.player;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.imc.kabanov.data.Symbol;
import com.imc.kabanov.game.symbol.SymbolGetter;

/**
 * @author Kabanov Alexey
 */
public class Computer implements Player {
    
    private SymbolGetter symbolGetter;

    @Inject
    public Computer(@Named("random") SymbolGetter symbolGetter) {
        this.symbolGetter = symbolGetter;
    }

    @Override
    public Symbol makeTurn() {
        return symbolGetter.getSymbol();
    }

    @Override
    public String getName() {
        return "Computer";
    }
}
