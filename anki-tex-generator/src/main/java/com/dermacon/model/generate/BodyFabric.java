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

        for (DocNode astCard : ast.getChildren()) {
            Card card = (Card)astCard;
            Section parentNode = findHeading(card.getTag(), output);

            if (parentNode == null) {
                output.addNode(createSection(card));
            } else {
                parentNode.addNode(card);
            }

//            newSection = createSection((Card) node);
//            idxSection = output.getChildren().indexOf(newSection);
//
//            if (idxSection < 0) {
//                output.addNode(newSection);
//            } else {
//                DocNode oldSection = output.getChildren().get(idxSection);
//                assert oldSection instanceof Section;
//                ((Section) oldSection).addNode(node);
//            }

        }

        return output;
    }

    static Section findHeading(List<String> tagHierarchy,
                               DocNode treeNode) {
        if (!((treeNode instanceof Section) || (treeNode instanceof Body))
                || !headingsMatch(treeNode, tagHierarchy.get(0))) {
            return null;
        }

        String inputHeading = tagHierarchy.remove(0);
        Section treeNodeSec = ((Section) treeNode);
        String nodeHeading = treeNodeSec.getValue();
        if (tagHierarchy.size() == 0
                && inputHeading.equals(nodeHeading)) {
            return treeNodeSec;
        }

        Section out = null;
        Iterator<? extends DocNode> it = treeNode.getChildren().iterator();
        while (out == null && it.hasNext()) {
            out = findHeading(deepCopy(tagHierarchy), it.next());
        }
        return out;
    }

    // todo rewrite
    private static boolean headingsMatch(DocNode node, String heading) {
        if (node == null || heading == null || !(node instanceof Section)) {
            return false;
        }
        Section nodeSection = (Section) node;
        return nodeSection.getValue().equals(heading);
    }

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
        List<String> tags = cp(card.getTag().get(0).split("::"));
        return createSection(tags, card);
    }

    // needed in txt parser, when using Arrays.asList(...)
    // removeLastElem won't work
    private static <E> List<E> cp(E... arr) {
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
