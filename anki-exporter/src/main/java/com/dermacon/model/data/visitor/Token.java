package com.dermacon.model.data.visitor;

import java.util.List;

public interface Token {
    <E> E accept(TokenVisitor<E> visitor);
    List<Token> getChildren();
}
