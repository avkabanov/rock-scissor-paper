package com.imc.kabanov.game.player;

import com.imc.kabanov.data.Symbol;

/**
 * @author Kabanov Alexey
 */
public interface Player {
    
    Symbol makeTurn();
    
    String getName();
}
