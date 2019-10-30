package com.dermacon.export;

import com.dermacon.fileIO.Filehandler;
import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.fileIO.InvalidArgs;
import com.dermacon.model.generate.Parser;
import com.dermacon.model.generate.StackFactory;
import com.dermacon.model.data.Card;

import java.util.List;

public class Exporter {

    private final String inputPath;
    private final String outputPath;
    private final List<Card> deck;
    private final Parser parser;

    public static class ExporterBuilder {

        private String inputPath;
        private String outputPath;
        private List<Card> deck;
        private Parser parser;

        public ExporterBuilder setInputPath(String inputPath) {
            this.inputPath = inputPath;
            return this;
        }

        public ExporterBuilder setOutputPath(String outputPath) {
            this.outputPath = outputPath;
            return this;
        }

        public ExporterBuilder setDeck(List<Card> deck) {
            this.deck = deck;
            return this;
        }

        public ExporterBuilder setParser(Parser parser) {
            this.parser = parser;
            return this;
        }

        public Exporter build() throws IncompleteExportInfo {
            if (inputPath == null || outputPath == null
                    || deck == null || parser == null) {
                throw new IncompleteExportInfo("one of the export info components is null");
            }
            return new Exporter(this);
        }
    }

    public Exporter(ExporterBuilder builder) {
        this.inputPath = builder.inputPath;
        this.outputPath = builder.outputPath;
        this.deck = builder.deck;
        this.parser = builder.parser;
    }

    public void export() {
        String output = parser.parse(deck);
        Filehandler.writeFile(outputPath, output);
    }

}
