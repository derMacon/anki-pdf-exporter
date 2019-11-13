#!/bin/bash

func_usage() {
(echo "Usage:
exporter.sh -h | exporter.sh --help
  prints this help and exits
exporter.sh INPUT 
  INPUT is a valid latex-File (.tex)")
}


# Pruefung, ob die Parameteranzahl 1 ist, wenn ja:
# Pruefung, ob eines der beiden Hilfe-Flags gesetzt wurde
# ggf. Ausgabe des Hilfetextes, sonst wird ERROR auf 1 gesetzt
if [ "$#" -ne 1 ] || [ "X$1" = "X-h" ] || [ "X$1" = "X--help" ]
then
		func_usage
else
		java -jar .txt-to-tex-exporter.jar ./test.txt

		cd output/
		file_name=$(basename "$1" .txt)
		./../.tex-to-pdf-exporter.sh $file_name.tex
fi

