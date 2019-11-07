import com.dermacon.model.data.nodes.ast.ASTCard;
import com.dermacon.model.data.nodes.ast.ASTNode;
import com.dermacon.model.data.nodes.ast.ASTStack;

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
    public ASTNode visitCard(CardStackParser.CardContext ctx) {
        return new ASTCard(ctx.front.getText(), ctx.back.getText());
    }
}
