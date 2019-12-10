package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.nodes.document.headings.Section;
import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.DocNode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Body element of the document object. Contains all relevant card
 * information / card stack.
 */
public class Body implements DocNode {

    private List<DocNode> elements;

    public Body(DocNode... elements) {
        this(Arrays.asList(elements));
    }

    public Body(List<DocNode> elements) {
        this.elements = elements.isEmpty() ?  new LinkedList<>() : elements;
    }

    @Override
    public List<DocNode> getChildren() {
        return elements;
    }

    public void addNode(DocNode node) {
        this.elements.add(node);
    }

    /**
     * Generates an iterator which in turn iterate over the different Section
     * elements in the elements children.
     * @return
     */
    public Iterator<Section> headingIterator() {
        return new Iterator<Section>() {
            private Iterator<Section> directSubSections =
                    elements.stream()
                            .filter(e -> e instanceof Section)
                            .map(e -> (Section)e)
                            .collect(Collectors.toList()).iterator();

            @Override
            public boolean hasNext() {
                return directSubSections.hasNext();
            }

            @Override
            public Section next() {
                return directSubSections.next();
            }
        };
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public String toString() {
        String output = "Body{";
        for (DocNode elem : elements) {
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
