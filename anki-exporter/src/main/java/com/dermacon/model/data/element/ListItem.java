package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;
import com.dermacon.model.data.visitor.Token;

import java.util.List;

public class ListItem implements Token {

    public ListItem(List<Token> children) {
        this.children = children;
    }

    private final List<Token> children;

    @Override
    public List<Token> getChildren() {
        return null;
    }

    @Override
    public <E> E accept(TokenVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public boolean equals(Object o) {
        ListItem other = null;
        if (o instanceof ListItem) {
            other = (ListItem) o;
        }
        return other != null
                && this.children.equals(other.children);
    }

}
