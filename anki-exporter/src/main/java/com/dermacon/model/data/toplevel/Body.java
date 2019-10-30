package com.dermacon.model.data.toplevel;

import com.dermacon.model.data.element.Element;
import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.List;

public class Body implements Token {

    private List<Element> element;

    @Override
    public void visit(TokenVisitor<?> visitor) {

    }
}
