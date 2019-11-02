package com.dermacon.model.data.visitor;

public interface Token {
    <E> E accept(TokenVisitor<E> visitor);
}
