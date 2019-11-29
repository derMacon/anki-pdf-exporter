package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.document.headings.Paragraph;
import com.dermacon.model.data.nodes.document.headings.Section;
import com.dermacon.model.data.nodes.document.headings.SubSection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BodyFabric {

    /**
     * Sorts cards from ast after their respective tags and generates a body
     * element instance.
     *
     * @param ast abstract syntax tree containing all necessary card elements.
     * @return body element containing all sorted elements
     */
    static Body createDocBody(ASTStack ast) {
        // stacks direct children NEED to be cards
        assert ast.getChildren().stream().allMatch(e -> e instanceof Card);
        Body output = new Body();

        ast.getChildren().stream()
                .filter(e -> e instanceof Card)
                .map(e -> (Card) e)
                .forEach(c -> appendBody(output, c));

        return output;
    }

    static void appendBody(Body body, Card card) {
        Section appendingSection = createSection(card);

        Iterator<Section> it = body.headingIterator();

        boolean foundMatch = false;

        while (!foundMatch && it.hasNext()) {
            foundMatch = append(appendingSection, it.next());
        }

        if (!foundMatch) {
            body.addNode(appendingSection);
        }
    }

    /**
     * -
     *
     * @param appendingSection
     * @param treeNodeSection
     * @return
     */
    static boolean append(Section appendingSection,
                          Section treeNodeSection) {
        if (appendingSection == null
                || !headingMatches(treeNodeSection, appendingSection)) {
            return false;
        }

        Section sectionCopy = appendingSection.deepCopy();
        Iterator<Section> it = treeNodeSection.headingIterator();
        boolean appendedNode = false;
        Section nextSection = sectionCopy.getFstSubSection();

        while (!appendedNode && it.hasNext()) {
            appendedNode = append(nextSection, it.next());
        }

        if (!appendedNode) {
            DocNode temp = nextSection == null ?
                    sectionCopy.getChildren().get(0) :
                    nextSection;
            treeNodeSection.addNode(temp);
            appendedNode = true;
        }

        return appendedNode;
    }

    private static boolean headingMatches(Section fst, Section snd) {
        return !(fst == null && snd == null)
                && fst.getValue().equals(snd.getValue());
    }


    private static List<String> deepCopy(List<String> in) {
        List<String> out = new LinkedList<>();
        for (String s : in) {
            out.add(s);
        }
        return out;
    }

    private static Section createSection(Card card) {
        List<String> tags = cp(card.getTag().get(0));
        return createSection(tags, card);
    }

    // needed in txt parser, when using Arrays.asList(...)
    // removeLastElem won't work
    private static <E> List<E> cp(List<E> arr) {
        List<E> out = new LinkedList<>();
        for (E e : arr) {
            out.add(e);
        }
        return out;
    }

    private static Section createSection(List<String> tags, DocNode node) {
        assert !tags.isEmpty();
        String heading = removeFstElem(tags);
        return new Section(heading, wrapSubSection(tags, node));
    }

    private static DocNode wrapSubSection(List<String> tags, DocNode node) {
        DocNode output = node;
        if (!tags.isEmpty()) {
            String heading = removeFstElem(tags);
            DocNode innerContent = wrapSubSubSection(tags, node);
            output = new SubSection(heading, innerContent);
        }
        return output;
    }

    private static DocNode wrapSubSubSection(List<String> tags, DocNode node) {
        DocNode output = node;
        if (!tags.isEmpty()) {
            String heading = removeFstElem(tags);
            DocNode innerContent = wrapParagraph(tags, node);
            output = new Paragraph(heading, innerContent);
        }
        return output;
    }

    private static DocNode wrapParagraph(List<String> tags, DocNode node) {
        DocNode output = node;
        if (!tags.isEmpty()) {
            String heading = removeFstElem(tags);
            DocNode innerContent = wrapSubParagraph(tags, node);
            output = new SubSection(heading, innerContent);
        }
        return output;
    }

    private static DocNode wrapSubParagraph(List<String> tags, DocNode node) {
        DocNode output = node;
        if (!tags.isEmpty()) {
            String heading = removeFstElem(tags);
            output = new SubSection(heading, node);
        }
        return output;
    }


    static <E> E removeFstElem(List<E> lst) {
        return lst.isEmpty() ? null : lst.remove(0);
    }
}
