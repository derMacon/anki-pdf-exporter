package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.List;

public class Document implements DocNode {
    private final Header header;
    private final Body body;
    private final String mediaPath;

    public Document(Header header, Body body, String mediaPath) {
        this.header = header;
        this.body = body;
        this.mediaPath = mediaPath;
    }

    public Header getHeader() {
        return header;
    }

    public Body getBody() {
        return body;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    @Override
    public <E> E accept(FormatVisitor<E> visitor) {
        return visitor.process(this);
    }

    @Override
    public List<DocNode> getChildren() {
        return null;
    }

    @Override
    public String toString() {
        return "Document{"
                + header.toString()
                + body.toString()
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        Document other = null;
        if (o instanceof Document) {
            other = (Document)o;
        }
        return other != null
                && this.header.equals(other.header)
                && this.body.equals(other.body);
    }

}
