package com.imc.kabanov.validation;

/**
 * @author Kabanov Alexey
 */
public interface GamePropertiesValidator {
    
    void validateNumberOfGamesPerSeries(int numberOfGames) throws ValidationException;

}
