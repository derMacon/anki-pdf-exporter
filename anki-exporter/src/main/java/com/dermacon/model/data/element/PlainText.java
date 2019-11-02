package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;
import com.dermacon.model.data.visitor.Token;

import java.util.List;

public class PlainText implements Token {

    private String value;

    public PlainText(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public List<Token> getChildren() {
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

    @Override
    public boolean equals(Object o) {
        PlainText other = null;
        if (o instanceof PlainText) {
            other = (PlainText) o;
        }
        return other != null
                && this.value.equals(other.value);
    }

}
