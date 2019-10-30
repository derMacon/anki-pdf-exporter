package com.dermacon.model.data.visitor;

import com.dermacon.model.data.element.Element;
import com.dermacon.model.data.element.OrderedList;
import com.dermacon.model.data.element.Section;
import com.dermacon.model.data.element.UnorderedList;
import com.dermacon.model.data.element.ListItem;
import com.dermacon.model.data.element.PlainText;
import com.dermacon.model.data.toplevel.Document;
import com.dermacon.model.data.toplevel.Header;

public interface TokenVisitor<R> {
    void process(Document document);
    void process(Header header);
    void process(PlainText text);
    void process(UnorderedList lstItem);
    void process(OrderedList lstItem);
    void process(ListItem lstItem);
    void process(Section section);

    R getResult();
}
