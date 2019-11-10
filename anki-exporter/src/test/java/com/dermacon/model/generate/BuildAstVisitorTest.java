package com.dermacon.model.generate;

import com.dermacon.antlr.CardStackLexer;
import com.dermacon.antlr.CardStackParser;
import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.sideElem.BoldItem;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.sideElem.SideContainer;
import com.dermacon.model.data.nodes.sideElem.SideElem;
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

}