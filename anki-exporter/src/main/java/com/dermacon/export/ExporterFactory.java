package com.dermacon.export;

import com.dermacon.fileIO.Filehandler;
import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.model.data.Card;
import com.dermacon.model.generate.Parser;
import com.dermacon.model.generate.StackFactory;
import com.dermacon.model.generate.TexParser;

import java.util.List;

public class ExporterFactory {

    private static final String UNIX_DEFAULT_MEDIA_PATH = "todo";

    private enum OS {
        LINUX, WINDOWS
    }

    private static String computeMediaPath() {
        return "todo";
    }

    public static Exporter create(String[] args) throws IncompleteExportInfo {
        // todo analyse / validate args...
        String inputPath = args[0];
        String outputPath = args[1];
        Parser parser = new TexParser(computeMediaPath());

        String content = Filehandler.read(inputPath);
        List<Card> deck = StackFactory.createStack(content);
        return new Exporter.ExporterBuilder()
                .setInputPath(inputPath)
                .setOutputPath(outputPath)
                .setDeck(deck)
                .setParser(parser)
                .build();
    }

}
