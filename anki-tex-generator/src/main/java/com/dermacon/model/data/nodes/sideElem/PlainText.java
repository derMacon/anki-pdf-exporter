package com.dermacon.model.data.nodes.sideElem;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.List;

public class PlainText extends SideElem {

    private String value;

    public PlainText(String value) {
//        this.value = value.replaceAll("ö", "\"o")
//                .replaceAll("Ö", "\"O")
//                .replaceAll("ü", "\"u")
//                .replaceAll("Ü", "\"U")
//                .replaceAll("ä", "\"a")
//                .replaceAll("Ä", "\"A");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public List<DocNode> getChildren() {
        System.err.println("plain text has no further children");
        return null;
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public String toString() {
        return "value:" + value + ";";
    }

    @Override
    public boolean equals(Object o) {
        PlainText other = null;
        if (o instanceof PlainText) {
            other = (PlainText) o;
        }
        return other != null
                && this.value.equals(other.value);
    }

}
