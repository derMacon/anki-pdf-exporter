package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;

public class OrderedList implements Element {

    @Override
    public void visit(TokenVisitor<?> visitor) {
        visitor.process(this);
    }
}
