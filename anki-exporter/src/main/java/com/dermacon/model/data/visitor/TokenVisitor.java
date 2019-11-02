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
    R process(Document doc);
    R process(Header header);
    R process(Body body);
    R process(Section section);
    R process(Card card);
    R process(PlainText text);
    R process(OrderedList lst);
    R process(UnorderedList lst);
    R process(ListItem lstItem);
}
