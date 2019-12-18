package com.imc.kabanov;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.imc.kabanov.data.GameParameters;
import com.imc.kabanov.data.GameResult;
import com.imc.kabanov.data.PlayerSymbolHolder;
import com.imc.kabanov.game.TwoPlayersGame;
import com.imc.kabanov.game.player.Player;
import com.imc.kabanov.io.GameEventNotificator;

/**
 * @author Kabanov Alexey
 */
public class PaperRockScissorGame {

    private final GameParameters gameParameters;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private TwoPlayersGame game;
    private GameEventNotificator eventNotificator;

    @Inject
    public PaperRockScissorGame(@Named("firstPlayer") Player firstPlayer, @Named("secondPlayer") Player secondPlayer,
                                TwoPlayersGame game, GameParameters gameParameters,
                                GameEventNotificator eventNotificator) {

        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.game = game;
        this.gameParameters = gameParameters;
        this.eventNotificator = eventNotificator;
    }

    public void start() {
        for (int i = 0; i < gameParameters.numberOfGamesPerSeries; i++) {
            showStartGameMessage();
            GameResult result = game.play(firstPlayer, secondPlayer);
            showGameResults(result);
            showEndOfTheGame();
        }
        game.finishGame();
    }

    private void showEndOfTheGame() {
        eventNotificator.displayEndOfTheGame();
    }

    private void showStartGameMessage() {
        eventNotificator.displayStartOfTheGame();
    }

    private void showGameResults(GameResult result) {
        printNameAndSymbol(result.getFirstPlayer());
        printNameAndSymbol(result.getSecondPlayer());
        printWinner(result.getWinner());
    }

    private void printNameAndSymbol(PlayerSymbolHolder holder) {
        eventNotificator.showNameAndDisplayedSymbol(holder);
    }

    private void printWinner(PlayerSymbolHolder holder) {
        eventNotificator.showWinner(holder);
    }
}
