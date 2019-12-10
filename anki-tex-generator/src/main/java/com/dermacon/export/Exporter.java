package com.dermacon.export;

import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.visitor.TexVisitor;
import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.generate.Parser;

import java.io.IOException;

/**
 * Supertype for the Exporter:
 * - reads an input
 * - takes in a parser that generates a fully qualified document tree
 * - writes output
 */
public abstract class Exporter {

    /**
     * Generates a fully qualified document
     */
    private final Parser parser;

    /**
     * Protected constructor can only be accessed via the subclasses.
     * - Sets the parser to interpret the content
     * @param parser to interpret the content
     * and other media located.
     */
    protected Exporter(Parser parser) {
        this.parser = parser;
    }

    /**
     * Exports a content provided by the subclasses.
     * - Reads the content that will be parsed
     * - Parser generates a abstract syntax tree
     * - A visitor will be created the wraps the abstract syntax in a
     * specified format.
     * - writes the output via an implemented subclass method
     */
    public void export() throws IOException {
        String content = read();
        Document document = parser.parse(content);
        FormatVisitor<String> visitor = new TexVisitor();
        String formated = document.accept(visitor);
        write(formated);
    }

    /**
     * Reads a string from a source
     * @return string generated from source
     */
    protected abstract String read() throws IOException;

    /**
     * Writes a given string to its original content
     * @param content formated content
     */
    protected abstract void write(String content) throws IOException;

}
