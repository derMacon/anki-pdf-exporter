package com.dermacon.model.data.element;

import com.dermacon.model.data.visitor.TokenVisitor;

import java.util.Arrays;
import java.util.List;

public class Card implements BodyElement {

    private final List<BodyElement> front;
    private final List<BodyElement> back;

    public Card(BodyElement[] front, BodyElement[] back) {
        this.front = Arrays.asList(front);
        this.back = Arrays.asList(back);
    }

    public List<BodyElement> getFront() {
        return front;
    }

    public List<BodyElement> getBack() {
        return back;
    }

    @Override
    public void visit(TokenVisitor<?> visitor) {
        visitor.process(this);
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
}
