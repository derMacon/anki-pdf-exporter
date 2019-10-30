package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

public class Section implements Element {

    @Override
    public void visit(TokenVisitor<?> visitor) {
        visitor.process(this);
    }

}
