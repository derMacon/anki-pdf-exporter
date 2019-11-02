package com.dermacon.model.data.toplevel;

import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.List;

/**
 * Header containing infos for the title page of the document.
 */
public class Header implements Token {

    private final String title;

    public Header(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Header{title:" + title + "}";
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
    public boolean equals(Object o) {
        Header other = null;
        if (o instanceof Header) {
            other = (Header)o;
        }
        return other != null
                && this.title.equals(other.title);
    }
}
