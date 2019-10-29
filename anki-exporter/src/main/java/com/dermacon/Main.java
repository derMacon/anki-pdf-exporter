package com.dermacon;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Anki-Pdf-Parser v1.0");
            new TexExporter(args).export();
        } catch (InvalidArgs invalidArgs) {
            System.err.println(invalidArgs.getMessage());
        }
    }
}
