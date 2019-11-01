package com.dermacon.model.data.visitor;

import com.dermacon.model.data.element.Card;
import com.dermacon.model.data.element.OrderedList;
import com.dermacon.model.data.element.Section;
import com.dermacon.model.data.element.UnorderedList;
import com.dermacon.model.data.element.ListItem;
import com.dermacon.model.data.element.PlainText;
import com.dermacon.model.data.toplevel.Body;
import com.dermacon.model.data.toplevel.Document;
import com.dermacon.model.data.toplevel.Header;

public interface TokenVisitor<R> {
    void process(Document doc);
    void process(Header header);
    void process(Body body);
    void process(Section section);
    void process(Card card);
    String process(PlainText text);
    void process(OrderedList lstItem);
    void process(UnorderedList lstItem);
    void process(ListItem lstItem);

    R getResult();
}
