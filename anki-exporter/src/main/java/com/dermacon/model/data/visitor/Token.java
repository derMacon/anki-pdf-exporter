package com.dermacon.model.data.visitor;

import java.util.List;

/**
 * Single node of the abstract syntax tree.
 */
public interface Token {
    <E> E accept(TokenVisitor<E> visitor);
    List<Token> getChildren();
}
