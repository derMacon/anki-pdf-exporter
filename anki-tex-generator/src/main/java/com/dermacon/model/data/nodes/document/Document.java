package com.dermacon.model.data.nodes.document;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.visitor.FormatVisitor;

import java.util.List;

/**
 * Document that will be parsed by the Visitor.
 * Holds the information for the header / title page as well as the body
 * element with all cards and the media path to specify where the actual
 * images lay that will be displayed on the cards.
 *
 */
public class Document implements DocNode {
    private final MetaHeader metaHeader;
    private final Body body;
    private final String mediaPath;

    public Document(MetaHeader metaHeader, Body body, String mediaPath) {
        this.metaHeader = metaHeader;
        this.body = body;
        this.mediaPath = mediaPath;
    }

    public MetaHeader getMetaHeader() {
        return metaHeader;
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
                + metaHeader.toString()
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
                && this.metaHeader.equals(other.metaHeader)
                && this.body.equals(other.body);
    }

}
