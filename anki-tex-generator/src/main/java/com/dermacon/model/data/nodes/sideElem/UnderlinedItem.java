package com.dermacon.model.data.nodes.sideElem;

import java.util.List;

public class UnderlinedItem extends SideContainer {

    public UnderlinedItem(SideContainer container) {
        super(container.getChildren());
    }

    @Override
    public boolean equals(Object o) {
        UnderlinedItem other = null;
        if (o instanceof UnderlinedItem) {
            other = (UnderlinedItem) o;
        }
        return other != null
                && this.elems.equals(other.elems);
    }

    @Override
    public String toString() {
        return "underlined(" + this.elems.toString() + ")";
    }

}
