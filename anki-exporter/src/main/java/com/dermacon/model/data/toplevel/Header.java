package com.dermacon.model.data.toplevel;

import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

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
}
