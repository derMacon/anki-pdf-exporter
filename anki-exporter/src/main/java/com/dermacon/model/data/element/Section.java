package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;
import com.dermacon.model.data.visitor.Token;

import java.util.Arrays;
import java.util.List;

public class Section implements Token {

    private final String value;
    private final List<Token> children;

    public Section(String value, Token... children) {
        this.value = value;
        this.children = Arrays.asList(children);
    }

    public String getValue() {
        return value;
    }

    @Override
    public List<Token> getChildren() {
        return children;
    }

    @Override
    public <E> E accept(TokenVisitor<E> visitor) {
        return visitor.process(this);
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

    @Override
    public boolean equals(Object o) {
        Section other = null;
        if (o instanceof Section) {
            other = (Section) o;
        }
        return other != null
                && this.value.equals(other.value)
                && this.children.equals(other.children);
    }

}
