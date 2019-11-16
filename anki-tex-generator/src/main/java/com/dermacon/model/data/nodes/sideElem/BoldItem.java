package com.dermacon.model.data.nodes.sideElem;

public class BoldItem extends SideContainer {

    public BoldItem(SideContainer elem) {
        super(elem.getChildren());
    }

    @Override
    public boolean equals(Object o) {
        BoldItem other = null;
        if (o instanceof BoldItem) {
            other = (BoldItem) o;
        }
        return other != null
                && this.elems.equals(other.elems);
    }

    @Override
    public String toString() {
        return "bold(" + this.elems.toString() + ")";
    }

}
