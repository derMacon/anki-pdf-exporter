package com.dermacon.model.generate;

import com.dermacon.model.data.element.Card;
import com.dermacon.model.data.element.PlainText;
import com.dermacon.model.data.element.Section;
import com.dermacon.model.data.toplevel.Body;
import com.dermacon.model.data.toplevel.Document;
import com.dermacon.model.data.toplevel.Header;
import com.dermacon.model.data.visitor.TexVisitor;
import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

    // --- tests without file io ---

    @Test
    public void testEquals() {
        Document doc_1 = new Document(
                new Header("title"),
                new Body(
                        new Section(
                                "section",
                                new PlainText("text"),
                                new Card(
                                        new Token[]{
                                                new PlainText("front"),
                                        }, new Token[]{
                                        new PlainText("back")
                                }
                                )
                        ), new Section(
                        "section",
                        new PlainText("text")
                )
                )
        );
        Document doc_2 = new Document(
                new Header("title"),
                new Body(
                        new Section(
                                "section",
                                new PlainText("text"),
                                new Card(
                                        new Token[]{
                                                new PlainText("front"),
                                        }, new Token[]{
                                        new PlainText("back")
                                }
                                )
                        ), new Section(
                        "section",
                        new PlainText("text")
                )
                )
        );
        Assert.assertTrue(doc_1.equals(doc_1));
        Assert.assertTrue(doc_2.equals(doc_2));
        Assert.assertTrue(doc_1.equals(doc_2));
        Assert.assertTrue(doc_2.equals(doc_1));

        Assert.assertFalse(doc_1.equals(new Document(
                new Header("title"),
                new Body(
                        new PlainText("text")
                )
        )));
    }

    @Test
    public void testVisitor_simple() {
        String input = "";
//        String input = "front1\tback1";

//        Parser parser = new TexParser("path/to/res/");
//        Document doc = parser.parse(input);

        Document doc = new Document(
                new Header("title"),
                new Body(
                        new Section(
                                "section",
                                new PlainText("text"),
                                new Card(
                                        new Token[]{
                                                new PlainText("front"),
                                        }, new Token[]{
                                        new PlainText("back")
                                }
                                )
                        ), new Section(
                        "section",
                            new PlainText("text")
                        )
                )
        );

        System.out.println(doc);
        TokenVisitor<String> visitor = new TexVisitor();
        System.out.println(doc.accept(visitor));
    }


}