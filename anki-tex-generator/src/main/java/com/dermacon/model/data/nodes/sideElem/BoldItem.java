package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.visitor.FormatVisitor;

public class BoldItem extends SideContainer {

    public BoldItem(SideContainer elem) {
        super(elem.getChildren());
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
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
