package com.dermacon.fileIO;

import java.io.IOException;

/**
 * Invalid file extension
 */
public class WrongInputTypeException extends IOException {
    public WrongInputTypeException(String message) {
        super(message);
    }
}
