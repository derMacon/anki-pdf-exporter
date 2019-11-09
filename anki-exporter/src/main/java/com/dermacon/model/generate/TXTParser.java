package com.dermacon.model.generate;

import com.dermacon.antlr.CardStackLexer;
import com.dermacon.antlr.CardStackParser;
import com.dermacon.model.data.nodes.ast.ASTNode;
import com.dermacon.model.data.nodes.ast.ASTStack;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.Header;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

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
        ast = sortStackByTags(ast);
//        return new Document(new Header(deckName), new Body())
        return null;
    }

    private ASTStack createAST(String input) {
        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        ASTStack ast = null;
        try {
            CardStackParser.StackContext cst = p.stack();
            ast = new BuildAstVisitor().visitStack(cst);
        } catch (Exception e) {
            // todo
            System.out.println(e.getStackTrace());
        }
        return ast;
    }

    private ASTStack sortStackByTags(ASTStack ast) {
        // todo
        return ast;
    }
}
