package com.imc.kabanov.io;

/**
 * @author Kabanov Alexey
 */
public class ConsoleOutputWriter implements OutputWriter {
    
    @Override
    public void showToOutput(String value) {
        System.out.println(value);
    }

}
