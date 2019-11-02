package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.List;

public class OrderedList implements BodyElement {

    private final List<ListItem> children;

    public OrderedList(List<ListItem> children) {
        this.children = children;
    }

    @Override
    public List<BodyElement> getChildren() {
        return null;
    }

    @Override
    public <E> E accept(TokenVisitor<E> visitor) {
        return visitor.process(this);
    }

}
