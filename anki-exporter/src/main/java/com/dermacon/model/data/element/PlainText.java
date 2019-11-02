package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.List;

public class PlainText implements BodyElement {

    private String value;

    public PlainText(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public List<BodyElement> getChildren() {
        return null;
    }

    @Override
    public <E> E accept(TokenVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public String toString() {
        return "value:" + value + ";";
    }

}
