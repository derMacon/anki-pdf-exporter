package com.dermacon.model.data;

import com.dermacon.model.data.visitor.Token;
import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.List;

public class Card implements Token {

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

    @Override
    public void visit(TokenVisitor<?> visitor) {

    }
}
