package com.dermacon.model.generate;

import com.dermacon.model.data.Card;

import java.util.List;

public interface Parser {
    public String parse(List<Card> stack);
}
