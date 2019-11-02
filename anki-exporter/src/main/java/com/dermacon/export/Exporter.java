package com.dermacon.export;

import com.dermacon.model.data.toplevel.Document;
import com.dermacon.model.data.visitor.TexVisitor;
import com.dermacon.model.generate.Parser;

public abstract class Exporter {
    private final Parser parser;

    protected Exporter(Parser parser) {
        this.parser = parser;
    }

    public void export() {
        String fileContent = read();
        Document document = parser.parse(fileContent);
        String formated = document.accept(new TexVisitor());
        write(formated);
    }

    protected abstract String read();
    protected abstract void write(String content);
}
