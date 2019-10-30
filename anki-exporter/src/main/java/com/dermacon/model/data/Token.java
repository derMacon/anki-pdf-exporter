package com.dermacon.model.data;

import java.util.List;

public interface Token {
    List<Token> getChildren();
    String getValue();
}
