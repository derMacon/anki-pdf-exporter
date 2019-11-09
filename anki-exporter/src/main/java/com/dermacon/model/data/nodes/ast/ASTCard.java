package com.dermacon.model.data.nodes.ast;

import com.dermacon.model.data.nodes.sideElem.SideContainer;

public class ASTCard extends ASTNode {
    private SideContainer front;
    private SideContainer back;

    public ASTCard(SideContainer front, SideContainer back) {
        this.front = front;
        this.back = back;
    }

    public SideContainer getFront() {
        return front;
    }

    public SideContainer getBack() {
        return back;
    }

    public void setFront(SideContainer front) {
        this.front = front;
    }

    public void setBack(SideContainer back) {
        this.back = back;
    }

    @Override
    public boolean equals(Object o) {
        ASTCard other = null;
        if (o instanceof ASTCard) {
            other = (ASTCard)o;
        }
        return other != null
                && this.front.equals(other.front)
                && this.back.equals(other.back);
    }

    @Override
    public String toString() {
        return "ASTCard(front:" + this.front.toString()
                + ";back:" + this.back.toString() + ")";
    }
}
