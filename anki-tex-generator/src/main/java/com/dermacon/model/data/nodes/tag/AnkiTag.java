package com.dermacon.model.data.nodes.tag;

// todo delete this
public class AnkiTag {
    private String content;

    public AnkiTag(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "tag:" + this.content + ")";
    }


}
