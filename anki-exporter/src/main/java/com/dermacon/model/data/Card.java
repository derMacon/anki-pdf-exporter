package com.dermacon.model.data;

import java.util.List;

public class Card {

    private final List<Token> front;
    private final List<Token> back;

    public Card(List<Token> front, List<Token> back) {
        this.front = front;
        this.back = back;
    }

    public List<Token> getFront() {
        return front;
    }

    public List<Token> getBack() {
        return back;
    }
}
