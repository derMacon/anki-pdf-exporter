package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.document.DocumentBuilder;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.document.headings.Section;
import com.dermacon.model.data.nodes.document.Document;
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
        String mediaPath = "media/path/";
        String input = "front\tback\n";

        DocNode[] nodes = new DocNode[]{new Section(
                new Card(
                        createCon(new PlainText("front")),
                        createCon(new PlainText("back"))
                )
        )};

        Document expOutput = new DocumentBuilder()
                .setDeckname(title)
                .setMediaPath(mediaPath)
                .setBody(new Body(nodes))
                .build();

        Parser parser = new TXTParser(mediaPath, title);
        Document actOutput = parser.parse(input);

        Assert.assertEquals(expOutput, actOutput);
    }

}