package com.dermacon.model.generate;

import com.dermacon.model.data.toplevel.Document;

public class TexParser implements Parser {

    public TexParser(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    private final String mediaPath;

    @Override
    public Document parse(String content) {
        return null;
    }
}
