package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.document.Section;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.Header;
import com.dermacon.model.data.nodes.sideElem.SideContainer;
import com.dermacon.model.data.nodes.sideElem.SideElem;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ParserTest {

    private SideContainer createCon(SideElem... elems) {
        return new SideContainer(Arrays.asList(elems));
    }

    @Test
    public void testVisitor_simple() {
        String title = "title";
        String input = "front\tback\n";

        Document expOutput = new Document(
                new Header(title),
                new Body(
                    new Section(
                        new Card(
                                createCon(new PlainText("front")),
                                createCon(new PlainText("back"))
                        )
                    )
                )
        );

        Parser parser = new TXTParser("path/to/media/", title);
        Document actOutput = parser.parse(input);

        Assert.assertEquals(expOutput, actOutput);
    }

}