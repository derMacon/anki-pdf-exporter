package com.dermacon.model.data.nodes.document.headings;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.visitor.FormatVisitor;

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
public class SubSection extends Section {

    public SubSection(String value, DocNode... children) {
        super(value, children);
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public String toString() {
        return "subsection:" + value + ";" + "{" + iterate(children) + "}";
    }

    @Override
    public boolean equals(Object o) {
        SubSection other = null;
        if (o instanceof SubSection) {
            other = (SubSection) o;
        }
        return other != null
                && this.value.equals(other.value)
                && this.children.equals(other.children);
    }

}
