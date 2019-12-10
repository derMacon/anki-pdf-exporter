package com.dermacon.model.data.visitor;

import com.dermacon.model.data.nodes.DocNode;
import com.dermacon.model.data.nodes.document.Card;
import com.dermacon.model.data.nodes.document.headings.Paragraph;
import com.dermacon.model.data.nodes.document.headings.SubParagraph;
import com.dermacon.model.data.nodes.document.headings.SubSection;
import com.dermacon.model.data.nodes.document.headings.SubSubSection;
import com.dermacon.model.data.nodes.sideElem.BoldItem;
import com.dermacon.model.data.nodes.sideElem.ImageItem;
import com.dermacon.model.data.nodes.sideElem.ItalicItem;
import com.dermacon.model.data.nodes.sideElem.ListItem;
import com.dermacon.model.data.nodes.sideElem.OrderedList;
import com.dermacon.model.data.nodes.sideElem.PlainText;
import com.dermacon.model.data.nodes.document.headings.Section;
import com.dermacon.model.data.nodes.sideElem.SideContainer;
import com.dermacon.model.data.nodes.sideElem.UnderlinedItem;
import com.dermacon.model.data.nodes.sideElem.UnorderedList;
import com.dermacon.model.data.nodes.document.Body;
import com.dermacon.model.data.nodes.document.Document;
import com.dermacon.model.data.nodes.document.MetaHeader;

import java.util.List;

/**
 * Visitor implementation for generating a latex document.
 *
 * Why the visitor pattern:
 * The visitor will be passed to the top level node of a data structure that
 * should be converted. This structure will internally call the accept method
 * of the visitor. Since java is a single dispach language it must be handled
 * this way and the visitor cannot process / accept the node directly because
 * the jvm always assumes the static type when dealing with overloaded methods.
 */
public class TexVisitor implements FormatVisitor<String> {

    private static final String DOC_TEMPLATE = "\\documentclass{article}\n"
            + "\\usepackage{tikz,lipsum,lmodern}\n"
            // german captions in table of contents
            + "\\usepackage[ngerman]{babel}\n"
            + "\\usepackage[most]{tcolorbox}\n"
            + "\\usepackage[paperheight=10.75in,paperwidth=7.25in,margin=1in,heightrounded]{geometry}\n"
            + "\\usepackage{graphicx}\n"
            + "\\usepackage{blindtext}\n"
            + "\\usepackage{ragged2e}\n"
            // headings always in first half of page
            + "\\usepackage{needspace}\n"
            + "\\usepackage[space]{grffile}\n"
            + "\\usepackage[utf8]{inputenc}\n"
            + "\\usepackage[export]{adjustbox}\n" // https://tex.stackexchange.com/questions/86350/includegraphics-maximum-width
            + "\\usepackage{hyperref}\n"
            + "\\hypersetup{\n" // make table of contents clickable
            + "    colorlinks,\n"
            + "    citecolor=black,\n"
            + "    filecolor=black,\n"
            + "    linkcolor=black,\n"
            + "    urlcolor=black\n"
            + "}"
            + "\n"
            + "\\usepackage{fancyhdr}\n"
            + "\\pagestyle{fancy}\n"
            + "\\fancyhf{}\n"
            + "\\rhead{\\rightmark}\n"
            + "\\chead{\\thepart}\n"
            + "\\lhead{\\nouppercase{\\leftmark}}\n"
            + "\\cfoot{\\thepage}"
            + "\n"
            + "\\graphicspath{{\"%s\"}}\n"
            + "\n"
            + "%s\n"
            + "\n"
            + "%s\n"
            + "\n";

    private static final String BODY_TEMPLATE =
            "\\begin{document}\n"
                    + "\\maketitle\n"
                    + "\\vspace{0.5cm}\n"
                    + "\\tableofcontents\n"
                    + "\\clearpage\n"
                    + "\n"
                    + "%s\n"
                    + "\\end{document}\n";

    private static final String CARD_DELIMITER = "%%---------------------\n";

    // resource: http://texdoc.net/texmf-dist/doc/latex/tcolorbox/tcolorbox.pdf
    // page: 366
    private static final String CARD_TEMPLATE = CARD_DELIMITER
            + "\\begin{tcolorbox}"
            + "[colback=white!10!white,colframe=lightgray!75!black,\n"
            + "  savelowerto=\\jobname_ex.texri,breakable,enhanced,"
            + "lines before break=40,arc=2pt,outer arc=2pt]\n"
            + "\n"
            + "\\justifying\n"
            + "%s\n"
            + "\n"
            + "\\tcblower\n"
            + "\n"
            + "\\justifying\n"
            + "%s\n"
            + "\\end{tcolorbox}\n";

    private static final String HEADER_TEMPLATE =
            "\\title{%s}\n"
                    + "\\author{Silas Hoffmann, inf103088}\n"
                    + "\\date{\\today}\n"
                    + "\n";

    private static final String SECTION_DELIMITER = "%%*********************\n";
    private static final String NEEDSPACE_COMMAND =
            "\\Needspace{35\\baselineskip}\n";

    private static final String SECTION_TEMPLATE = SECTION_DELIMITER
            + NEEDSPACE_COMMAND
            + "\\section{%s}\n%s";
    private static final String SUBSECTION_TEMPLATE =
            NEEDSPACE_COMMAND
            + "\\subsection{%s}\n%s";
    private static final String SUBSUBSECTION_TEMPLATE =
            NEEDSPACE_COMMAND
            + "\\subsubsection{%s}\n%s";
    private static final String PARAGRAPH_TEMPLATE =
            NEEDSPACE_COMMAND
            + "\\paragraph{%s}\n\\vspace{3px}\n%s";
    private static final String SUBPARAGRAPH_TEMPLATE =
            NEEDSPACE_COMMAND
            + "\\subparagraph{%s}\n%s";

    private static final String BOLD_TEMPLATE = "\\textbf{%s}";
    private static final String UNDERLINED_TEMPLATE = "\\underline{%s}";
    private static final String ITALIC_TEMPLATE = "\\textit{%s}";

    private static final String UL_TEMPLATE = "\\begin{itemize}\n"
            + "%s"
            + "\\end{itemize}";

    private static final String OL_TEMPLATE = "\\begin{enumerate}\n"
            + "%s"
            + "\\end{enumerate}";

    private static final String LST_ITEM_TEMPLATE = "\\item %s";

    private static final String IMG_TEMPLATE =
            "\\begin{center}\n"
                    + "\\includegraphics[max width=.9\\textwidth]{%s}\n"
                    + "\\end{center}\n";

    @Override
    public String process(Document doc) {
        return String.format(DOC_TEMPLATE,
                doc.getMediaPath(),
                doc.getMetaHeader().accept(this),
                doc.getBody().accept(this));
    }

    @Override
    public String process(MetaHeader metaHeader) {
        return String.format(HEADER_TEMPLATE, metaHeader.getTitle());
    }

    @Override
    public String process(Body body) {
        return String.format(BODY_TEMPLATE,
                iterateChildren(body.getChildren()));
    }

    /**
     * Iterates over a given list of elements extending the most top level
     * DocNode class and appends the generated output when accepting the
     * visitor object to a StringBuilder instance.
     * @param children list of elements extending the most top level DocNode
     *                 class
     * @return String containing the latex representation of the children of
     * the current node.
     */
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
    public String process(SubSection section) {
        return String.format(SUBSECTION_TEMPLATE,
                section.getValue(),
                iterateChildren(section.getChildren()));
    }

    @Override
    public String process(SubSubSection section) {
        return String.format(SUBSUBSECTION_TEMPLATE,
                section.getValue(),
                iterateChildren(section.getChildren()));
    }

    @Override
    public String process(Paragraph section) {
        return String.format(PARAGRAPH_TEMPLATE,
                section.getValue(),
                iterateChildren(section.getChildren()));
    }

    @Override
    public String process(SubParagraph section) {
        return String.format(SUBPARAGRAPH_TEMPLATE,
                section.getValue(),
                iterateChildren(section.getChildren()));
    }

    @Override
    public String process(Card card) {
        return String.format(CARD_TEMPLATE,
                iterateChildren(card.getFront().getChildren()),
                iterateChildren(card.getBack().getChildren()));
    }

    @Override
    public String process(SideContainer cont) {
        return iterateChildren(cont.getChildren());
    }

//    @Override
//    public String process(List<AnkiTag> tags) {
//         todo
//        return "test tag";
//    }

    @Override
    public String process(UnderlinedItem item) {
        return String.format(UNDERLINED_TEMPLATE,
                iterateChildren(item.getChildren()));
    }

    @Override
    public String process(ItalicItem item) {
        return String.format(ITALIC_TEMPLATE,
                iterateChildren(item.getChildren()));
    }

    @Override
    public String process(BoldItem item) {
        return String.format(BOLD_TEMPLATE,
                iterateChildren(item.getChildren()));
    }

    @Override
    public String process(PlainText text) {
        return text.getValue();
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
        return String.format(IMG_TEMPLATE, img.getName().trim());
    }

}
