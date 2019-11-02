package com.dermacon.export;

import com.dermacon.fileIO.Filehandler;
import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.model.data.toplevel.Document;
import com.dermacon.model.data.visitor.TexVisitor;
import com.dermacon.model.data.visitor.TokenVisitor;
import com.dermacon.model.generate.Parser;

public class Exporter {

    private final String inputPath;
    private final String outputPath;
    private final Parser parser;

    public static class ExporterBuilder {

        private String inputPath;
        private String outputPath;
        private Parser parser;

        public ExporterBuilder setInputPath(String inputPath) {
            this.inputPath = inputPath;
            return this;
        }

        public ExporterBuilder setOutputPath(String outputPath) {
            this.outputPath = outputPath;
            return this;
        }

        public ExporterBuilder setParser(Parser parser) {
            this.parser = parser;
            return this;
        }

        public Exporter build() throws IncompleteExportInfo {
            if (inputPath == null || outputPath == null
                    || parser == null) {
                throw new IncompleteExportInfo("one of the export info components is null");
            }
            return new Exporter(this);
        }
    }

    public Exporter(ExporterBuilder builder) {
        this.inputPath = builder.inputPath;
        this.outputPath = builder.outputPath;
        this.parser = builder.parser;
    }

    public void export() {
        String fileContent = Filehandler.read(inputPath);
        Document document = parser.parse(fileContent);
        TokenVisitor<String> visitor = new TexVisitor();
        String formated = document.accept(visitor);
        Filehandler.writeFile(outputPath, formated);
    }

}
