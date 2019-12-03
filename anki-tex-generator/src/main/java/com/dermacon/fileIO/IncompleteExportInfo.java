package com.dermacon.fileIO;

/**
 * Exception that will be thrown when the information is not appropriate for
 * the Exporter Superclass.
 */
public class IncompleteExportInfo extends Exception {
    public IncompleteExportInfo(String message) {
        super(message);
    }
}
