package com.imc.kabanov.game.symbol;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.imc.kabanov.data.Symbol;
import com.imc.kabanov.data.SymbolTest;
import com.imc.kabanov.io.GameEventNotificator;
import com.imc.kabanov.io.InputReader;

/**
 * @author Kabanov Alexey
 */
@RunWith(MockitoJUnitRunner.class)
public class InputStreamSymbolGetterTest {

    @Mock private GameEventNotificator notificator;
    @Mock private InputReader inputReader;
    @Captor private ArgumentCaptor<String> stringArgumentCaptor;

    private InputStreamSymbolGetter inputStreamSymbolGetter;

    @Test
    public void shouldGetSymbolFromCorrectString() {
        Mockito.when(inputReader.readLine()).thenReturn("R");

        inputStreamSymbolGetter = new InputStreamSymbolGetter(inputReader, notificator);

        Assert.assertEquals(Symbol.ROCK, inputStreamSymbolGetter.getSymbol());
    }

    // we input valid and invalid symbol and check that "Can not parse" message shown once
    @Test
    public void shouldRetryOnInvalidSymbol() {
        Mockito.when(inputReader.readLine())
                .thenReturn(SymbolTest.INVALID_SYMBOL_ALIAS)
                .thenReturn("R");

        inputStreamSymbolGetter = new InputStreamSymbolGetter(inputReader, notificator);
        Symbol parsedSymbol = inputStreamSymbolGetter.getSymbol();
        
        Mockito.verify(notificator, Mockito.times(1))
                .showCanNotParseInput(stringArgumentCaptor.capture());
        
        Assert.assertEquals(SymbolTest.INVALID_SYMBOL_ALIAS, stringArgumentCaptor.getValue());
        Assert.assertEquals(Symbol.ROCK, parsedSymbol);
    }

}