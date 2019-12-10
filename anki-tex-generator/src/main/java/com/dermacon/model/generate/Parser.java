package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.document.Document;

/**
 * Parser interface
 */
public interface Parser {

    /**
     * Generating a document instance from the String content.
     * @param content String content to parse
     * @return document node structure depending on the given input.
     */
    Document parse(String content);
}
