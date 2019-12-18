package com.imc.kabanov.io;

import javax.annotation.Nonnull;

import com.imc.kabanov.data.PlayerSymbolHolder;

/**
 * @author Kabanov Alexey
 */
public class OutputFormatterImpl implements OutputFormatter {
    
    @Nonnull
    @Override
    public String formatDisplayedSymbol(@Nonnull PlayerSymbolHolder holder) {
        return "Player " + holder.getPlayer().getName() + " displayed " + holder.getSymbol().getDisplayName();
    }

    @Nonnull
    @Override
    public String formatWinner(@Nonnull PlayerSymbolHolder holder) {
        return "The winner is " + holder.getPlayer().getName() + " with symbol " + holder.getSymbol().getDisplayName();
    }
}
