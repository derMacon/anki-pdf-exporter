package com.dermacon.model.generate;

import com.dermacon.model.data.Card;

import java.util.List;

public class TexParser implements Parser {

    public TexParser(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    private final String mediaPath;

    @Override
    public String parse(List<Card> stack) {
        return null;
    }
}
