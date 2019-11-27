package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.document.headings.Paragraph;
import com.dermacon.model.data.nodes.document.headings.Section;
import com.dermacon.model.data.nodes.document.headings.SubParagraph;
import com.dermacon.model.data.nodes.document.headings.SubSection;
import com.dermacon.model.data.nodes.document.headings.SubSubSection;
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

import static org.junit.Assert.*;

public class BodyFabricTest {

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

        Body actOutput = BodyFabric.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);
    }


    @Test
    public void testRemoveFstElem() {
        List<String> l = new LinkedList<>();
        Assert.assertNull(BodyFabric.removeFstElem(l));
        Assert.assertEquals(new LinkedList<String>(), l);

        l.add("fst");
        l.add("snd");
        l.add("thrd");

        List<String> l2 = new LinkedList<>();
        l2.add("snd");
        l2.add("thrd");

        List<String> l3 = new LinkedList<>();
        l3.add("thrd");

        Assert.assertEquals("fst", BodyFabric.removeFstElem(l));
        Assert.assertEquals(l2, l);
        Assert.assertEquals("snd", BodyFabric.removeFstElem(l));
        Assert.assertEquals(l3, l);
        Assert.assertEquals("thrd", BodyFabric.removeFstElem(l));
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

        Body actOutput = BodyFabric.createDocBody(ast);
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

        Body actOutput = BodyFabric.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testCreateDocBody_twoCardsSameTag1() {
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

        Body actOutput = BodyFabric.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testCreateDocBody_twoCardsSameTag2() {
        Card card1 = createCard(
                "fstTag",
                createCon(
                        new PlainText("front1")
                ),
                createCon(
                        new PlainText("back1")
                )
        );

        Card card2 = createCard(
                "fstTag",
                createCon(
                        new PlainText("front2")
                ),
                createCon(
                        new PlainText("back2")
                )
        );

        Body expOutput = new Body(new Section("fstTag", card1, card2));
        ASTStack ast = createStack(card1, card2);
        Body actOutput = BodyFabric.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testCreateDocBody_sectionContainsOtherSection() {
        Card card1 = createCard(
                "fstTag",
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
    }

    @Test
    public void testCreateDocBody_sameParent() {
        Card card1 = createCard(
                "fstTag",
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

        Card card3 = createCard(
                "fstTag::thrdTag",
                createCon(
                        new PlainText("front3")
                ),
                createCon(
                        new PlainText("back3")
                )
        );

        Card card4 = createCard(
                "fstTag::thrdTag",
                createCon(
                        new PlainText("front4")
                ),
                createCon(
                        new PlainText("back4")
                )
        );

        ASTStack ast = createStack(card1, card2, card3, card4);
        Body expOutput = new Body(new Section("fstTag", card1,
                new SubSection("sndTag", card2),
                new SubSection("thrdTag", card3, card4)
        ));

        Body actOutput = BodyFabric.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testCreateDocBody_twoCardsNotSameTag() {
        Card card1 = createCard(
                "fstTag::sndTag::thrdTag::frthTag::ffthTag::sxtag",
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
                new SubSection("sndTag",
                        new SubSubSection("thrdTag",
                                new Paragraph("frthTag",
                                        new SubParagraph("ffthTag",
                                                card1
                                        )
                                )
                        ), card2)));

        Body actOutput = BodyFabric.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testHeadingExists_true() {
        Section sec1 = new Section("fst", new PlainText("hello"));
        Section sec2 = new Section("snd", new PlainText("world"));
        Assert.assertFalse(BodyFabric.append(sec1, sec2));
    }

    @Test
    public void testHeadingExists_isChild1() {
        List<String> hierarchy = new LinkedList<>();
        hierarchy.add("fst");
        hierarchy.add("snd");
        Section sec1 = new Section("fst",
                new SubSection("snd", new PlainText("hello"))
        );
        Section sec2 = new Section("snd", new PlainText("world"));
        Assert.assertFalse(BodyFabric.append(sec1, sec2));
    }

    @Test
    public void testHeadingExists_isChild2() {
        List<String> hierarchy = new LinkedList<>();
        hierarchy.add("fst");
        hierarchy.add("snd");
        SubSection subSec = new SubSection("snd", new PlainText("hi"));
        Section sec = new Section("fst",
                subSec
        );
//        assertEquals(subSec, BodyFabric.findHeading(hierarchy, sec));
    }

}