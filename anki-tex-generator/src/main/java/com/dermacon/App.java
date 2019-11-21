package com.dermacon;

import com.dermacon.export.ExporterFactory;
import com.dermacon.export.MockExporter;
import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.fileIO.WrongFilePathException;
import com.dermacon.fileIO.WrongInputTypeException;
import com.dermacon.model.generate.TXTParser;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) {
//        StringBuilder out = new StringBuilder();
//        String input = "front\tback\tdeck::Einführung\n";
//        try {
//            new MockExporter(new TXTParser("path/", "deck"), input,
//             out).export();
//            System.out.println(out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            System.out.println("Anki-Pdf-Parser v1.0");
            ExporterFactory.create(args).export();
        } catch (IncompleteExportInfo
                | WrongInputTypeException
                | WrongFilePathException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
