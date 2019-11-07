public class InfixExpressionNode extends ExpressionNode {
    private ExpressionNode left;
    private ExpressionNode right;

    public ExpressionNode getLeft() {
        return left;
    }

    public ExpressionNode getRight() {
        return right;
    }

    public void setLeft(ExpressionNode left) {
        this.left = left;
    }

    public void setRight(ExpressionNode right) {
        this.right = right;
    }
}
