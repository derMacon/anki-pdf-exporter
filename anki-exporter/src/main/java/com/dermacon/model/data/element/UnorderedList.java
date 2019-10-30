package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.List;

public class UnorderedList implements Element {

    private List<Element> items;

    public UnorderedList(List<Element> items) {
        this.items = items;
    }

    @Override
    public void visit(TokenVisitor<?> visitor) {
        for (Element elem : items) {
            elem.visit(visitor);
        }
    }
}
