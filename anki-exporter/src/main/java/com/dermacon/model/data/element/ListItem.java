package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.List;

public class ListItem implements Element {

    private final List<Element> children;

    public ListItem(List<Element> children) {
        this.children = children;
    }

    @Override
    public void visit(TokenVisitor<?> visitor) {
        visitor.process(this);
    }
}
