package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.document.headings.Section;
import com.dermacon.model.data.nodes.document.headings.SubSection;
import com.dermacon.model.data.nodes.sideElem.ListItem;
import com.dermacon.model.data.nodes.sideElem.OrderedList;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.sideElem.SideContainer;
import com.dermacon.model.data.nodes.sideElem.SideElem;
import com.dermacon.model.data.nodes.sideElem.UnorderedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TXTParserTest {

    private ASTStack createStack(DocNode... nodes) {
        return new ASTStack(Arrays.asList(nodes));
    }

    private Card createCard(SideContainer... con) {
        assert con.length == 2 : "a card needs to have two sides";
        return new Card(con[0], con[1]);
    }

    private Card createCard(String tag, SideContainer... con) {
        assert con.length == 2 : "a card needs to have two sides";
        return new Card(con[0], con[1], tag);
    }

    private SideContainer createCon(SideElem... elems) {
        return new SideContainer(Arrays.asList(elems));
    }

    private UnorderedList createUL(ListItem... elems) {
        return new UnorderedList(Arrays.asList(elems));
    }

    private OrderedList createOL(ListItem... elems) {
        return new OrderedList(Arrays.asList(elems));
    }

    @Test
    public void testRemoveFstElem() {
        List<String> l = new LinkedList<>();
        Assert.assertNull(TXTParser.removeFstElem(l));
        Assert.assertEquals(new LinkedList<String>(), l);

        l.add("fst");
        l.add("snd");
        l.add("thrd");

        List<String> l2 = new LinkedList<>();
        l2.add("snd");
        l2.add("thrd");

        List<String> l3 = new LinkedList<>();
        l3.add("thrd");

        Assert.assertEquals("fst", TXTParser.removeFstElem(l));
        Assert.assertEquals(l2, l);
        Assert.assertEquals("snd", TXTParser.removeFstElem(l));
        Assert.assertEquals(l3, l);
        Assert.assertEquals("thrd", TXTParser.removeFstElem(l));
    }

    @Test
    public void testCreateDocBody_simple() {
        Card card = createCard(
                "tag",
                createCon(
                        new PlainText("front")
                ),
                createCon(
                        new PlainText("back")
                )
        );

        ASTStack ast = createStack(card);
        Body expOutput = new Body(new Section("tag", card));

        Body actOutput = TXTParser.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testCreateDocBody_emptyCardTag() {
        Card card = createCard(
                createCon(
                        new PlainText("front")
                ),
                createCon(
                        new PlainText("back")
                )
        );

        ASTStack ast = createStack(card);
        Body expOutput = new Body(new Section("Generelles", card));

        Body actOutput = TXTParser.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testCreateDocBody_simpleHierarchyTag() {
        Card card1 = createCard(
                "fstTag::sndTag",
                createCon(
                        new PlainText("front1")
                ),
                createCon(
                        new PlainText("back1")
                )
        );

        ASTStack ast = createStack(card1);
        Body expOutput = new Body(new Section("fstTag",
                new SubSection("sndTag", card1)));

        Body actOutput = TXTParser.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testCreateDocBody_twoCardsSameTag() {
        Card card1 = createCard(
                "fstTag::sndTag",
                createCon(
                        new PlainText("front1")
                ),
                createCon(
                        new PlainText("back1")
                )
        );

        Card card2 = createCard(
                "fstTag::sndTag",
                createCon(
                        new PlainText("front2")
                ),
                createCon(
                        new PlainText("back2")
                )
        );

        ASTStack ast = createStack(card1, card2);
        Body expOutput = new Body(new Section("fstTag",
                new SubSection("sndTag", card1, card2)));

        Body actOutput = TXTParser.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);
    }

}