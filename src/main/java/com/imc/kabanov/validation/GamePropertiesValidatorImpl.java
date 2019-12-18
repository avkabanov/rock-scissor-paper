package com.imc.kabanov.validation;

/**
 * @author Kabanov Alexey
 */
public class GamePropertiesValidatorImpl implements GamePropertiesValidator {
    
    @Override
    public void validateNumberOfGamesPerSeries(int numberOfGames) throws ValidationException {
        if (numberOfGames <= 0) {
            throw new ValidationException("Number of games should be greater than 0");
        }
    }
}
