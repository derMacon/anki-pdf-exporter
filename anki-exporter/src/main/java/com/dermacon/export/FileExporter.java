package com.dermacon.export;

import com.dermacon.fileIO.Filehandler;
import com.dermacon.fileIO.IncompleteExportInfo;
import com.dermacon.fileIO.WrongInputTypeException;
import com.dermacon.model.generate.Parser;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileExporter extends Exporter {

    private static final String OUTPUT_DIR = System.getProperty("user.dir")
            + File.separator + "output" + File.separator;
    private static final String TEX_PDF_COMMAND = "pdflatex %s";
    private static final String OUTPUT_EXTENSION = ".tex";
    private static final String INPUT_EXTENSION = ".txt";


    private static final String[] TEMP_EXTENSIONS = new String[] {
        ".aux", ".log"
    };

    private final String inputPath;

    public static class ExporterBuilder {

        private String inputPath;
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

        public ExporterBuilder setMediaPath(String mediaPath) {
            this.mediaPath = mediaPath;
            return this;
        }

        public ExporterBuilder setParser(Parser parser) {
            this.parser = parser;
            return this;
        }

        public FileExporter build() throws IncompleteExportInfo {
            if (inputPath == null || parser == null) {
                throw new IncompleteExportInfo("one of the file " +
                        "exporter components is empty");
            }
            return new FileExporter(this);
        }

    }

    public FileExporter(ExporterBuilder builder) {
        super(builder.parser, builder.mediaPath);
        this.inputPath = builder.inputPath;
    }

    @Override
    protected String read() throws IOException {
        return Filehandler.read(inputPath);
    }

    @Override
    protected void write(String content) throws IOException {
        String outputPath = OUTPUT_DIR
                + removeExtension(this.inputPath)
                + OUTPUT_EXTENSION;
        Filehandler.writeFile(outputPath, content);
//        generatePdf(outputPath);
    }

    private void generatePdf(String outputPath) throws IOException {
        String command = String.format(TEX_PDF_COMMAND, outputPath);
        System.out.println("command: " + command);
        Runtime.getRuntime().exec(command);
//        Runtime.getRuntime().exec(command, null, new File(OUTPUT_DIR));

//        String fileName = removeExtension(outputPath);
//        for (String ext : TEMP_EXTENSIONS) {
//            Filehandler.saveDelete(fileName + ext);
//        }
    }

    public static String removeExtension(String fullFileName) {
        String regex = "((.*/)*|.\\/)?(.*)" + INPUT_EXTENSION;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(fullFileName);

        if (m.find()) {
            return m.group(3);
        }
        return fullFileName;
    }
}
