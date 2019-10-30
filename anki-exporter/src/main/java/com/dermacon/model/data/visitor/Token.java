package com.dermacon.model.data.visitor;

public interface Token {
    void visit(TokenVisitor<?> visitor);
}
