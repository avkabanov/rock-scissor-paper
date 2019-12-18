package com.imc.kabanov.game;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.inject.Inject;
import com.imc.kabanov.data.GameResult;
import com.imc.kabanov.data.PlayerSymbolHolder;
import com.imc.kabanov.data.Symbol;
import com.imc.kabanov.game.player.Player;
import com.imc.kabanov.io.GameEventNotificator;
import com.imc.kabanov.strategy.GameStrategy;

/**
 * @author Kabanov Alexey
 */
public class TwoPlayersGameImpl implements TwoPlayersGame {
    private final int numberOfPlayers = 2;
    private ExecutorService service = Executors.newFixedThreadPool(numberOfPlayers);
    
    private GameStrategy strategy;
    private GameEventNotificator eventNotificator;

    @Inject
    public TwoPlayersGameImpl(GameStrategy strategy, GameEventNotificator eventNotificator) {
        this.strategy = strategy;
        this.eventNotificator = eventNotificator;
    }

    @Override
    public GameResult play(Player firstPlayer, Player secondPlayer) {
        Symbol firstChoice;
        Symbol secondChoice;
        PlayerSymbolHolder firstPlayerSymbol;
        PlayerSymbolHolder secondPlayerSymbol;

        PlayerSymbolHolder winnerPlayerSymbol;
        do {
            Future<Symbol> firstPlayerTurnFuture = service.submit(firstPlayer::makeTurn);
            Future<Symbol> secondPlayerTurnFuture = service.submit(secondPlayer::makeTurn);

            try {
                firstChoice = firstPlayerTurnFuture.get();
                secondChoice = secondPlayerTurnFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);    
            } 
            
            firstPlayerSymbol = new PlayerSymbolHolder(firstPlayer, firstChoice);
            secondPlayerSymbol = new PlayerSymbolHolder(secondPlayer, secondChoice);
            winnerPlayerSymbol = strategy.findWinner(firstPlayerSymbol, secondPlayerSymbol);
            
            if (winnerPlayerSymbol == null) {
                eventNotificator.showSameSymbolsDisplayed(firstPlayerSymbol.getSymbol());
            }
            
        } while (winnerPlayerSymbol == null);

        return new GameResult(firstPlayerSymbol, secondPlayerSymbol, winnerPlayerSymbol);
    }

    @Override
    public void finishGame() {
        service.shutdown();
    }
}
