package com.dermacon;

import com.dermacon.export.ExporterFactory;
import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.fileIO.InvalidArgs;
import com.dermacon.fileIO.WrongFilePathException;
import com.dermacon.fileIO.WrongInputTypeException;

import java.io.IOException;

/**
 * Main class calling the logic.
 */
public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Anki-Pdf-Parser v1.0");
            ExporterFactory.create(args).export();
        } catch (IncompleteExportInfo
                | WrongFilePathException
                | InvalidArgs
                | WrongInputTypeException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
