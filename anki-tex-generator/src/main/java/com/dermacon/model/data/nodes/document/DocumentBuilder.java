package com.dermacon.model.data.nodes.document;

public class DocumentBuilder {

    private String deckname = "Anki deck";
    private String mediaPath = "todo set path";
    private Body body = null;

    public DocumentBuilder setDeckname(String name) {
        this.deckname = name;
        return this;
    }

    public DocumentBuilder setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
        return this;
    }

    public DocumentBuilder setBody(Body body) {
        this.body = body;
        return this;
    }

    public Document build() {
        return new Document(new MetaHeader(deckname), body, mediaPath);
    }

}
