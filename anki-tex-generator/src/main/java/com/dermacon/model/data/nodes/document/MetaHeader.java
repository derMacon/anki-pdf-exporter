package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.List;

/**
 * Header containing infos for the title page of the document.
 */
public class MetaHeader implements DocNode {

    private final String title;

    public MetaHeader(String title) {
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
    public List<DocNode> getChildren() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        MetaHeader other = null;
        if (o instanceof MetaHeader) {
            other = (MetaHeader)o;
        }
        return other != null
                && this.title.equals(other.title);
    }
}
