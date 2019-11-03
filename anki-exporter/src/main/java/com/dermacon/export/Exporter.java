package com.dermacon.export;

import com.dermacon.model.data.toplevel.Document;
import com.dermacon.model.data.visitor.TexVisitor;
import com.dermacon.model.data.visitor.TokenVisitor;
import com.dermacon.model.generate.Parser;

public abstract class Exporter {

    private final Parser parser;
    private final String mediaPath;

    protected Exporter(Parser parser, String mediaPath) {
        this.parser = parser;
        this.mediaPath = mediaPath;
    }

    public void export() {
        String fileContent = read();
        Document document = parser.parse(fileContent);
        TokenVisitor<String> visitor = new TexVisitor(mediaPath);
        String formated = document.accept(visitor);
        write(formated);
    }

    protected abstract String read();
    protected abstract void write(String content);
}
