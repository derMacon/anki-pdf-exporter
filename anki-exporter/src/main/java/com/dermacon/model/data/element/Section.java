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

    public List<BodyElement> getChildren() {
        return children;
    }

    @Override
    public void visit(TokenVisitor<?> visitor) {
        for (BodyElement elem : children) {
            elem.visit(visitor);
        }
        visitor.process(this);
    }

    @Override
    public String toString() {
        return "section:" + value + ";" + "{" + iterate(children) + "}";
    }

    private static String iterate(List<?> lst) {
        String out = "";
        for (Object o : lst) {
            out += o.toString();
        }
        return out;
    }
}