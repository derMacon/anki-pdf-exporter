public class FunctionNode extends ExpressionNode {

    private java.util.function.Function<Double, Double> Function;
    private ExpressionNode argument;

    public FunctionNode(java.util.function.Function<Double, Double> function, ExpressionNode argument) {
        Function = function;
        this.argument = argument;
    }

    public ExpressionNode getArgument() {
        return argument;
    }

    public java.util.function.Function<Double, Double> getFunction() {
        return Function;
    }

    public void setArgument(ExpressionNode argument) {
        this.argument = argument;
    }

    public void setFunction(java.util.function.Function<Double, Double> function) {
        Function = function;
    }
}
