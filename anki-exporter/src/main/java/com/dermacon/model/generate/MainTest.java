package com.dermacon.model.generate;

import com.dermacon.antlr.CardStackLexer;
import com.dermacon.antlr.CardStackParser;
import com.dermacon.model.data.nodes.ast.ASTNode;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class MainTest {

    public static void main(String[] args) {
        System.out.println("here");
        String input = "front\t<b>bold</b>back\n2\t3\n";
        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));

        try {
            CardStackParser.StackContext cst = p.stack();
            ASTNode ast = new BuildAstVisitor().visitStack(cst);
//            Double value = new EvaluateExpressionVisitor().visit(ast);
            System.out.println("res");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

}
