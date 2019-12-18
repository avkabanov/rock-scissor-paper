package com.imc.kabanov.configuration;

import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.imc.kabanov.data.GameParameters;
import com.imc.kabanov.data.GameParametersFactory;
import com.imc.kabanov.validation.GamePropertiesValidator;
import com.imc.kabanov.validation.GamePropertiesValidatorImpl;
import com.imc.kabanov.validation.ValidationException;

/**
 * Configuration to load game properties from game.properties resource file
 * 
 * @author Kabanov Alexey
 */
public class GamePropertiesModule extends AbstractModule {
    
    @Override
    protected void configure() {
        try {
            Properties props = new Properties();
            props.load(getClass()
                    .getClassLoader().getResourceAsStream("game.properties"));
            Names.bindProperties(binder(), props);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config: ", e);
        };
        bind(GamePropertiesValidator.class).to(GamePropertiesValidatorImpl.class);
    }

    @Provides
    public GameParameters providesGameParameter(GameParametersFactory gameParametersFactory) throws ValidationException {
        return gameParametersFactory.createGameParameters();
    }
}
