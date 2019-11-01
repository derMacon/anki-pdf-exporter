package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;

public class PlainText implements BodyElement {

    private String value;

    public PlainText(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void visit(TokenVisitor<?> visitor) {
        visitor.process(this);
    }

    @Override
    public String toString() {
        return "value:" + value;
    }
}
