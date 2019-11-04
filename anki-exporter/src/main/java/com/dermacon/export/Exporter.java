package com.dermacon.export;

import com.dermacon.model.data.toplevel.Document;
import com.dermacon.model.data.visitor.TexVisitor;
import com.dermacon.model.data.visitor.TokenVisitor;
import com.dermacon.model.generate.Parser;

/**
 * Supertype for the Exporter:
 * -
 */
public abstract class Exporter {

    private final Parser parser;
    private final String mediaPath;

    /**
     * Protected constructor can only be accessed via the subclasses.
     * - Sets the parser to interpret the content
     * - Sets the media path used by anki droid itself. Here are all images
     * and other media located.
     * @param parser to interpret the content
     * @param mediaPath used by anki droid itself. Here are all images
     * and other media located.
     */
    protected Exporter(Parser parser, String mediaPath) {
        this.parser = parser;
        this.mediaPath = mediaPath;
    }

    /**
     * Exports a content provided by the subclasses.
     * - Reads the content that will be parsed
     * - Parser generates a abstract syntax tree
     * - A visitor will be created the wraps the abstract syntax in a
     * specified format.
     * - writes the output via a implemented subclass method
     */
    public void export() {
        String content = read();
        Document document = parser.parse(content);
        TokenVisitor<String> visitor = new TexVisitor(mediaPath);
        String formated = document.accept(visitor);
        write(formated);
    }

    /**
     * Reads a string from a source
     * @return string generated from source
     */
    protected abstract String read();

    /**
     * Writes a given string to its original content
     * @param content formated content
     */
    protected abstract void write(String content);
}
