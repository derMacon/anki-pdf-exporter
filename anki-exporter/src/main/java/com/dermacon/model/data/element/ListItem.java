package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.List;

public class ListItem implements BodyElement {

    public ListItem(List<BodyElement> children) {
        this.children = children;
    }

    private final List<BodyElement> children;

    @Override
    public List<BodyElement> getChildren() {
        return null;
    }

    @Override
    public <E> E accept(TokenVisitor<E> visitor) {
        return visitor.process(this);
    }

}
