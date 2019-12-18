package com.imc.kabanov.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.Nonnull;

/**
 * @author Kabanov Alexey
 */
public class ConsoleInputReader implements InputReader {
    
    //private final Scanner scanner = new Scanner(System.in);
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    @Nonnull
    @Override
    public String readLine() {
        //return scanner.next();
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
