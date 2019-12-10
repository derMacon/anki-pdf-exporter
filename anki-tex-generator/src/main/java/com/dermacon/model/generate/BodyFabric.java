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

    /**
     * Inserts a given card node into a given body node. The whole point is
     * that the card nodes should be inserted into the right section of the
     * latex document. Those sections directly correspond with the first tag
     * of the card.
     *
     * @param body node to which the card should be appended.
     * @param card node that should be inserted
     */
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

    /**
     * Checks if two heading match. Only checks the actual headings not the
     * children from the two nodes.
     * @param fst first heading to check
     * @param snd second heading to check
     * @return true if the heading match.
     */
    private static boolean headingMatches(Section fst, Section snd) {
        return !(fst == null && snd == null)
                && fst.getValue().equals(snd.getValue());
    }


    /**
     * Creates a section for a given card. The generated section
     * contains the Card node.
     * @param card card to generate a section for.
     * @return section object containing the card node.
     */
    private static Section createSection(Card card) {
        List<String> tags = cp(card.getTag().get(0));
        return createSection(tags, card);
    }


    /**
     * Makes a deep copy of a given list and returns it.
     * needed in txt parser, when using Arrays.asList(...)
     * removeLastElem won't work
     * @param lst original list to copy
     * @param <E> Generic Type of the input / output elements of the list.
     * @return Deep copy of the given list.
     */
    private static <E> List<E> cp(List<E> lst) {
        List<E> out = new LinkedList<>();
        for (E e : lst) {
            out.add(e);
        }
        return out;
    }

    /**
     * Creates a top level section node element.
     * @param tags tag hierarchy to which a section should be generated.
     *             E.g. tags = [fstTag, sndTag]
     *             => new Section(fstTag, new SubSection(sndTag, node));
     * @param node node to insert in the deepest section element.
     * @return Section element containing the described node hierarchy.
     */
    private static Section createSection(List<String> tags, DocNode node) {
        assert !tags.isEmpty();
        String heading = removeFstElem(tags);
        return new Section(heading, wrapSubSection(tags, node));
    }

    /**
     * See createSection.
     * @param tags tag hierarchy to which a section should be generated.
     *             E.g. tags = [fstTag, sndTag]
     *             => new Section(fstTag, new SubSection(sndTag, node));
     * @param node node to insert in the deepest section element.
     * @return SubSection element containing the described node hierarchy.
     */
    private static DocNode wrapSubSection(List<String> tags, DocNode node) {
        DocNode output = node;
        if (!tags.isEmpty()) {
            String heading = removeFstElem(tags);
            DocNode innerContent = wrapSubSubSection(tags, node);
            output = new SubSection(heading, innerContent);
        }
        return output;
    }

    /**
     * See createSection
     * @param tags
     * @param node
     * @return
     */
    private static DocNode wrapSubSubSection(List<String> tags, DocNode node) {
        DocNode output = node;
        if (!tags.isEmpty()) {
            String heading = removeFstElem(tags);
            DocNode innerContent = wrapParagraph(tags, node);
            output = new Paragraph(heading, innerContent);
        }
        return output;
    }

    /**
     * See createSection
     * @param tags
     * @param node
     * @return
     */
    private static DocNode wrapParagraph(List<String> tags, DocNode node) {
        DocNode output = node;
        if (!tags.isEmpty()) {
            String heading = removeFstElem(tags);
            DocNode innerContent = wrapSubParagraph(tags, node);
            output = new SubSection(heading, innerContent);
        }
        return output;
    }

    /**
     * See createSection
     * @param tags
     * @param node
     * @return
     */
    private static DocNode wrapSubParagraph(List<String> tags, DocNode node) {
        DocNode output = node;
        if (!tags.isEmpty()) {
            String heading = removeFstElem(tags);
            output = new SubSection(heading, node);
        }
        return output;
    }

    /**
     * Removes the first element of the list if the list is not empty. The
     * removed element will be returned.
     * @param lst list to edit.
     * @param <E> Generic type of the list elements.
     * @return Removed element.
     */
    static <E> E removeFstElem(List<E> lst) {
        return lst.isEmpty() ? null : lst.remove(0);
    }
}
