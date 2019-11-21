package com.dermacon.model.generate;

import com.dermacon.antlr.CardStackBaseVisitor;
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

import java.util.LinkedList;
import java.util.List;

public class BuildAstVisitor extends CardStackBaseVisitor<DocNode> {

    @Override
    public ASTStack visitStack(CardStackParser.StackContext ctx) {
        ASTStack stack = new ASTStack();
        for(CardStackParser.CardContext cardCtx : ctx.card()) {
            stack.addNode(visit(cardCtx));
        }
        return stack;
    }

    @Override
    public Card visitCard(CardStackParser.CardContext ctx) {
        return new Card(visitSideContainer(ctx.front),
                visitSideContainer(ctx.back));
    }

    @Override
    public SideContainer visitSideContainer(CardStackParser.SideContainerContext ctx) {
        List<SideElem> elems = new LinkedList<>();
        for (CardStackParser.SideNodeContext sCtx : ctx.elems) {
            elems.add(visitSideNode(sCtx));
        }
        return new SideContainer(elems);
    }

    @Override
    public SideElem visitSideNode(CardStackParser.SideNodeContext ctx) {
        // todo rewrite
        SideElem out = null;
        if (ctx.boldItem() != null) {
            out = visitBoldItem(ctx.boldItem());
        } else if (ctx.recursiveItem() != null) {
            out = visitRecursiveItem(ctx.recursiveItem());
        } else if (ctx.plainText() != null) {
            out = visitPlainText(ctx.plainText());
        } else if (ctx.unorderedList() != null) {
            out = visitUnorderedList(ctx.unorderedList());
        } else if (ctx.orderedList() != null) {
            out = visitOrderedList(ctx.orderedList());
        } else if (ctx.divBlock() != null) {
            out = visitDivBlock(ctx.divBlock());
        } else if (ctx.imageItem() != null) {
            out = visitImageItem(ctx.imageItem());
        }

        assert (out != null) : "given context could not be interpreted";
        return out;
    }

    @Override
    public SideContainer visitDivBlock(CardStackParser.DivBlockContext ctx) {
        return new DivBlock(visitSideContainer(ctx.sideContainer()));
    }

    @Override
    public SideContainer visitBoldItem(CardStackParser.BoldItemContext ctx) {
        return new BoldItem(visitSideContainer(ctx.sideContainer()));
    }

    @Override
    public SideContainer visitRecursiveItem(CardStackParser.RecursiveItemContext ctx) {
        return new RecursiveItem(visitSideContainer(ctx.sideContainer()));
    }

    @Override
    public OrderedList visitOrderedList(CardStackParser.OrderedListContext ctx) {
        List<ListItem> elems = new LinkedList<>();
        for (CardStackParser.ListItemContext lstCtx : ctx.elems) {
            elems.add(visitListItem(lstCtx));
        }
        return new OrderedList(elems);
    }

//    private <T> List<ListItem> iterateChildren() {
//
//    }

    @Override
    public UnorderedList visitUnorderedList(CardStackParser.UnorderedListContext ctx) {
        // todo implement using helper
        List<ListItem> elems = new LinkedList<>();
        for (CardStackParser.ListItemContext lstCtx : ctx.elems) {
            elems.add(visitListItem(lstCtx));
        }
        return new UnorderedList(elems);
    }

    @Override
    public ListItem visitListItem(CardStackParser.ListItemContext ctx) {
        return new ListItem(visitSideContainer(ctx.sideContainer()));
    }

    @Override
    public SideElem visitPlainText(CardStackParser.PlainTextContext ctx) {
        return new PlainText(ctx.getText());
    }

    @Override
    public ImageItem visitImageItem(CardStackParser.ImageItemContext ctx) {
        return new ImageItem(ctx.IDENTIFIER().getText());
    }
}
