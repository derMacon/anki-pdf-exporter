package com.dermacon.model.data.nodes.sideElem;

import java.util.List;

public class DivBlock extends SideContainer {

    public DivBlock(SideContainer container) {
        super(container.getChildren());
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
