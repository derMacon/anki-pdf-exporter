package com.dermacon.model.generate;

import com.dermacon.antlr.CardStackLexer;
import com.dermacon.antlr.CardStackParser;
import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.DocumentBuilder;
import com.dermacon.model.data.nodes.document.headings.Paragraph;
import com.dermacon.model.data.nodes.document.headings.Section;
import com.dermacon.model.data.nodes.document.headings.SubParagraph;
import com.dermacon.model.data.nodes.document.headings.SubSection;
import com.dermacon.model.data.nodes.document.headings.SubSubSection;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Arrays;
import java.util.List;

public class TXTParser implements Parser {

    private final String mediaPath;
    private final String deckName;

    public TXTParser(String mediaPath, String deckName) {
        this.mediaPath = mediaPath;
        this.deckName = deckName;
    }

    @Override
    public Document parse(String content) {
        ASTStack ast = createAST(content);
        return new DocumentBuilder()
                .setDeckname(deckName)
                .setMediaPath(mediaPath)
                .setBody(createDocBody(ast))
                .build();
    }

    /**
     * Creates abstract syntax tree containing all card elements.
     * Grouping by tags will happen later on, this method simply translates
     * ankis html
     * format to a ast.
     * <p>
     * IMPORTANT: ELEMENTS ARE NOT GROUPED BY TAG
     *
     * @param input
     * @return
     */
    private ASTStack createAST(String input) {
        System.out.println("content to parse: " + input);
        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        ASTStack ast = new BuildAstVisitor().visitStack(cst);
        return ast;
    }

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

            if (idxSection == -1) {
                output.addNode(newSection);
            } else {
                DocNode oldSection = output.getChildren().get(idxSection);
                assert oldSection instanceof Section;
                ((Section)oldSection).addNode(newSection);
            }

        }

        return output;
    }

    private static Section createSection(Card card) {
        List<String> tags = Arrays.asList(card.getTag().get(0).split("::"));
        return createSection(tags, card);
    }

    private static Section createSection(List<String> tags, DocNode node) {
        if (tags.size() == 1) {
            return new Section(tags.get(0), node);
        }

        // only 5 possible types of headings
        String tag;
        switch (tags.size()) {
            case 2:
                tag = removeLastElem(tags);
                return new SubSection(tag, createSection(tags, node));
            case 3:
                tag = removeLastElem(tags);
                return new SubSubSection(tag, createSection(tags, node));
            case 4:
                tag = removeLastElem(tags);
                return new Paragraph(tag, createSection(tags, node));
            case 5:
                tag = removeLastElem(tags);
                return new SubParagraph(tag, createSection(tags, node));
            default:
                removeLastElem(tags);
                return createSection(tags, node);
        }

    }

    private static <E> E removeLastElem(List<E> lst) {
        E out = lst.isEmpty() ? null : lst.get(lst.size() - 1);
        int newLastIdx = lst.isEmpty() ? 0 : lst.size() - 2;
        lst.subList(0, newLastIdx);
        return out;
    }
}
