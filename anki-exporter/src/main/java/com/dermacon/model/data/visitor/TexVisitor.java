package com.dermacon.model.data.visitor;

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
            + "\n"
            + "\\begin{document}\n"
            + "%s\n"
            + "\\end{document}\n"
            + "\n";

    private static final String TITLE_TEMPLATE = "\\title{%s}\n";

    private static final String SECTION_DELIMITER = "%%-------------------------\n";
    private static final String SECTION_TEMPLATE = SECTION_DELIMITER
            + "\\section{%s}\n%s\n\n";


    String output = "";

    @Override
    public void process(Document doc) {
        String title = String.format(TITLE_TEMPLATE, doc.getHeader().getTitle());
        output = String.format(DOC_TEMPLATE, title, output);
    }

    @Override
    public void process(Header header) {
        // todo
    }

    @Override
    public void process(Body body) {

    }

    @Override
    public void process(Section section) {
        output += String.format(SECTION_TEMPLATE, section.getValue(), output);
    }

    @Override
    public void process(PlainText text) {
        output += text.getValue() + "\n\n";
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
