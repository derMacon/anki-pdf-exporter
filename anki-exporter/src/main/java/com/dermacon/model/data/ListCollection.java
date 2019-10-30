package com.dermacon.model.data;

import java.util.List;

public class ListCollection implements Token {

    private List<Token> items;

    @Override
    public List<Token> getChildren() {
        return items;
    }

    @Override
    public String getValue() {
        return null;
    }
}
