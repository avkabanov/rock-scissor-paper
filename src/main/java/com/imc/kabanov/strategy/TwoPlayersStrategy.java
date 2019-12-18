package com.imc.kabanov.strategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.imc.kabanov.data.PlayerSymbolHolder;

/**
 * @author Kabanov Alexey
 */
public class TwoPlayersStrategy implements GameStrategy {

    @Override
    @Nullable
    public PlayerSymbolHolder findWinner(@Nonnull PlayerSymbolHolder first, @Nonnull PlayerSymbolHolder second) {
        if (first.getSymbol() == second.getSymbol()) {
            return null;
        }
        
        if (first.getSymbol().beats(second.getSymbol())) {
            return first;
        } else {
            return second;
        }
    }
}
