package com.imc.kabanov.io;

import javax.annotation.Nonnull;

import com.imc.kabanov.data.PlayerSymbolHolder;

/**
 * @author Kabanov Alexey
 */
public interface OutputFormatter {

    @Nonnull
    String formatDisplayedSymbol(@Nonnull PlayerSymbolHolder holder);

    @Nonnull
    String formatWinner(@Nonnull PlayerSymbolHolder holder);
}
