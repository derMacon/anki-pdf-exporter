package com.dermacon.model.generate;

import com.dermacon.antlr.CardStackLexer;
import com.dermacon.antlr.CardStackParser;
import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.sideElem.BoldItem;
import com.dermacon.model.data.nodes.sideElem.DivBlock;
import com.dermacon.model.data.nodes.sideElem.ImageItem;
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

        DocNode expOutput = createStack(
                new Card(
                        createCon(new PlainText("front")),
                        createCon(new PlainText("back"))
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_multipleWords() {
//        String input = "front \tback\n";
        String input = "front, front2\tback\n";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new PlainText("front, front2")),
                        createCon(new PlainText("back"))
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_twoCards() {
        String input = "front\t<b>bold</b>back\n2\t3\n";

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
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_recursiveText() {
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
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_List_ul() {
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
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_List_ol() {
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
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_divBlock_withoutClass() {
        String input = "<div>front</div>\t<div>back1</div><div>back2</div>\n";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new DivBlock(createCon(new PlainText("front")))),
                        createCon(
                                new DivBlock(createCon(new PlainText("back1"))),
                                new DivBlock(createCon(new PlainText("back2")))
                        )
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_divBlock_withClass1() {
        String input = "<div class=front>front</div>\tback\n";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new DivBlock(createCon(new PlainText("front")))),
                        createCon(new PlainText("back"))
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_divBlock_withoutClass2() {
        String input = "<div class=front>front</div>\t<div>back1</div><div" +
                ">back2</div>\n";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new DivBlock(createCon(new PlainText("front")))),
                        createCon(
                                new DivBlock(createCon(new PlainText("back1"))),
                                new DivBlock(createCon(new PlainText("back2")))
                        )
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }


    @Test
    public void testVisitStack_img() {
        String input = "front\t<img src=test.png/>\n";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new PlainText("front")),
                        createCon(
                                new ImageItem("test.png")
                        )
                )
        );

        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_backslash() {
        String input = "front\tback/after\n";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new PlainText("front")),
                        createCon(new PlainText("back/after"))
                )
        );

        input = input.replaceAll("\"", "");
        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }

    @Test
    public void testVisitStack_realWorldExample1() {
        String input = "\"<div class=\"\"front\"\"> Erläutern Sie die " +
                "Motivation hinter der Datenbanktheorie.</div>\"\t\"back\"\"\n";
//        String input = "\"<div class=\"\"front\"\">hi ho</div>\tback\n";
//                "Motivation hinter der Datenbanktheorie.</div>\"\t\"back\"\"\n";

        DocNode expOutput = createStack(
                new Card(
                        createCon(new DivBlock(createCon(new PlainText(" Erläutern Sie" +
                                " die Motivation hinter der Datenbanktheorie.")))),
                        createCon(new PlainText("back"))
                )
        );

        input = input.replaceAll("\"", "");
        CardStackLexer l = new CardStackLexer(new ANTLRInputStream(input));
        CardStackParser p = new CardStackParser(new CommonTokenStream(l));
        CardStackParser.StackContext cst = p.stack();
        DocNode actOutput = new BuildAstVisitor().visitStack(cst);

        Assert.assertEquals(expOutput, actOutput);
    }


}