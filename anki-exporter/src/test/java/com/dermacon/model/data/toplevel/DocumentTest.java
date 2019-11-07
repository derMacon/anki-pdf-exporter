package com.dermacon.model.data.toplevel;

import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.Header;
import com.dermacon.model.data.nodes.document.Section;
import com.dermacon.model.data.nodes.Node;
import org.junit.Assert;
import org.junit.Test;

public class DocumentTest {

    @Test
    public void testEquals() {
        Document doc_1 = new Document(
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
        Document doc_2 = new Document(
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
        Assert.assertEquals(doc_1, doc_1);
        Assert.assertEquals(doc_2, doc_2);
        Assert.assertEquals(doc_1, doc_2);
        Assert.assertEquals(doc_2, doc_1);

        Assert.assertNotEquals(doc_1, new Document(
                new Header("title"),
                new Body(
                        new PlainText("text")
                )
        ));
    }
}