package com.dermacon.fileIO;

import java.io.IOException;

/**
 * Wrong file path
 */
public class WrongFilePathException extends IOException {
    public WrongFilePathException(String message) {
        super(message);
    }
}
