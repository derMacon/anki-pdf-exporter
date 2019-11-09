package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.document.Document;

public class TXTParser implements Parser {

    public TXTParser(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    private final String mediaPath;

    @Override
    public Document parse(String content) {
        return null;
    }
}
