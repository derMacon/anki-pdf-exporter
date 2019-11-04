package com.dermacon.model.generate;

import com.dermacon.model.data.element.Card;
import com.dermacon.model.data.element.PlainText;
import com.dermacon.model.data.element.Section;
import com.dermacon.model.data.toplevel.Body;
import com.dermacon.model.data.toplevel.Document;
import com.dermacon.model.data.toplevel.Header;
import com.dermacon.model.data.visitor.Token;
import org.junit.Assert;
import org.junit.Test;

public class ParserTest {

    @Test
    public void testVisitor_simple() {
        Document exp_output = new Document(
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

        Parser parser = new CSVParser("path/to/media/");
        String content = "todo test parser";

//        Assert.assertEquals(exp_output, parser.parse(content));
    }

}