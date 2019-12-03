package com.dermacon.export;

import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.fileIO.InvalidArgs;
import com.dermacon.fileIO.WrongInputTypeException;
import com.dermacon.model.generate.Parser;
import com.dermacon.model.generate.TXTParser;
import org.apache.commons.io.FilenameUtils;

/**
 * Static factory that generates an exporter instance.
 */
public class ExporterFactory {

    /**
     * Creates an exporter instance
     * @param args command line args given by the user.
     * @return exporter instance
     * @throws IncompleteExportInfo one of the params for the export info
     * components is incorrect.
     * @throws WrongInputTypeException the input file to parse has the wrong
     * file type.
     */
    public static FileExporter create(String[] args) throws IncompleteExportInfo, WrongInputTypeException, InvalidArgs {
        // todo analyse / validate args...
        String inputPath = "";
        String outputPath = "";
        String imgPath = "";
        if (args.length == 3) {
            inputPath = args[0];
            outputPath = args[1];
            imgPath = args[2];
        } else {
            throw new InvalidArgs("args length: " + args.length);
        }

        String deckname = FilenameUtils.getBaseName(inputPath);
        Parser parser = new TXTParser(imgPath, deckname);

        return new FileExporter.ExporterBuilder()
                .setInputPath(inputPath)
                .setOutputDir(outputPath)
                .setParser(parser)
                .build();
    }

}
