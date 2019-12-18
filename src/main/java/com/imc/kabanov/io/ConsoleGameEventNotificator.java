package com.imc.kabanov.io;

import javax.inject.Inject;

import com.imc.kabanov.data.PlayerSymbolHolder;
import com.imc.kabanov.data.Symbol;

/**
 * @author Kabanov Alexey
 */
public class ConsoleGameEventNotificator implements GameEventNotificator {
    private OutputWriter outputWriter;
    private OutputFormatter formatter;

    @Inject
    public ConsoleGameEventNotificator(OutputWriter outputWriter, OutputFormatter formatter) {
        this.outputWriter = outputWriter;
        this.formatter = formatter;
    }

    @Override
    public void displayEndOfTheGame() {
        outputWriter.showToOutput("Game finished\n");
    }

    @Override
    public void displayStartOfTheGame() {
        outputWriter.showToOutput("New game");

    }

    @Override
    public void showNameAndDisplayedSymbol(PlayerSymbolHolder holder) {
        outputWriter.showToOutput(formatter.formatDisplayedSymbol(holder));
    }

    @Override
    public void showWinner(PlayerSymbolHolder holder) {
        outputWriter.showToOutput(formatter.formatWinner(holder));
    }

    @Override
    public void showSameSymbolsDisplayed(Symbol symbol) {
        outputWriter.showToOutput("Both players displayed " + symbol.getDisplayName() + ". Rethrowing");
    }

    @Override
    public void showEnterTheChoice() {
        outputWriter.showToOutput("Please enter your choice. Possible values are " + Symbol.getAllAliases());
    }

    @Override
    public void showCanNotParseInput(String line) {
        outputWriter.showToOutput("Can not parse " + line + ". Possible values are: " + Symbol.getAllAliases());
    }
}
