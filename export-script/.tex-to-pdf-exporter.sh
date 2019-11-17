#!/bin/bash

echo hier
echo $(pwd)

# generate pdf from tex file
pdflatex "$1"

# remove extension
NAME=$(basename $1 .tex)

# delete temp files
shopt -s extglob
rm ${NAME}.!(txt|pdf|tex) >/dev/null

rm *_ex.tex >/dev/null >/dev/null

# preview pdf doc
xdg-open ${NAME}.pdf

