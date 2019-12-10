package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.DocNode;

import java.util.List;

public class UnorderedList extends SideElem {

    private final List<ListItem> children;

    public UnorderedList(List<ListItem> children) {
        this.children = children;
    }

    @Override
    public List<DocNode> getChildren() {
        return null;
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public boolean equals(Object o) {
        UnorderedList other = null;
        if (o instanceof UnorderedList) {
            other = (UnorderedList) o;
        }
        return other != null
                && this.children.equals(other.children);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("ul(");
        for (ListItem item : children) {
            out.append(item);
        }
        return out.toString() + ")";
    }
}
