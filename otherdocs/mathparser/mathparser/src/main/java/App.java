import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println("Anki-Pdf-Parser v1.0");

        String exprTest = "2+2";
//        String exprTest = new Scanner(System.in).nextLine();

        MathLexer l = new MathLexer(new ANTLRInputStream(exprTest));
        MathParser p = new MathParser(new CommonTokenStream(l));

        try {
            MathParser.CompileUnitContext cst = p.compileUnit();
            ExpressionNode ast = new BuildAstVisitor().visitCompileUnit(cst);
            Double value = new EvaluateExpressionVisitor().visit(ast);
            System.out.println("val: " + value);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
//        p.addErrorListener(new BaseErrorListener() {
//            //            @Override
//            public void syntaxError(Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
//                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
//            }
//        });

    }
}
