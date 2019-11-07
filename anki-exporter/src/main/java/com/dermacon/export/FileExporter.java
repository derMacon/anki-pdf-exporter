package com.dermacon.export;

import com.dermacon.fileIO.Filehandler;
import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.model.generate.Parser;

public class FileExporter extends Exporter {

    private final String inputPath;
    private final String outputPath;

    public static class ExporterBuilder {

        private String inputPath;
        private String outputPath;
        private String mediaPath;
        private Parser parser;

        public ExporterBuilder setInputPath(String inputPath) {
            this.inputPath = inputPath;
            return this;
        }

        public ExporterBuilder setOutputPath(String outputPath) {
            this.outputPath = outputPath;
            return this;
        }

        public ExporterBuilder setMediaPath(String mediaPath) {
            this.mediaPath = mediaPath;
            return this;
        }

        public ExporterBuilder setParser(Parser parser) {
            this.parser = parser;
            return this;
        }

        public FileExporter build() throws IncompleteExportInfo {
            if (inputPath == null || outputPath == null
                    || parser == null) {
                throw new IncompleteExportInfo("one of the export info components is null");
            }
            return new FileExporter(this);
        }
    }

    public FileExporter(ExporterBuilder builder) {
        super(builder.parser, builder.mediaPath);
        this.inputPath = builder.inputPath;
        this.outputPath = builder.outputPath;
    }

    @Override
    protected String read() {
        return Filehandler.read(inputPath);
    }

    @Override
    protected void write(String content) {
        Filehandler.writeFile(outputPath, content);
    }

}
