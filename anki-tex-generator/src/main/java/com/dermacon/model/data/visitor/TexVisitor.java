package com.dermacon.model.data.visitor;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.sideElem.ImageItem;
import com.dermacon.model.data.nodes.sideElem.ListItem;
import com.dermacon.model.data.nodes.sideElem.OrderedList;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.document.Section;
import com.dermacon.model.data.nodes.sideElem.SideContainer;
import com.dermacon.model.data.nodes.sideElem.SideElem;
import com.dermacon.model.data.nodes.sideElem.UnorderedList;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.Header;

import java.util.List;

public class TexVisitor implements FormatVisitor<String> {

    private static final String DOC_TEMPLATE = "\\documentclass{article}\n"
            + "\\usepackage{tikz,lipsum,lmodern}\n"
            + "\\usepackage[most]{tcolorbox}\n"
            + "\\usepackage[paperheight=10.75in,paperwidth=7.25in,margin=1in,heightrounded]{geometry}\n"
            + "\\usepackage{graphicx}\n"
            + "\\usepackage{blindtext}\n"
            + "\\usepackage{ragged2e}\n"
            + "\\usepackage[space]{grffile}\n"
            + "\n"
            + "\\graphicspath{ {%s} }\n"
//            + "\\graphicspath{ {./img/} }\n"
            + "\n"
            + "%s\n"
            + "\n"
            + "%s\n"
            + "\n";

    private static final String BODY_TEMPLATE = "\\begin{document}\n"
                    + "%s\n"
                    + "\\end{document}\n";

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

    private static final String HEADER_TEMPLATE = "\\title{%s}";
    private static final String SECTION_DELIMITER = "%%*********************\n";
    private static final String SECTION_TEMPLATE = SECTION_DELIMITER
            + "\\section{%s}\n%s";


    private static final String UL_TEMPLATE = "\\begin{itemize}\n"
            + "%s"
            + "\\end{itemize}";

    private static final String OL_TEMPLATE = "\\begin{enumerate}\n"
            + "%s"
            + "\\end{enumerate}";

    private static final String LST_ITEM_TEMPLATE = "\\item %s";

    private static final String IMG_TEMPLATE = "\\includegraphics{%s}";

    private final String mediaPath;

    public TexVisitor(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    @Override
    public String process(Document doc) {
        return String.format(DOC_TEMPLATE,
                mediaPath,
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

    private String iterateChildren(List<? extends DocNode> children) {
        StringBuilder out = new StringBuilder();
        for (DocNode elem : children) {
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
                iterateChildren(card.getFront().getChildren()),
                iterateChildren(card.getBack().getChildren()));
//        return null;
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

    @Override
    public String process(ImageItem img) {
        return String.format(IMG_TEMPLATE, img.getName());
    }

    @Override
    public String process(SideContainer cont) {
        return iterateChildren(cont.getChildren());
    }


}
