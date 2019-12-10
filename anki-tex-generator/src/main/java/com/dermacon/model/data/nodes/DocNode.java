package com.dermacon.model.data.nodes;

import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.List;

/**
 * Single node of the abstract syntax tree.
 */
public interface DocNode {
    <E> E accept(FormatVisitor<E> visitor);
    List<? extends DocNode> getChildren();
}
