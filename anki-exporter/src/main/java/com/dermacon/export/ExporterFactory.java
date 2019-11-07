package com.dermacon.export;

import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.model.generate.Parser;
import com.dermacon.model.generate.CSVParser;

public class ExporterFactory {

    private static final String UNIX_DEFAULT_MEDIA_PATH = "todo";

    private enum OS {
        LINUX, WINDOWS
    }

    private static String computeMediaPath() {
        return "todo";
    }

    public static FileExporter create(String[] args) throws IncompleteExportInfo {
        // todo analyse / validate args...
        String inputPath = args[0];
        String outputPath = args[1];
        Parser parser = new CSVParser(computeMediaPath());

        return new FileExporter.ExporterBuilder()
                .setMediaPath(computeMediaPath())
                .setInputPath(inputPath)
                .setOutputPath(outputPath)
                .setParser(parser)
                .build();
    }

}
