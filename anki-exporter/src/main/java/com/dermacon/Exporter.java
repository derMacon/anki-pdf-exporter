package com.dermacon;

import java.util.List;

public abstract class Exporter {
    private final String inputPath;
//    private final String outputPath;

    public Exporter(String... args) throws InvalidArgs {
        // todo analyze args
        this.inputPath = args[0];
    }

    public void export() {
        String content = Filehandler.read(inputPath);
        List<Card> stack = StackFactory.createStack(content);
        String output = createContent(stack);
//        Filehandler.writeFile(outputPath, output);
    }

    public abstract String createContent(List<Card> stack);

}
