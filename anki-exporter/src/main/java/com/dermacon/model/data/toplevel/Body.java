package com.dermacon.model.data.toplevel;

import com.dermacon.model.data.element.BodyElement;
import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.Arrays;
import java.util.List;

public class Body implements Token {

    private final List<BodyElement> elements;

    public Body(BodyElement... elements) {
        this.elements = Arrays.asList(elements);
    }

    @Override
    public void visit(TokenVisitor<?> visitor) {
        for (BodyElement elem : elements) {
            elem.visit(visitor);
        }
    }

    @Override
    public String toString() {
        String output = "Body{";
        for (BodyElement elem : elements) {
            output += "elem{" + elem.toString() + "}";
        }
        return output + "}";
    }
}
