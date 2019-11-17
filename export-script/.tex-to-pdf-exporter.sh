#!/bin/bash

# generate pdf from tex file
pdflatex "$1"

# delete temp files
shopt -s extglob
rm test.!(txt|pdf|tex)
rm *_ex.tex

# preview pdf doc
xdg-open ./test.pdf


