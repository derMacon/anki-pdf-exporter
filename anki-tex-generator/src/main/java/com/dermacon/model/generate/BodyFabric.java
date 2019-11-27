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


//    static Section appendSection(Section treeSection, Section sec) {
//        Section match = null;
//        if (treeSection.equals(sec)) {
//            Iterator<? extends DocNode> childIterator = treeSection.getChildren().iterator();
//            DocNode curr;
//            while (match == null && childIterator.hasNext()) {
//                curr = childIterator.next();
//                if (headingMatches(curr, sec.getValue())) {
//                    match = (Section) curr;
//                }
//            }
//
//            if (match == null) {
//                treeSection.addNode(sec);
//            } else {
//                appendSection(match, sec.getFstSubSection());
//            }
//        }
//        return match;
//    }


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

    // todo rewrite
//    private static boolean headingMatches(DocNode node, String heading) {
//        if (node != null && node instanceof Body) {
//            return true;
//        }
//
//        if (node == null || heading == null
//                || !(node instanceof Section)) {
//            return false;
//        }
//        Section nodeSection = (Section) node;
//        return nodeSection.getValue().equals(heading);
//    }

//    private static Section getNextSection(Section in) {
//        Iterator<Section> it = in.headingIterator().iterator();
//        if (it.hasNext()) {
//            return it.next();
//        } else {
//            return null;
//        }
//    }
//
    private static List<String> deepCopy(List<String> in) {
        List<String> out = new LinkedList<>();
        for (String s : in) {
            out.add(s);
        }
        return out;
    }

//    private static boolean appendBody(Card inputCard, DocNode treeNode) {
//        if (inputCard.getTag().isEmpty()) {
//            treeNode.getChildren().add(inputCard);
//            return true;
//        }
//        String toplevelHeading = inputCard.getTag().remove(0);
//        if (toplevelHeading == null
//                || !(treeNode instanceof Section)
//                || !toplevelHeading.equals(((Section) treeNode).getValue())
//
//        ) {
//            return false;
//        }
//
//        List<? extends DocNode> children = treeNode.getChildren();
//        int i = 0;
//        boolean nodeHasBeenPlaced = false;
//        while (i < children.size() && !nodeHasBeenPlaced) {
//            nodeHasBeenPlaced = appendBody(inputCard, children.get(i));
//            i++;
//        }
//
//    }

    private static boolean isParent(Section inputSection, Body body) {
        List<DocNode> sections = body.getChildren();
        for (DocNode treeSection : sections) {

        }
        return false;
    }

    private static DocNode findParent(Section section, DocNode node) {
        DocNode parentSection = null;

        if (node instanceof Section) {
            Section treeSection = (Section) node;
            // section title conform
            if (section.getValue().equals(treeSection.getValue())) {
                parentSection = treeSection;
                // check children
                for (DocNode subNode : treeSection.getChildren()) {
//                    DocNode temp = findParent()
                }
            }
        }
        return parentSection;
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
        // todo
//        E out = lst.isEmpty() ? null : lst.get(lst.size() - 1);
//        int newLastIdx = lst.isEmpty() ? 0 : lst.size() - 1;
//        lst.subList(0, newLastIdx).clear();
//        return out;

        return lst.isEmpty() ? null : lst.remove(0);

    }
}
