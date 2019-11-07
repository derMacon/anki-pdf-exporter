public class NegateNode extends ExpressionNode {
    private ExpressionNode innerNode;

    public NegateNode(ExpressionNode innerNode) {
        this.innerNode = innerNode;
    }

    public ExpressionNode getInnerNode() {
        return innerNode;
    }

    public void setInnerNode(ExpressionNode innerNode) {
        this.innerNode = innerNode;
    }
}
