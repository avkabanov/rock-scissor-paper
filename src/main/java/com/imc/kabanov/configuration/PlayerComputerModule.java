package com.imc.kabanov.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.imc.kabanov.game.player.Computer;
import com.imc.kabanov.game.player.Human;
import com.imc.kabanov.game.player.Player;
import com.imc.kabanov.game.symbol.InputStreamSymbolGetter;
import com.imc.kabanov.game.symbol.RandomSymbolGetter;
import com.imc.kabanov.game.symbol.SymbolGetter;

/**
 * Configures one player to be a human with user input from stream, and another - to be a computer with input from a 
 * random numbers
 * 
 * @author Kabanov Alexey
 */
public class PlayerComputerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SymbolGetter.class).annotatedWith(Names.named("random")).to(RandomSymbolGetter.class);
        bind(SymbolGetter.class).annotatedWith(Names.named("userInput")).to(InputStreamSymbolGetter.class);
        bind(Player.class).annotatedWith(Names.named("firstPlayer")).to(Human.class);
        bind(Player.class).annotatedWith(Names.named("secondPlayer")).to(Computer.class);
    }
}
