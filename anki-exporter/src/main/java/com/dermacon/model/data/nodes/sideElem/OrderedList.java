package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.Node;

import java.util.List;

public class OrderedList extends SideElem {

    private final List<ListItem> children;

    public OrderedList(List<ListItem> children) {
        this.children = children;
    }

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
        OrderedList other = null;
        if (o instanceof OrderedList) {
            other = (OrderedList)o;
        }
        return other != null
                && this.children.equals(other.children);
    }

}
