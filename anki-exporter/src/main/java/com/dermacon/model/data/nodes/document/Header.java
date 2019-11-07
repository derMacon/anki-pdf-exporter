package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.nodes.Node;
import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.List;

/**
 * Header containing infos for the title page of the document.
 */
public class Header implements Node {

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
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public List<Node> getChildren() {
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
