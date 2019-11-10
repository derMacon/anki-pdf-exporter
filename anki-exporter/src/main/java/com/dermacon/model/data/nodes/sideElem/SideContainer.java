package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.List;

public class SideContainer extends SideElem {
    protected List<SideElem> elems;

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
