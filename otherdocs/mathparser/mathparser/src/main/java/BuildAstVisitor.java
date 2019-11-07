public class BuildAstVisitor extends MathBaseVisitor<ExpressionNode> {

    @Override
    public ExpressionNode visitCompileUnit(MathParser.CompileUnitContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public ExpressionNode visitNumberExpr(MathParser.NumberExprContext ctx) {
        return new NumberNode(Double.parseDouble(ctx.value.getText()));
    }

    @Override
    public ExpressionNode visitParensExpr(MathParser.ParensExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public ExpressionNode visitInfixExpr(MathParser.InfixExprContext ctx) {
        InfixExpressionNode node = null;

        switch(ctx.op.getType()) {
            case MathLexer.OP_ADD:
                node = new AdditionNode();
                break;
            case MathLexer.OP_SUB:
                node = new SubtractionNode();
                break;
            case MathLexer.OP_MUL:
                node = new MultiplicationNode();
                break;
            case MathLexer.OP_DIV:
                node = new DivisionNode();
                break;
        }

        node.setLeft(visit(ctx.left));
        node.setRight(visit(ctx.right));

        return node;
    }

    @Override
    public ExpressionNode visitUnaryExpr(MathParser.UnaryExprContext ctx) {
        switch (ctx.op.getType()) {
            case MathLexer.OP_ADD:
                return visit(ctx.expr());
            case MathLexer.OP_SUB:
                return new NegateNode(visit(ctx.expr()));
        }
        System.out.println("this should not happen");
        return null;
    }

    @Override
    public ExpressionNode visitFuncExpr(MathParser.FuncExprContext ctx) {
        // todo
        return new FunctionNode((f) -> (Math.sqrt(f)), visit(ctx.expr()));
    }

}
