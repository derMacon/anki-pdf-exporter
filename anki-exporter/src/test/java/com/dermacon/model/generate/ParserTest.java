package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.document.Section;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.Header;
import com.dermacon.model.data.nodes.Node;
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
                                        new Node[]{
                                                new PlainText("front"),
                                        }, new Node[]{
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