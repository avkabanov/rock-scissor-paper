package com.imc.kabanov.game.player;

import javax.inject.Inject;
import javax.inject.Named;

import com.imc.kabanov.data.Symbol;
import com.imc.kabanov.game.symbol.SymbolGetter;

/**
 * @author Kabanov Alexey
 */
public class Human implements Player {
    
    private SymbolGetter symbolGetter;

    @Inject
    public Human(@Named("userInput") SymbolGetter symbolGetter) {
        this.symbolGetter = symbolGetter;
    }

    @Override
    public Symbol makeTurn() {
        return symbolGetter.getSymbol();
    }

    @Override
    public String getName() {
        return "Human";
    }
}
