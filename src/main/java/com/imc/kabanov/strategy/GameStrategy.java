package com.imc.kabanov.strategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.imc.kabanov.data.PlayerSymbolHolder;

/**
 * @author Kabanov Alexey
 */
public interface GameStrategy {

   /**
    * Calculates the winner player according to their symbols
    *
    * @return null if unable to determine the winner in current symbols combination
    */
    @Nullable
    PlayerSymbolHolder findWinner(@Nonnull PlayerSymbolHolder first, @Nonnull PlayerSymbolHolder second);
}
