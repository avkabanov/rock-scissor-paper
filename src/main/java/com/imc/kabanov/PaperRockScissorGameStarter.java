package com.imc.kabanov;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.imc.kabanov.configuration.ConsoleInputOutputModule;
import com.imc.kabanov.configuration.GamePropertiesModule;
import com.imc.kabanov.configuration.PlayerComputerModule;
import com.imc.kabanov.configuration.TwoPlayersGameModule;

/**
 * @author Kabanov Alexey
 */
public class PaperRockScissorGameStarter {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TwoPlayersGameModule(),
                new PlayerComputerModule(),
                new ConsoleInputOutputModule(), 
                new GamePropertiesModule());

        PaperRockScissorGame game = injector.getInstance(PaperRockScissorGame.class);
        game.start();
    }
}
