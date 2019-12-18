package com.imc.kabanov.game;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.imc.kabanov.data.GameResult;
import com.imc.kabanov.data.PlayerSymbolHolder;
import com.imc.kabanov.data.Symbol;
import com.imc.kabanov.game.player.Player;
import com.imc.kabanov.io.GameEventNotificator;
import com.imc.kabanov.strategy.GameStrategy;
import com.imc.kabanov.strategy.TwoPlayersStrategy;

/**
 * @author Kabanov Alexey
 */
@RunWith(MockitoJUnitRunner.class)
public class TwoPlayersGameImplTest {

    @Mock private Player firstPlayer;
    @Mock private Player secondPlayer;
    @Mock private GameEventNotificator eventNotificator;

    @Captor private ArgumentCaptor<PlayerSymbolHolder> firstPlayerCaptor;
    @Captor private ArgumentCaptor<PlayerSymbolHolder> secondPlayerCaptor;

    private GameStrategy gameStrategy;
    private TwoPlayersGameImpl game;

    @Before
    public void setup() {
        gameStrategy = Mockito.spy(new TwoPlayersStrategy());
        game = new TwoPlayersGameImpl(gameStrategy, eventNotificator);
    }

    @Test
    public void shouldReturnFirstPlayerWhenFirstIsTheWinner() {
        Mockito.when(firstPlayer.makeTurn()).thenReturn(Symbol.ROCK);
        Mockito.when(secondPlayer.makeTurn()).thenReturn(Symbol.SCISSORS);

        GameResult result = game.play(firstPlayer, secondPlayer);

        Assert.assertSame(firstPlayer, result.getWinner().getPlayer());
        Assert.assertSame(Symbol.ROCK, result.getWinner().getSymbol());
    }

    @Test
    public void shouldRethrowOnTheSameSymbols() {
        Mockito.when(firstPlayer.makeTurn()).thenReturn(Symbol.ROCK);
        Mockito.when(secondPlayer.makeTurn()).thenReturn(Symbol.ROCK).thenReturn(Symbol.PAPER);

        GameResult result = game.play(firstPlayer, secondPlayer);

        Assert.assertSame(secondPlayer, result.getWinner().getPlayer());
        Mockito.verify(
                gameStrategy, Mockito.times(2))
                .findWinner(firstPlayerCaptor.capture(), secondPlayerCaptor.capture());

        // first round
        Assert.assertEquals(Symbol.ROCK, firstPlayerCaptor.getAllValues().get(0).getSymbol());
        Assert.assertEquals(Symbol.ROCK, secondPlayerCaptor.getAllValues().get(0).getSymbol());

        // second round
        Assert.assertEquals(Symbol.ROCK, firstPlayerCaptor.getAllValues().get(1).getSymbol());
        Assert.assertEquals(Symbol.PAPER, secondPlayerCaptor.getAllValues().get(1).getSymbol());

    }

    // In this test we want to check that both methods "makeTurn" executes async and do not block for each other.
    // The test can look in the following way: we enter inside "make turn' on one thread and wait until the other 
    // thread will enter inside it's own "make turn" method.
    // The problem is we do not know when thread, that performs "make turn" will start. So, if we are inside
    // "make turn" of one thread, but the other thread is not entered inside "make turn" method yet, that means one
    // of two things: either thread is not started yet (because we don't know when it is going to start) or the second
    // thread is blocked by the first one.
    // To distinguish these two situations, it has been decided to make a time threshold that means the time, that takes
    // for a thread to start. 
    // With the other words, if we are inside "makeTurn" of one thread, but after time threshold the other thread 
    // had not entered "make turn" method - we consider this situation as one thread is blocked by another.
    @Test
    public void shouldMakeTurnMethodDoNotBlockEachOther() {
        
        // threshold time has been chosen experimentally
        final int timeoutThresholdMillis = 2000; 
        
        CountDownLatch firstLatch = new CountDownLatch(1);
        CountDownLatch secondLatch = new CountDownLatch(1);
        Mockito.when(firstPlayer.makeTurn()).then((Answer<Symbol>) invocation -> {
            firstLatch.countDown();
            boolean quitedByLatchCondition = secondLatch.await(timeoutThresholdMillis, TimeUnit.MILLISECONDS);
            Assert.assertTrue(quitedByLatchCondition);
            return Symbol.PAPER;
        });
        Mockito.when(secondPlayer.makeTurn()).then((Answer<Symbol>) invocation -> {
            boolean quitedByLatchCondition = firstLatch.await(timeoutThresholdMillis, TimeUnit.MILLISECONDS);
            secondLatch.countDown();
            Assert.assertTrue(quitedByLatchCondition);
            return Symbol.ROCK;
        });

        game.play(firstPlayer, secondPlayer);
    }
}