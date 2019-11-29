#!/bin/bash

# echo $(pwd)

# generate pdf from tex file
pdflatex "$1"
# pdflatex --interaction=nonstopmode "$1"

# generate it again for table of contents
pdflatex "$1"
# pdflatex --interaction=nonstopmode "$1"

# remove extension
NAME=$(basename $1 .tex)

# delete temp files
shopt -s extglob
rm ${NAME}.!(txt|pdf|tex) >/dev/null

rm *_ex.tex >/dev/null >/dev/null

# preview pdf doc
xdg-open ${NAME}.pdf

