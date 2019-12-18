package com.imc.kabanov.configuration;

import com.google.inject.AbstractModule;
import com.imc.kabanov.io.ConsoleGameEventNotificator;
import com.imc.kabanov.io.ConsoleInputReader;
import com.imc.kabanov.io.ConsoleOutputWriter;
import com.imc.kabanov.io.GameEventNotificator;
import com.imc.kabanov.io.InputReader;
import com.imc.kabanov.io.OutputFormatter;
import com.imc.kabanov.io.OutputFormatterImpl;
import com.imc.kabanov.io.OutputWriter;

/** 
 * Class configures all input and output goes to the console
 * 
 * @author Kabanov Alexey
 */
public class ConsoleInputOutputModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(OutputWriter.class).to(ConsoleOutputWriter.class);
        bind(InputReader.class).to(ConsoleInputReader.class);
        bind(OutputFormatter.class).to(OutputFormatterImpl.class);
        bind(GameEventNotificator.class).to(ConsoleGameEventNotificator.class);
    }
}
