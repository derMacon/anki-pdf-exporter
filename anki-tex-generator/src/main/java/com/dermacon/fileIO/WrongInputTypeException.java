package com.dermacon.fileIO;

import java.io.IOException;

public class WrongInputTypeException extends IOException {
    public WrongInputTypeException(String message) {
        super(message);
    }
}
