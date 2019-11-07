import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class MainTest {

    public static void main(String[] args) {
        System.out.println("here");
        String input = "front\tback";
        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));

        try {
            CardStackParser.StackContext cst = p.stack();
            ASTNode ast = new BuildAstVisitor().visitStack(cst);
//            Double value = new EvaluateExpressionVisitor().visit(ast);
//            System.out.println("val: " + value);
            System.out.println("res");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

}
