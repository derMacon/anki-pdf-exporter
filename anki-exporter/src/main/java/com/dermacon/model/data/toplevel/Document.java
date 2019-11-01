package com.dermacon.model.data.toplevel;

import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

public class Document implements Token {
    private Header header;
    private Body body;

    public Document(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

    @Override
    public void visit(TokenVisitor<?> visitor) {
        body.visit(visitor);
        header.visit(visitor);
        visitor.process(this);
    }

    @Override
    public String toString() {
        return "Document{"
                + header.toString()
                + body.toString()
                + "}";
    }
}
