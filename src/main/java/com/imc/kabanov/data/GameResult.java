package com.imc.kabanov.data;

/**
 * @author Kabanov Alexey
 */
public class GameResult {
    private final PlayerSymbolHolder firstPlayer;
    private final PlayerSymbolHolder secondPlayer;
    private final PlayerSymbolHolder winner;

    public GameResult(PlayerSymbolHolder firstPlayer, PlayerSymbolHolder secondPlayer, PlayerSymbolHolder winner) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.winner = winner;
    }

    public PlayerSymbolHolder getFirstPlayer() {
        return firstPlayer;
    }

    public PlayerSymbolHolder getSecondPlayer() {
        return secondPlayer;
    }

    public PlayerSymbolHolder getWinner() {
        return winner;
    }
}
