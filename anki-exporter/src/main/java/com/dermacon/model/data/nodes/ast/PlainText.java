package com.dermacon.model.data.nodes.ast;

import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.Node;

import java.util.List;

public class PlainText implements Node {

    private String value;

    public PlainText(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
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
    public String toString() {
        return "value:" + value + ";";
    }

    @Override
    public boolean equals(Object o) {
        PlainText other = null;
        if (o instanceof PlainText) {
            other = (PlainText) o;
        }
        return other != null
                && this.value.equals(other.value);
    }

}
