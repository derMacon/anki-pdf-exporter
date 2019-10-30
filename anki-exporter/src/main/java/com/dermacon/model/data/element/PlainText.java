package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

public class PlainText implements Element {

    private String value;

    public PlainText(String value) {
        this.value = value;
    }

    @Override
    public void visit(TokenVisitor<?> visitor) {
        visitor.process(this);
    }
}
