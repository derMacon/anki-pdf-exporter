import static org.junit.Assert.assertTrue;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void testExampleField() throws Exception {
        FieldLexer l = new FieldLexer(new ANTLRInputStream(getClass().getResourceAsStream("/example.field")));
        FieldParser p = new FieldParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        FieldParser.FieldContext c = p.field();
        System.out.println("here");
    }

    @Test
    public void testExampleJson() throws Exception {
//        com.example.JsonLexer l =
//                new com.example.JsonLexer(new ANTLRInputStream(getClass().getResourceAsStream(
//                        "/example.json")));
//        com.example.JsonParser p = new com.example.JsonParser(new CommonTokenStream(l));
//        p.addErrorListener(new BaseErrorListener() {
//            @Override
//            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
//                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
//            }
//        });
//        com.example.JsonParser.JsonContext c = p.json();

//        com.example.JsonLexer lexer = new com.example.JsonLexer(CharStreams.fromString("{\n" +
//                "  name : true}"));

        com.example.JsonLexer lexer =
                new com.example.JsonLexer(CharStreams.fromString("{fruit: " +
                        "\"Apple\", size: \"Large\", color: \"Red\"}"));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        com.example.JsonParser parser = new com.example.JsonParser(tokenStream);
//        parser.json();
        com.example.JsonParser.JsonContext c = parser.json();

        System.out.println("here");
    }


}
