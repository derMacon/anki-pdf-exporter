package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.List;

public class OrderedList implements BodyElement {

    private final List<ListItem> children;

    public OrderedList(List<ListItem> children) {
        this.children = children;
    }

    @Override
    public void visit(TokenVisitor<?> visitor) {
        for (ListItem elem : children) {
            elem.visit(visitor);
        }
        visitor.process(this);
    }
}
