package com.dermacon.model.data;

import java.util.List;

public class PlainText implements Token {

    private String value;

    @Override
    public List<Token> getChildren() {
        return null;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
