package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.document.headings.Paragraph;
import com.dermacon.model.data.nodes.document.headings.Section;
import com.dermacon.model.data.nodes.document.headings.SubSection;

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

        Section newSection;
        int idxSection;
        for (DocNode node : ast.getChildren()) {
            newSection = createSection((Card)node);
            idxSection = output.getChildren().indexOf(newSection);

            if (idxSection < 0) {
                output.addNode(newSection);
            } else {
                DocNode oldSection = output.getChildren().get(idxSection);
                assert oldSection instanceof Section;
                ((Section)oldSection).addNode(node);
            }

        }

        return output;
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
