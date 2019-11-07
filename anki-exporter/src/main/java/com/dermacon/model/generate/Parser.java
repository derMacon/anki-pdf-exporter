package com.dermacon.model.generate;

import com.dermacon.model.data.nodes.document.Document;

public interface Parser {
    public Document parse(String content);
}
