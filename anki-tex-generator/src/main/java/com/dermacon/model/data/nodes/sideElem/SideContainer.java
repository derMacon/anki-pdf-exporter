package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.LinkedList;
import java.util.List;

public class SideContainer extends SideElem {
    protected List<SideElem> elems = new LinkedList<>();

    public SideContainer() {
        this(new LinkedList<>());
    }

    public SideContainer(List<SideElem> elems) {
        if (elems != null) {
            this.elems = elems;
        }
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        assert false : "double dispatch - sub classes have to implement this " +
                "themselves";
        return visitor.process(this);
    }

    @Override
    public List<SideElem> getChildren() {
        return this.elems;
    }

    @Override
    public boolean equals(Object o) {
        SideContainer other = null;
        if (o instanceof SideContainer) {
            other = (SideContainer) o;
        }
        return other != null
                && this.elems.equals(other.elems);
    }

    @Override
    public String toString() {
        return "sideElemContainer(" + this.elems.toString() + ")";
    }
}
