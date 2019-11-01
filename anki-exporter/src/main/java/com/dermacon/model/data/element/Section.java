package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.Arrays;
import java.util.List;

public class Section implements BodyElement {

    private final String value;
    private final List<BodyElement> children;

    public Section(String value, BodyElement... children) {
        this.value = value;
        this.children = Arrays.asList(children);
    }

    public String getValue() {
        return value;
    }

    @Override
    public void visit(TokenVisitor<?> visitor) {
        for (BodyElement elem : children) {
            elem.visit(visitor);
        }
        visitor.process(this);
    }

}
