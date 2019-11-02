package com.dermacon.model.data.visitor;

import com.dermacon.model.data.element.Card;
import com.dermacon.model.data.element.ListItem;
import com.dermacon.model.data.element.OrderedList;
import com.dermacon.model.data.element.PlainText;
import com.dermacon.model.data.element.Section;
import com.dermacon.model.data.element.UnorderedList;
import com.dermacon.model.data.toplevel.Body;
import com.dermacon.model.data.toplevel.Document;
import com.dermacon.model.data.toplevel.Header;

import java.util.List;

public class TexVisitor implements TokenVisitor<String> {

    private static final String DOC_TEMPLATE = "\\documentclass{article}\n"
            + "\\usepackage{tikz,lipsum,lmodern}\n"
            + "\\usepackage[most]{tcolorbox}\n"
            + "\\usepackage[paperheight=10.75in,paperwidth=7.25in,margin=1in,heightrounded]{geometry}\n"
            + "\\usepackage{graphicx}\n"
            + "\\usepackage{blindtext}\n"
            + "\\usepackage{ragged2e}\n"
            + "\\usepackage[space]{grffile}\n"
            + "\n"
            + "\\graphicspath{ {./img/} }\n"
            + "\n"
            + "%s\n"
            + "\n"
            + "%s\n"
            + "\n";

    private static final String BODY_TEMPLATE = "\\begin{document}\n"
                    + "%s\n"
                    + "\\end{document}\n";

    private static final String HEADER_TEMPLATE = "\\title{%s}";
    private static final String SECTION_DELIMITER = "%%*********************\n";
    private static final String SECTION_TEMPLATE = SECTION_DELIMITER
            + "\\section{%s}\n%s";

    private static final String CARD_DELIMITER = "%%---------------------\n";
    private static final String CARD_TEMPLATE = CARD_DELIMITER
            + "\\begin{tcolorbox}"
            + "[colback=white!10!white,colframe=lightgray!75!black,\n"
            + "  savelowerto=\\jobname_ex.tex]\n"
            + "\n"
            + "\\begin{center}\n"
            + "%s\n"
            + "\\end{center}\n"
            + "\n"
            + "\\tcblower\n"
            + "\n"
            + "\\justifying\n"
            + "%s\n"
            + "\\end{tcolorbox}\n";

    private static final String UL_TEMPLATE = "\\begin{itemize}\n"
            + "%s"
            + "\\end{itemize}";

    private static final String OL_TEMPLATE = "\\begin{enumerate}\n"
            + "%s"
            + "\\end{enumerate}";

    private static final String LST_ITEM_TEMPLATE = "\\item %s";

    @Override
    public String process(Document doc) {
        return String.format(DOC_TEMPLATE,
                doc.getHeader().accept(this),
                doc.getBody().accept(this));
    }

    @Override
    public String process(Header header) {
        return String.format(HEADER_TEMPLATE, header.getTitle());
    }

    @Override
    public String process(Body body) {
        return String.format(BODY_TEMPLATE,
                iterateChildren(body.getChildren()));
    }

    private String iterateChildren(List<Token> children) {
        StringBuilder out = new StringBuilder();
        for (Token elem : children) {
            out.append(elem.accept(this));
        }
        return out.toString();
    }

    @Override
    public String process(Section section) {
        return String.format(SECTION_TEMPLATE,
                section.getValue(),
                iterateChildren(section.getChildren()));
    }

    @Override
    public String process(Card card) {
        return String.format(CARD_TEMPLATE,
                iterateChildren(card.getFront()),
                iterateChildren(card.getBack()));
    }

    @Override
    public String process(PlainText text) {
        return text.getValue() + "\n";
    }

    @Override
    public String process(OrderedList lst) {
        return String.format(OL_TEMPLATE, iterateChildren(lst.getChildren()));
    }

    @Override
    public String process(UnorderedList lst) {
        return String.format(UL_TEMPLATE, iterateChildren(lst.getChildren()));
    }

    @Override
    public String process(ListItem lstItem) {
       return String.format(LST_ITEM_TEMPLATE,
                iterateChildren(lstItem.getChildren()));
    }

}
