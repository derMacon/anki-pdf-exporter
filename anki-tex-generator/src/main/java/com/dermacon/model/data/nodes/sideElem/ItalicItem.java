package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.visitor.FormatVisitor;

public class ItalicItem extends SideContainer {

    public ItalicItem(SideContainer elem) {
        super(elem.getChildren());
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }


    @Override
    public boolean equals(Object o) {
        ItalicItem other = null;
        if (o instanceof ItalicItem) {
            other = (ItalicItem) o;
        }
        return other != null
                && this.elems.equals(other.elems);
    }

    @Override
    public String toString() {
        return "recursive(" + this.elems.toString() + ")";
    }

}
