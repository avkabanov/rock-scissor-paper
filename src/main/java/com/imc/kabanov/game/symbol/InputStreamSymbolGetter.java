package com.imc.kabanov.game.symbol;

import javax.annotation.Nonnull;

import com.google.inject.Inject;
import com.imc.kabanov.data.Symbol;
import com.imc.kabanov.io.GameEventNotificator;
import com.imc.kabanov.io.InputReader;

/**
 * @author Kabanov Alexey
 */
public class InputStreamSymbolGetter implements SymbolGetter {

    private InputReader inputStream;
    private GameEventNotificator eventNotificator;

    @Inject
    public InputStreamSymbolGetter(InputReader inputStream, GameEventNotificator eventNotificator) {
        this.inputStream = inputStream;
        this.eventNotificator = eventNotificator;
    }

    private Symbol parseSymbol(String line) {
        return Symbol.valueOfAlias(line.trim().toUpperCase());
    }

    @Nonnull
    @Override
    public Symbol getSymbol() {
        Symbol symbol;

        do {
            eventNotificator.showEnterTheChoice();
            String line = inputStream.readLine();

            symbol = parseSymbol(line);
            if (symbol == null) {
                eventNotificator.showCanNotParseInput(line);
            }
        } while (symbol == null);
        return symbol;
    }
}