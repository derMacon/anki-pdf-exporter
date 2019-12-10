package com.dermacon.fileIO;

/**
 * Thrown if the provided command line args are incomplete / incorrect.
 */
public class InvalidArgs extends Exception {
    public InvalidArgs(String message) {
        super(message);
    }
}
