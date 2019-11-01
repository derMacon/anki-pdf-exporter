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
            + "\n";

    private static final String BODY_TEMPLATE = "\\begin{document}\n"
                    + "%s\n"
                    + "\\end{document}\n";

    private static final String HEADER_TEMPLATE = "\\title{%s}\n%s";
    private static final String SECTION_DELIMITER = "%%*********************\n";
    private static final String SECTION_TEMPLATE = SECTION_DELIMITER
            + "\\section{%s}\n%s\n\n";

    private static final String CARD_DELIMITER = "%%---------------------\n";
    private static final String CARD_TEMPLATE = CARD_DELIMITER
            + "\\begin{tcolorbox}"
            + "[colback=white!10!white,colframe=lightgray!75!black,\n"
            + "  savelowerto=\\jobname_ex.tex]\n"
            + "\n"
            + "  \\begin{center}\n"
            + "    %s\n"
            + "  \\end{center}\n"
            + "\n"
            + "  \\tcblower\n"
            + "\n"
            + "  \\justifying\n"
            + "  %s\n"
            + "\n"
            + "\\end{tcolorbox}\n";

    String output = "";

    @Override
    public void process(Document doc) {
        output = String.format(DOC_TEMPLATE, output);
    }

    @Override
    public void process(Header header) {
        output = String.format(HEADER_TEMPLATE, header.getTitle(), output);
    }

    @Override
    public void process(Body body) {
        output = String.format(BODY_TEMPLATE, output);
    }

    @Override
    public void process(Section section) {
//        String childrenOutput =
//                section.getChildren().stream().forEach(e -> process(e));
//        output += String.format(SECTION_TEMPLATE, section.getValue(),
//                process(section.getChildren()));
    }

    @Override
    public void process(Card card) {
        output += String.format(CARD_TEMPLATE, card.getFront(),
                card.getBack());
    }

    @Override
    public String process(PlainText text) {
        return text.getValue() + "\n\n";
    }

    @Override
    public void process(OrderedList lstItem) {

    }

    @Override
    public void process(UnorderedList lstItem) {

    }

    @Override
    public void process(ListItem lstItem) {

    }

    @Override
    public String getResult() {
        return output;
    }
}
