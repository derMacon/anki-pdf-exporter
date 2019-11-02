package com.dermacon.model.data.toplevel;

import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.List;

public class Document implements Token {
    private Header header;
    private Body body;

    public Document(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public Body getBody() {
        return body;
    }

    @Override
    public <E> E accept(TokenVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public List<Token> getChildren() {
        return null;
    }

    @Override
    public String toString() {
        return "Document{"
                + header.toString()
                + body.toString()
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        Document other = null;
        if (o instanceof Document) {
            other = (Document)o;
        }
        return other != null
                && this.header.equals(other.header)
                && this.body.equals(other.body);
    }

}
