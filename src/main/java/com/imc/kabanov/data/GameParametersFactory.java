package com.imc.kabanov.data;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.imc.kabanov.validation.GamePropertiesValidator;
import com.imc.kabanov.validation.ValidationException;

/**
 * @author Kabanov Alexey
 */
public class GameParametersFactory {

    private String numberOfGamesInASeries;
    private GamePropertiesValidator validator;

    @Inject
    public GameParametersFactory(@Named("number_of_games_per_series") String numberOfGamesInASeries,
                                 GamePropertiesValidator validator) {
        this.numberOfGamesInASeries = numberOfGamesInASeries;
        this.validator = validator;
    }

    public GameParameters createGameParameters() throws ValidationException {
        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(numberOfGamesInASeries);
        } catch (NumberFormatException e) {
            throw new ValidationException(
                    "Number of games must be an integer. Can not parse: " + numberOfGamesInASeries);
        }

        validator.validateNumberOfGamesPerSeries(parsedNumber);
        return new GameParameters(parsedNumber);
    }
}
