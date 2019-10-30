package com.dermacon.model.data;

import java.util.List;

public class ListItem implements Token {

    private final List<Token> children;

    public ListItem(List<Token> children) {
        this.children = children;
    }

    @Override
    public List<Token> getChildren() {
        return this.children;
    }

    @Override
    public String getValue() {
        return null;
    }

}
