package com.dermacon.model.data.nodes.sideElem;

public class RecursiveItem extends SideContainer {

    public RecursiveItem(SideContainer elem) {
        super(elem.getChildren());
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
