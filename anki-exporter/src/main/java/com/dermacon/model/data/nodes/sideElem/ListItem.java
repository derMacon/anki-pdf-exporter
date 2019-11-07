package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.Node;

import java.util.List;

public class ListItem extends SideElem {

    private SideContainer container;

    public ListItem(SideContainer container) {
        this.container = container;
    }

    public SideContainer getContainer() {
        return container;
    }

    public void setContainer(SideContainer container) {
        this.container = container;
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return null;
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }
}
