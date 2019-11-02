package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.Token;

import java.util.List;

public interface BodyElement extends Token {
    List<BodyElement> getChildren();
};
