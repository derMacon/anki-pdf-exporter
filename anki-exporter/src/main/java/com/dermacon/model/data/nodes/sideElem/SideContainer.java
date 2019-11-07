package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.nodes.Node;
import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.LinkedList;
import java.util.List;

public class SideContainer extends SideElem {
    private List<SideElem> elems;

    public SideContainer(List<SideElem> elems) {
        this.elems = elems;
    }

    public List<SideElem> getElems() {
        return elems;
    }

    public void setElems(List<SideElem> elems) {
        this.elems = elems;
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
