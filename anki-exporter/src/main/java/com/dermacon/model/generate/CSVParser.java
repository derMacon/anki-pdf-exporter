package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.document.Document;

public class CSVParser implements Parser {

    public CSVParser(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    private final String mediaPath;

    @Override
    public Document parse(String content) {
        return null;
    }
}
