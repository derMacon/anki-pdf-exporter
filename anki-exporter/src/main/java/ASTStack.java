import java.util.LinkedList;
import java.util.List;

public class ASTStack extends ASTNode {
    private List<ASTNode> nodes = new LinkedList<>();

    public void addNode(ASTNode node) {
        nodes.add(node);
    }

    public List<ASTNode> getNodes() {
        return nodes;
    }
}
