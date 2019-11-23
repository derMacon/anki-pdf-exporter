package com.dermacon.model.data.nodes.document.headings;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.visitor.FormatVisitor;

/**
 * Heading priority:
 * - section
 * - subsection
 * - subsubsection
 * - paragraph
 * - subparagraph
 */
public class SubSubSection extends SubSection {

    public SubSubSection(String value, DocNode... children) {
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
        SubSubSection other = null;
        if (o instanceof SubSubSection) {
            other = (SubSubSection) o;
        }
        return other != null
                // only cares for the section heading, not for its content
                && this.value.equals(other.value);
    }

}
