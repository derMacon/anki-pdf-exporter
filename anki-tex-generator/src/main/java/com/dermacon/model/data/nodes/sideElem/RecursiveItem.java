package com.dermacon.model.data.nodes.sideElem;

import java.util.List;

public class RecursiveItem extends SideContainer {
    public RecursiveItem(List<SideElem> elems) {
        super(elems);
    }

    @Override
    public boolean equals(Object o) {
        RecursiveItem other = null;
        if (o instanceof RecursiveItem) {
            other = (RecursiveItem) o;
        }
        return other != null
                && this.elems.equals(other.elems);
    }

    @Override
    public String toString() {
        return "recursive(" + this.elems.toString() + ")";
    }

}
