package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.Node;

import java.util.Arrays;
import java.util.List;

public class Section implements Node {

    private static final String DEFAULT_TITLE = "Generelles";

    private final String value;
    private final List<Node> children;

    public Section(Node... children) {
        this(DEFAULT_TITLE, children);
    }

    public Section(String value, Node... children) {
        this.value = value;
        this.children = Arrays.asList(children);
    }

    public String getValue() {
        return value;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public String toString() {
        return "section:" + value + ";" + "{" + iterate(children) + "}";
    }

    private static String iterate(List<?> lst) {
        String out = "";
        for (Object o : lst) {
            out += o.toString();
        }
        return out;
    }

    @Override
    public boolean equals(Object o) {
        Section other = null;
        if (o instanceof Section) {
            other = (Section) o;
        }
        return other != null
                && this.value.equals(other.value)
                && this.children.equals(other.children);
    }

}
