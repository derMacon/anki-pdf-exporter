package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.ASTStack;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.sideElem.ListItem;
import com.dermacon.model.data.nodes.sideElem.OrderedList;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.sideElem.SideContainer;
import com.dermacon.model.data.nodes.sideElem.SideElem;
import com.dermacon.model.data.nodes.sideElem.UnorderedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TXTParserTest {

    private ASTStack createStack(DocNode... nodes) {
        return new ASTStack(Arrays.asList(nodes));
    }

    private Card createCard(SideContainer... con) {
        assert con.length == 2 : "a card needs to have two sides";
        return new Card(con[0], con[1]);
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
                createCon(
                        new PlainText("front")
                ),
                createCon(
                        new PlainText("back")
                )
        );

        ASTStack ast = createStack(card);
        Body expOutput = new Body(card);

        Body actOutput = TXTParser.createDocBody(ast);
        Assert.assertEquals(expOutput, actOutput);

    }

}