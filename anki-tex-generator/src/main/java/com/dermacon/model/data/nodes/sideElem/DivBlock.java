package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.List;

public class DivBlock extends SideContainer {

    public DivBlock(SideContainer container) {
        super(container.getChildren());
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
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
        return "div(" + this.elems.toString() + ")";
    }
}
