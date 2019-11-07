package com.dermacon.model.data.nodes.ast;

import com.dermacon.model.data.nodes.sideElem.SideElem;

import java.util.List;

public class ASTCard extends ASTNode {
    private SideElem front;
    private SideElem back;

    public ASTCard(SideElem front, SideElem back) {
        this.front = front;
        this.back = back;
    }

    public SideElem getFront() {
        return front;
    }

    public SideElem getBack() {
        return back;
    }

    public void setFront(SideElem front) {
        this.front = front;
    }

    public void setBack(SideElem back) {
        this.back = back;
    }
}
