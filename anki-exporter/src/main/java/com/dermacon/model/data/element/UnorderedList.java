package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;
import com.dermacon.model.data.visitor.Token;

import java.util.List;

public class UnorderedList implements Token {

    private final List<ListItem> children;

    public UnorderedList(List<ListItem> children) {
        this.children = children;
    }

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
        UnorderedList other = null;
        if (o instanceof UnorderedList) {
            other = (UnorderedList) o;
        }
        return other != null
                && this.children.equals(other.children);
    }
}
