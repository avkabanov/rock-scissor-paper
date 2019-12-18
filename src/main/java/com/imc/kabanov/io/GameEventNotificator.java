package com.imc.kabanov.io;

import com.imc.kabanov.data.PlayerSymbolHolder;
import com.imc.kabanov.data.Symbol;

/**
 * @author Kabanov Alexey
 */
public interface GameEventNotificator {
    void displayEndOfTheGame();

    void displayStartOfTheGame();

    void showNameAndDisplayedSymbol(PlayerSymbolHolder holder);

    void showWinner(PlayerSymbolHolder holder);

    void showSameSymbolsDisplayed(Symbol symbol);

    void showEnterTheChoice();

    void showCanNotParseInput(String line);
}
