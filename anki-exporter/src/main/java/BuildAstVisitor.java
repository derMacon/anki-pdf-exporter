public class BuildAstVisitor extends CardStackBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitStack(CardStackParser.StackContext ctx) {
        return visit(ctx.card());
    }

    @Override
    public ASTNode visitCard(CardStackParser.CardContext ctx) {
        return new ASTCard(ctx.getText(), ctx.getText());
    }
}
