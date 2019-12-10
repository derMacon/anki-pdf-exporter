package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.nodes.sideElem.SideContainer;
import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.DocNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Card object that contains a front and backside full of Side elements.
 * Also holds a list for all specified tags
 */
public class Card implements DocNode {

    private final SideContainer front;
    private final SideContainer back;
    private final List<List<String>> tag;

    public Card(SideContainer front, SideContainer back) {
        this(front, back, "");
    }

    public Card(SideContainer front, SideContainer back, String tags) {
        this.front = front;
        this.back = back;
        this.tag = new LinkedList<>();
        for (String tag : tags.replaceAll("_", "").split(" ")) {
            List<String> hierarchy = new LinkedList<>();
            for (String subTag : tag.split("::")) {
                hierarchy.add(subTag);
            }
            this.tag.add(hierarchy);
        }
    }

    public SideContainer getFront() {
        return front;
    }

    public SideContainer getBack() {
        return back;
    }

    public List<List<String>> getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "Card(front:" + this.front.toString()
                + ";back:" + this.back.toString() + ");"
                + "tags:" + this.tag.toString();
    }

    @Override
    public List<DocNode> getChildren() {
        // todo
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
