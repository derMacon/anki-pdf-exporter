package com.dermacon.model.generate;

import com.dermacon.model.data.Card;
import com.dermacon.model.data.toplevel.Document;

import java.util.List;

public interface Parser {
    public Document parse(String content);
}
