package com.dermacon.model.generate;

import com.dermacon.antlr.CardStackLexer;
import com.dermacon.antlr.CardStackParser;
import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.sideElem.BoldItem;
import com.dermacon.model.data.nodes.sideElem.ListItem;
import com.dermacon.model.data.nodes.sideElem.OrderedList;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.sideElem.RecursiveItem;
import com.dermacon.model.data.nodes.sideElem.SideContainer;
import com.dermacon.model.data.nodes.sideElem.SideElem;
import com.dermacon.model.data.nodes.sideElem.UnorderedList;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BuildAstVisitorTest {

    private DocNode createStack(DocNode... nodes) {
        return new ASTStack(Arrays.asList(nodes));
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
    public void testVisitStack_simple() {
        String input = "front\tback\n";
        String mockMediaPath = "path/to/media/";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new PlainText("front")),
                        createCon(new PlainText("back"))
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor(mockMediaPath).visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_twoCards() {
        String input = "front\t<b>bold</b>back\n2\t3\n";
        String mockMediaPath = "path/to/media/";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new PlainText("front")),
                        createCon(
                                new BoldItem(
                                        createCon(new PlainText("bold"))
                                ),
                                new PlainText("back")
                        )
                ),
                new Card(
                        createCon(new PlainText("2")),
                        createCon(new PlainText("3"))
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor(mockMediaPath).visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_recursiveText() {
        String mockMediaPath = "path/to/media/";
        String input = "front\t<i>rec</i>back\n2\t3\n";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new PlainText("front")),
                        createCon(
                                new RecursiveItem(
                                        createCon(new PlainText("rec"))
                                ),
                                new PlainText("back")
                        )
                ),
                new Card(
                        createCon(new PlainText("2")),
                        createCon(new PlainText("3"))
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor(mockMediaPath).visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_List_ul() {
        String mockMediaPath = "path/to/media/";
        String input = "front\tback" +
                "<ul><li>unordered</li><li>list</li></ul>\n";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new PlainText("front")),
                        createCon(
                                new PlainText("back"),
                                createUL(
                                        new ListItem(createCon(new PlainText(
                                                "unordered"))),
                                        new ListItem(createCon(new PlainText(
                                                "list")))
                                )
                        )
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor(mockMediaPath).visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_List_ol() {
        String mockMediaPath = "path/to/media/";
        String input = "front<ol><li>firstItem</li><li>second</li></ol>\tback" +
                "\n";

        DocNode expOutput = createStack(
                new Card(
                        createCon(
                                new PlainText("front"),
                                createOL(
                                        new ListItem(createCon(new PlainText(
                                                "firstItem"))),
                                        new ListItem(createCon(new PlainText(
                                                "second")))
                                )
                        ),
                        createCon(new PlainText("back"))
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor(mockMediaPath).visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

}