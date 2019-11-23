package com.dermacon.model.data.nodes.document.headings;

import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.DocNode;

import java.util.Arrays;
import java.util.List;

/**
 * Heading priority:
 * - section
 * - subsection
 * - subsubsection
 * - paragraph
 * - subparagraph
 */
public class Section implements DocNode {

    protected static final String DEFAULT_TITLE = "Generelles";

    protected final String value;
    protected final List<DocNode> children;

    public Section(DocNode... children) {
        this(DEFAULT_TITLE, children);
    }

    public Section(String value, DocNode... children) {
        this(value, Arrays.asList(children));
    }

    public Section(List<DocNode> children) {
        this(DEFAULT_TITLE, children);
    }

    public Section(String value, List<DocNode> children) {
        this.value = value;
        this.children = children;
    }

    public String getValue() {
        return value;
    }

    @Override
    public List<DocNode> getChildren() {
        return children;
    }

    public void addNode(DocNode node) {
        this.children.add(node);
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public String toString() {
        return "section:" + value + ";" + "{" + iterate(children) + "}";
    }

    protected static String iterate(List<?> lst) {
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
