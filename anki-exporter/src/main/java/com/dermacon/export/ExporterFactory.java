package com.dermacon.export;

import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.fileIO.WrongInputTypeException;
import com.dermacon.model.generate.Parser;
import com.dermacon.model.generate.TXTParser;

public class ExporterFactory {

    private static final String UNIX_DEFAULT_MEDIA_PATH = "todo";

    private enum OS {
        LINUX, WINDOWS
    }

    private static String computeMediaPath() {
        return "todo";
    }

    public static FileExporter create(String[] args) throws IncompleteExportInfo, WrongInputTypeException {
        // todo analyse / validate args...
        String inputPath = args[0];
        String outputPath = args[1];
        String deckname = "todo extract deck name";
        Parser parser = new TXTParser(computeMediaPath(), deckname);

        return new FileExporter.ExporterBuilder()
                .setMediaPath(computeMediaPath())
                .setInputPath(inputPath)
                .setOutputDir(outputPath)
                .setParser(parser)
                .build();
    }

}
