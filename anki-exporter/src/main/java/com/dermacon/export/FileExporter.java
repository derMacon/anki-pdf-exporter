package com.dermacon.export;

import com.dermacon.fileIO.Filehandler;
import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.fileIO.WrongFilePathException;
import com.dermacon.fileIO.WrongInputTypeException;
import com.dermacon.model.generate.Parser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

public class FileExporter extends Exporter {

    private static final String TEX_PDF_COMMAND = "pdflatex %s";
    private static final String OUTPUT_EXTENSION = ".tex";
    private static final String INPUT_EXTENSION = ".txt";


    private static final String[] TEMP_EXTENSIONS = new String[] {
        ".aux", ".log"
    };

    private final String inputPath;
    private final String outputDir;

    public static class ExporterBuilder {

        private String inputPath;
        private String outputDir;
        private String mediaPath;
        private Parser parser;

        public ExporterBuilder setInputPath(String inputPath) throws WrongInputTypeException {
            if (!inputPath.endsWith(INPUT_EXTENSION)) {
                new WrongInputTypeException("input file has wrong file " +
                        "extendsion");
            }
            this.inputPath = inputPath;
            return this;
        }

        public ExporterBuilder setOutputDir(String outputDir) throws WrongInputTypeException {
            if (!new File(outputDir).isDirectory()) {
                new WrongInputTypeException("output path: \"" + outputDir
                + "\" is not a directory");
            }
            this.outputDir = outputDir;
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
            if (inputPath == null || outputDir == null || parser == null) {
                throw new IncompleteExportInfo("one of the file " +
                        "exporter components is empty");
            }
            return new FileExporter(this);
        }

    }

    public FileExporter(ExporterBuilder builder) {
        super(builder.parser, builder.mediaPath);
        this.inputPath = builder.inputPath;
        this.outputDir = builder.outputDir;
    }

    @Override
    protected String read() throws IOException {
        return Filehandler.read(inputPath);
    }

    @Override
    protected void write(String content) throws IOException {
        String outputPath = createOutputPath();
        System.out.println("full: " + FilenameUtils.getFullPath(inputPath));
        Filehandler.writeFile(outputPath, content);
    }

    // todo revise method
    private String createOutputPath() throws WrongFilePathException {
        String output = inputPath;
        if (inputPath.startsWith("../")) {
            output = output.replace("../",
                    new File(System.getProperty("user.dir")).getParent()
                    + File.separator);

            if (output.contains("../")) {
                throw new WrongFilePathException("path: " + inputPath + " " +
                        "cannot contain multiple \"../\"");
            }
        }

        output = System.getProperty("user.dir")
                + File.separator
                + normalizePath(outputDir)
                + normalizePath(output)
                + OUTPUT_EXTENSION;

        return output;
    }

    public static String normalizePath(String fullFileName) {
        String output = FilenameUtils.normalize(fullFileName);
        if (!fullFileName.endsWith(INPUT_EXTENSION)
                && !fullFileName.endsWith(OUTPUT_EXTENSION)) {
            return output;
        }
        return FilenameUtils.removeExtension(FilenameUtils.getName(output));
    }
}
