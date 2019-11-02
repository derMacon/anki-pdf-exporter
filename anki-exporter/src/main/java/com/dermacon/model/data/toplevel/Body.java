package com.dermacon.model.data.toplevel;

import com.dermacon.model.data.visitor.TokenVisitor;
import com.dermacon.model.data.visitor.Token;

import java.util.Arrays;
import java.util.List;

public class Body implements Token {

    private final List<Token> elements;

    public Body(Token... elements) {
        this.elements = Arrays.asList(elements);
    }

    @Override
    public List<Token> getChildren() {
        return elements;
    }

    @Override
    public <E> E accept(TokenVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public String toString() {
        String output = "Body{";
        for (Token elem : elements) {
            output += "elem{" + elem.toString() + "}";
        }
        return output + "}";
    }

    @Override
    public boolean equals(Object o) {
        Body other = null;
        if (o instanceof Body) {
            other = (Body)o;
        }
        return other != null
                && this.elements.equals(other.elements);
    }

}
