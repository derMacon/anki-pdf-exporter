package com.dermacon;

import com.dermacon.export.Exporter;
import com.dermacon.export.ExporterFactory;
import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.fileIO.InvalidArgs;

/**
 *
 */
public class App 
{
    public static void main( String[] args ) {
        try {
            System.out.println("Anki-Pdf-Parser v1.0");
            ExporterFactory.create(args).export();
        } catch (IncompleteExportInfo invalidArgs) {
            System.err.println(invalidArgs.getMessage());
        }
    }
}
