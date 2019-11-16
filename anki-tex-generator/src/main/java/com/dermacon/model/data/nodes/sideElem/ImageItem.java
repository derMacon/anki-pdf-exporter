package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.List;

public class ImageItem extends SideElem {

    private final String name;

    public ImageItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public List<? extends DocNode> getChildren() {
        System.err.println("plain text has no further children");
        return null;
    }

    @Override
    public boolean equals(Object o) {
        ImageItem other = null;
        if (o instanceof ImageItem) {
            other = (ImageItem) o;
        }
        return other != null
                && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "img(" + this.name + ")";
    }
}
