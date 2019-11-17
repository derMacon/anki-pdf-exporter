package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.nodes.DocNode;

import java.util.LinkedList;
import java.util.List;

public class DocumentBuilder {

    private String deckname = "Anki deck";
    private String mediaPath = "todo set path";
    private List<DocNode> ast = new LinkedList<>();

    public DocumentBuilder setDeckname(String name) {
        this.deckname = name;
        return this;
    }

    public DocumentBuilder setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
        return this;
    }

    public DocumentBuilder setNodes(List<DocNode> ast) {
        this.ast = ast;
        return this;
    }

    public Document build() {
        return new Document(new Header(deckname), new Body(ast), mediaPath);
    }

}
