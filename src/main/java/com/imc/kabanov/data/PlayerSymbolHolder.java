package com.imc.kabanov.data;

import com.imc.kabanov.game.player.Player;

/**
 * @author Kabanov Alexey
 */
public class PlayerSymbolHolder {
    
    private final Player player;
    private final Symbol symbol;

    public PlayerSymbolHolder(Player player, Symbol symbol) {
        this.player = player;
        this.symbol = symbol;
    }

    public Player getPlayer() {
        return player;
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    
}
