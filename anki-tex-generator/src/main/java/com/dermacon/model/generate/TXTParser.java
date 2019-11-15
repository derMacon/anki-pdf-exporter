package com.dermacon.model.generate;

import com.dermacon.antlr.CardStackLexer;
import com.dermacon.antlr.CardStackParser;
import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.DocumentBuilder;
import com.dermacon.model.data.nodes.document.Section;
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
                .setNodes(sortStackByTags(ast))
                .build();
    }

    private ASTStack createAST(String input) {
        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        ASTStack ast = new BuildAstVisitor(mediaPath).visitStack(cst);
        return ast;
    }

    private List<DocNode> sortStackByTags(ASTStack ast) {
        // todo
        return Arrays.asList(new Section[] {new Section(ast.getChildren())});
    }
}
