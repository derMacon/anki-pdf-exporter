package com.dermacon.model.data.element;

import com.dermacon.model.data.toplevel.Body;
import com.dermacon.model.data.visitor.TokenVisitor;
import com.dermacon.model.data.visitor.Token;

import java.util.Arrays;
import java.util.List;

public class Card implements Token {

    private final List<Token> front;
    private final List<Token> back;

    public Card(Token[] front, Token[] back) {
        this.front = Arrays.asList(front);
        this.back = Arrays.asList(back);
    }

    public List<Token> getFront() {
        return front;
    }

    public List<Token> getBack() {
        return back;
    }

    @Override
    public String toString() {
        return "card{front:{" + iterate(front) + "}"
            + "back:{" + iterate(front) + "}}";
    }

    private static String iterate(List<?> lst) {
        String out = "";
        for (Object o : lst) {
            out += o.toString();
        }
        return out;
    }

    @Override
    public List<Token> getChildren() {
        return null;
    }

    @Override
    public <E> E accept(TokenVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public boolean equals(Object o) {
        Card other = null;
        if (o instanceof Card) {
            other = (Card)o;
        }
        return other != null
                && this.front.equals(other.front)
                && this.back.equals(other.back);
    }
}
