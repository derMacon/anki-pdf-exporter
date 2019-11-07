public class EvaluateExpressionVisitor extends AstVisitor<Double> {
    @Override
    public Double visit(AdditionNode node) {
        return visit(node.getLeft()) + visit(node.getRight());
    }

    @Override
    public Double visit(SubtractionNode node) {
        return null;
    }

    @Override
    public Double visit(MultiplicationNode node) {
        return null;
    }

    @Override
    public Double visit(DivisionNode node) {
        return null;
    }

    @Override
    public Double visit(NegateNode node) {
        return null;
    }

    @Override
    public Double visit(FunctionNode node) {
        return null;
    }

    @Override
    public Double visit(NumberNode node) {
        return node.getValue();
    }
}
