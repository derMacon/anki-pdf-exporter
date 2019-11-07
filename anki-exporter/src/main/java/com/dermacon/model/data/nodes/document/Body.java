package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.Node;

import java.util.Arrays;
import java.util.List;

public class Body implements Node {

    private final List<Node> elements;

    public Body(Node... elements) {
        this.elements = Arrays.asList(elements);
    }

    @Override
    public List<Node> getChildren() {
        return elements;
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public String toString() {
        String output = "Body{";
        for (Node elem : elements) {
            output += "elem{" + elem.toString() + "}";
        }
        return output + "}";
    }

    @Override
    public boolean equals(Object o) {
        Body other = null;
        if (o instanceof Body) {
            other = (Body)o;
        }
        return other != null
                && this.elements.equals(other.elements);
    }

}
