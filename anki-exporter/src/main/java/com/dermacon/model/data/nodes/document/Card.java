package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.nodes.sideElem.SideContainer;
import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.Node;

import java.util.Arrays;
import java.util.List;

public class Card implements Node {

    private final SideContainer front;
    private final SideContainer back;

    public Card(SideContainer front, SideContainer back) {
        this.front = front;
        this.back = back;
    }

    public SideContainer getFront() {
        return front;
    }

    public SideContainer getBack() {
        return back;
    }

    @Override
    public String toString() {
        return "Card(front:" + this.front.toString()
                + ";back:" + this.back.toString() + ")";
    }

    @Override
    public List<Node> getChildren() {
        return null;
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public boolean equals(Object o) {
        Card other = null;
        if (o instanceof Card) {
            other = (Card)o;
        }
        return other != null
                && this.front.equals(other.front)
                && this.back.equals(other.back);
    }
}
