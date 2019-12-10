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
import com.dermacon.model.data.nodes.sideElem.ItalicItem;
import com.dermacon.model.data.nodes.sideElem.SideContainer;
import com.dermacon.model.data.nodes.sideElem.SideElem;
import com.dermacon.model.data.nodes.sideElem.UnderlinedItem;
import com.dermacon.model.data.nodes.sideElem.UnorderedList;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;
import java.util.List;

/**
 * Generates an AstStack node hierarchy. This tree will later on be processed
 * by the corresponding visitor instance (depending on what output format
 * will be used).
 */
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
        String tags = ctx.tags == null ? "" : ctx.tags.getText();
        return new Card(visitSideContainer(ctx.front),
                visitSideContainer(ctx.back), tags);
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
        } else if (ctx.italicItem() != null) {
            out = visitItalicItem(ctx.italicItem());
        } else if (ctx.underlinedItem() != null) {
            out = visitUnderlinedItem(ctx.underlinedItem());
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
    public SideContainer visitItalicItem(CardStackParser.ItalicItemContext ctx) {
        return new ItalicItem(visitSideContainer(ctx.sideContainer()));
    }

    @Override
    public SideContainer visitUnderlinedItem(CardStackParser.UnderlinedItemContext ctx) {
        return new UnderlinedItem(visitSideContainer(ctx.sideContainer()));
    }

    @Override
    public OrderedList visitOrderedList(CardStackParser.OrderedListContext ctx) {
        List<ListItem> elems = new LinkedList<>();
        for (CardStackParser.ListItemContext lstCtx : ctx.elems) {
            elems.add(visitListItem(lstCtx));
        }
        return new OrderedList(elems);
    }

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
    public PlainText visitPlainText(CardStackParser.PlainTextContext ctx) {
//        return new PlainText(ctx.paragraph);
        StringBuilder content = new StringBuilder();
        for (CardStackParser.WordContext w : ctx.paragraph) {
            content.append(w.getText());
        }
        return new PlainText(content.toString());
    }

    @Override
    public ImageItem visitImageItem(CardStackParser.ImageItemContext ctx) {
        return new ImageItem(ctx.IDENTIFIER().getText() + ctx.IMG_TYPE().getText());
    }

}
