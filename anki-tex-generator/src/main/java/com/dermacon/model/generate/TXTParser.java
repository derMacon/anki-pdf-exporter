package com.dermacon.model.generate;

import com.dermacon.antlr.CardStackLexer;
import com.dermacon.antlr.CardStackParser;
import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.DocumentBuilder;
import com.dermacon.model.data.nodes.document.headings.Section;
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
        Card card;
        String tag;
        for (DocNode node : ast.getChildren()) {
            card = (Card) node;
            // only the first tag will be evaluated
            tag = card.getTag().get(0);
            output.addNode(new Section(tag, card));
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

        // only 4 possible types of headings
        if (tags.size() > 4) {
            tags.subList(0, 3).clear();
        }

//        switch (i) {
//            case 2: return new
//            case 3:
//                out = new Section(hierarchy[i], card);
//        }

    }
}
