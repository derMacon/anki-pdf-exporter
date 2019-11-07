package com.dermacon.model.data.nodes.ast;

import com.dermacon.model.data.nodes.sideElem.SideElem;

import java.util.List;

public class ASTCard extends ASTNode {
    private List<SideElem> front;
    private List<SideElem> back;

    public ASTCard(List<SideElem> front, List<SideElem> back) {
        this.front = front;
        this.back = back;
    }

    public List<SideElem> getFront() {
        return front;
    }

    public List<SideElem> getBack() {
        return back;
    }

    public void setFront(List<SideElem> front) {
        this.front = front;
    }

    public void setBack(List<SideElem> back) {
        this.back = back;
    }
}
