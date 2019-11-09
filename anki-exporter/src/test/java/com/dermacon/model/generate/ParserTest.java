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
        String title = "title";
        String txt = "content";
        String input = "front\tback\n";

        Document exp_output = new Document(
                new Header(title),
                new Body(
                        new Card()
                )
        );

        Parser parser = new TXTParser("path/to/media/", title);
        String content = parser.parse()

//        Assert.assertEquals(exp_output, parser.parse(content));
    }

}