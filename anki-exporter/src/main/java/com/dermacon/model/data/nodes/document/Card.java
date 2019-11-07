package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.Node;

import java.util.Arrays;
import java.util.List;

public class Card implements Node {

    private final List<Node> front;
    private final List<Node> back;

    public Card(Node[] front, Node[] back) {
        this.front = Arrays.asList(front);
        this.back = Arrays.asList(back);
    }

    public List<Node> getFront() {
        return front;
    }

    public List<Node> getBack() {
        return back;
    }

    @Override
    public String toString() {
        return "card{front:{" + iterate(front) + "}"
            + "back:{" + iterate(front) + "}}";
    }

    private static String iterate(List<?> lst) {
        String out = "";
        for (Object o : lst) {
            out += o.toString();
        }
        return out;
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
