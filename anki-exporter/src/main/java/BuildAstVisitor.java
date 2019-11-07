import com.dermacon.model.data.nodes.ast.ASTCard;
import com.dermacon.model.data.nodes.ast.ASTNode;
import com.dermacon.model.data.nodes.ast.ASTStack;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.sideElem.SideElem;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.LinkedList;
import java.util.List;

public class BuildAstVisitor extends CardStackBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitStack(CardStackParser.StackContext ctx) {
        ASTStack stack = new ASTStack();
        for(CardStackParser.CardContext cardCtx : ctx.card()) {
            stack.addNode(visit(cardCtx));
        }
        return stack;
    }

    @Override
    public ASTCard visitCard(CardStackParser.CardContext ctx) {
        List<SideElem> front = new LinkedList<>();
        for(CardStackParser.SideContext cardCtx : ctx.front) {
            front.add(visitSide(cardCtx));
        }
        List<SideElem> back = new LinkedList<>();
        for(CardStackParser.SideContext cardCtx : ctx.back) {
            back.add(visitSide(cardCtx));
        }
        return new ASTCard(front, back);
    }

    @Override
    public SideElem visitSide(CardStackParser.SideContext ctx) {
        return visitPlainText(ctx.plainText());
    }

    @Override
    public SideElem visitPlainText(CardStackParser.PlainTextContext ctx) {
        return new PlainText(ctx.getText());
    }
}
