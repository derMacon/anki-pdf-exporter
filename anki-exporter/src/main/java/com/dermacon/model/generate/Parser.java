package com.dermacon.model.generate;

import com.dermacon.model.data.toplevel.Document;

public interface Parser {
    public Document parse(String content);
}
