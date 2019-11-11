//package com.dermacon;
//
//import com.dermacon.export.ExporterFactory;
//import com.dermacon.fileIO.IncompleteExportInfo;
//import com.dermacon.fileIO.WrongInputTypeException;
//
//import java.io.IOException;
//
///**
// *
// */
//public class App
//{
//    public static void main( String[] args ) {
//        try {
//            System.out.println("Anki-Pdf-Parser v1.0");
//            ExporterFactory.create(args).export();
//        } catch (IncompleteExportInfo | WrongInputTypeException e) {
//            System.err.println(e.getMessage());
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//}


package com.dermacon;

import com.dermacon.export.Exporter;
import com.dermacon.export.ExporterFactory;
import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.fileIO.InvalidArgs;

import java.io.IOException;

/**
 *
 */
public class App
{
//    public static void main( String[] args ) {
//        try {
//            System.out.println("Anki-Pdf-Parser v1.0");
//            ExporterFactory.create(args).export();
//        } catch (IncompleteExportInfo invalidArgs) {
//            System.err.println(invalidArgs.getMessage());
//        }
//    }

    public static void main(String[] args) throws IOException {
        System.out.println("hello");
        System.out.println(System.getProperty("user.dir" +
                ""));
        Runtime.getRuntime().exec("pdflatex ./test.tex");
    }

}


