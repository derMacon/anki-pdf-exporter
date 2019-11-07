package com.dermacon.model.data.visitor;

import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.sideElem.OrderedList;
import com.dermacon.model.data.nodes.document.Section;
import com.dermacon.model.data.nodes.sideElem.UnorderedList;
import com.dermacon.model.data.nodes.sideElem.ListItem;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.Header;

public interface FormatVisitor<R> {
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