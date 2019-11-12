package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.LinkedList;
import java.util.List;

public class ASTStack implements DocNode {
    private List<DocNode> nodes;

    public ASTStack() {
        nodes = new LinkedList<>();
    }

    public ASTStack(List<DocNode> nodes) {
        this.nodes = nodes;
    }

    public void addNode(DocNode node) {
        nodes.add(node);
    }

    @Override
    public List<DocNode> getChildren() {
        return this.nodes;
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return null;
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
