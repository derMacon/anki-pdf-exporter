package com.dermacon.model.generate;

import com.dermacon.antlr.CardStackLexer;
import com.dermacon.antlr.CardStackParser;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.DocumentBuilder;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class TXTParser implements Parser {

    private final String mediaPath;
    private final String deckName;

    /**
     * Constructor setting the media path and deckname of the deck that
     * should be parsed.
     *
     * @param mediaPath path where anki stores all images.
     * @param deckName  name of the deck that should be parsed.
     */
    public TXTParser(String mediaPath, String deckName) {
        this.mediaPath = mediaPath;
        this.deckName = deckName;
    }

    @Override
    public Document parse(String content) {
        ASTStack ast = createAST(content);
        Body documentBody = BodyFabric.createDocBody(ast);
        return new DocumentBuilder()
                .setDeckname(deckName)
                .setMediaPath(mediaPath)
                .setBody(documentBody)
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
     * @param input String input (anki format) that should be parsed to an
     *              abstract syntax tree
     * @return abstract syntax tree containing all relevant nodes. See .g4
     * grammar file for more information.
     */
    private ASTStack createAST(String input) {
        System.out.println("content to parse: " + input);
        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        ASTStack ast = new BuildAstVisitor().visitStack(cst);
        return ast;
    }

}
