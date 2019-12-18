package com.imc.kabanov.data;

/**
 * @author Kabanov Alexey
 */
public class GameParameters {
    public final int numberOfGamesPerSeries;

    public GameParameters(int numberOfGamesPerSeries) {
        this.numberOfGamesPerSeries = numberOfGamesPerSeries;
    }

    public int getNumberOfGamesPerSeries() {
        return numberOfGamesPerSeries;
    }
}
