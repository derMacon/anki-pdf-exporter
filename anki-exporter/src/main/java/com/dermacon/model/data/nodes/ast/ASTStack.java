package com.dermacon.model.data.nodes.ast;

import java.util.LinkedList;
import java.util.List;

public class ASTStack extends ASTNode {
    private List<ASTNode> nodes;

    public ASTStack() {
        nodes = new LinkedList<>();
    }

    public ASTStack(List<ASTNode> nodes) {
        this.nodes = nodes;
    }

    public void addNode(ASTNode node) {
        nodes.add(node);
    }

    public List<ASTNode> getNodes() {
        return nodes;
    }

    @Override
    public boolean equals(Object o) {
        ASTStack other = null;
        if (o instanceof ASTStack) {
            other = (ASTStack) o;
        }
        return other != null
                && this.nodes.equals(other.nodes);
    }

    @Override
    public String toString() {
        return "stack(" + nodes.toString() + ")";
    }
}
