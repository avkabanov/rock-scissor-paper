package com.imc.kabanov.configuration;

import com.google.inject.AbstractModule;
import com.imc.kabanov.game.TwoPlayersGame;
import com.imc.kabanov.game.TwoPlayersGameImpl;
import com.imc.kabanov.strategy.GameStrategy;
import com.imc.kabanov.strategy.TwoPlayersStrategy;

/**
 * Configures game strategy for two players mode 
 * 
 * @author Kabanov Alexey
 */
public class TwoPlayersGameModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TwoPlayersGame.class).to(TwoPlayersGameImpl.class);
        bind(GameStrategy.class).to(TwoPlayersStrategy.class);
        
    }
}
