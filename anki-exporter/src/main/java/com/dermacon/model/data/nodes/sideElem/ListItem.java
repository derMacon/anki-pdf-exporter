package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.Node;

import java.util.List;

public class ListItem implements Node {

    public ListItem(List<Node> children) {
        this.children = children;
    }

    private final List<Node> children;

    @Override
    public List<Node> getChildren() {
        return null;
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public boolean equals(Object o) {
        ListItem other = null;
        if (o instanceof ListItem) {
            other = (ListItem) o;
        }
        return other != null
                && this.children.equals(other.children);
    }

}
