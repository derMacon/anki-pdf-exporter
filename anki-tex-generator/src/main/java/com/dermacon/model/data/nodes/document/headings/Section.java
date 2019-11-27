package com.dermacon.model.data.nodes.document.headings;

import com.dermacon.model.data.visitor.FormatVisitor;
import com.dermacon.model.data.nodes.DocNode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Heading priority:
 * - section
 * - subsection
 * - subsubsection
 * - paragraph
 * - subparagraph
 */
public class Section implements DocNode  {

    protected static final String DEFAULT_TITLE = "Generelles";

    protected final String value;
    protected final List<DocNode> children;

    public Section(DocNode... children) {
        this(DEFAULT_TITLE, cp(children));
    }

    public Section(String value, DocNode... children) {
        this(value, cp(children));
    }

    public Section(List<DocNode> children) {
        this(DEFAULT_TITLE, children);
    }

    public Section(String value, List<DocNode> children) {
        this.children = children;
        this.value = value.isEmpty() ? DEFAULT_TITLE : value;
    }

    // need in txt parser, when using Arrays.asList(...)
    // removeLastElem won't work
    private static <E> List<E> cp(E... arr) {
        List<E> out = new LinkedList<>();
        for (E e : arr) {
            out.add(e);
        }
        return out;
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

    public Section getFstSubSection() {
       for (DocNode curr : children) {
           if (curr instanceof Section) {
               return (Section) curr;
           }
       }
       return null;
    }


//    /**
//     * Only check if sub sections match, all other classes irrelevant
//     * @param o
//     * @return
//     */
//    public boolean headingsMatch(Object o) {
//        if (!(o instanceof Section)) {
//            return false;
//        }
//
//        Section other = (Section) o;
//        boolean matchingSubHeadings = true;
//        int i = 0;
//        Iterator<DocNode> it = other.children.iterator();
//        while(matchingSubHeadings && it.hasNext()) {
//            matchingSubHeadings = headingsMatch(it.next());
//            i++;
//        }
//
//        return other != null
//                && this.value.equals(other.value)
//                && this.children.equals(other.children);
//
//    }

    @Override
    public boolean equals(Object o) {
        Section other = null;
        if (o instanceof Section) {
            other = (Section) o;
        }

        // only check if sub sections match, all other classes irrelevant




        return other != null
                && this.value.equals(other.value)
                && this.children.equals(other.children);
    }

    public Iterable<Section> headingIterator() {
        return new Iterable<Section>() {
            @Override
            public Iterator<Section> iterator() {
                return new Iterator<Section>() {
                    private Iterator<Section> directSubSections =
                            children.stream()
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
        };
    }
}
