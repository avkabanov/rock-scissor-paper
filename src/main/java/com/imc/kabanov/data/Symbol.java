package com.imc.kabanov.data;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Kabanov Alexey
 */
public enum Symbol {
    PAPER("P", "Paper") {
        @Override
        public boolean beats(Symbol symbol) {
            return symbol == Symbol.ROCK;
        }
    },
    ROCK("R", "Rock") {
        @Override
        public boolean beats(Symbol symbol) {
            return symbol == Symbol.SCISSORS;
        }
    },
    SCISSORS("S", "Scissors") {
        @Override
        public boolean beats(Symbol symbol) {
            return symbol == Symbol.PAPER;
        }
    };

    private String stringAlias;
    private String displayName;

    Symbol(String stringAlias, String displayName) {
        this.stringAlias = stringAlias;
        this.displayName = displayName;
    }

    @Nullable
    public static Symbol valueOfAlias(@Nonnull String trim) {
        for (Symbol value : values()) {
            if (value.stringAlias.equals(trim)) {
                return value;
            }
        }
        return null;
    }

    public static Set<String> getAllAliases() {
        return Arrays.stream(values())
                .map(value -> value.stringAlias)
                .collect(Collectors.toSet());
    }

    public abstract boolean beats(Symbol symbol);

    public String getDisplayName() {
        return displayName;
    }
}
