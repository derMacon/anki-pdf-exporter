package com.dermacon.export;

import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.fileIO.WrongInputTypeException;
import com.dermacon.model.generate.Parser;
import com.dermacon.model.generate.TXTParser;

public class ExporterFactory {

    public static FileExporter create(String[] args) throws IncompleteExportInfo, WrongInputTypeException {
        // todo analyse / validate args...
        String inputPath = args[0];
        String outputPath = args[1];
        String imgPath = args[2];
        String deckname = "todo extract deck name";
        Parser parser = new TXTParser(imgPath, deckname);

        return new FileExporter.ExporterBuilder()
                .setInputPath(inputPath)
                .setOutputDir(outputPath)
                .setParser(parser)
                .build();
    }

}
