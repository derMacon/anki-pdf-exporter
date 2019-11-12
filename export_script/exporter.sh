#!/bin/bash


func_usage() {
(echo "Usage:
exporter.sh -h | exporter.sh --help
  prints this help and exits
exporter.sh INPUT 
  INPUT is a valid latex-File (.tex)")
}


# die Variable ERROR wird zum speichern des Fehlercodes verwendet,
# initial mit 0 belegt
ERROR=0

# Pruefung, ob die Parameteranzahl 1 ist, wenn ja:
# Pruefung, ob eines der beiden Hilfe-Flags gesetzt wurde
# ggf. Ausgabe des Hilfetextes, sonst wird ERROR auf 1 gesetzt

if [ "$#" -eq 1 ]
then
    if [ "X$1" = "X-h" ] || [ "X$1" = "X--help" ]
    then
        func_usage
    else
				java -jar .txt-to-tex-exporter.jar ./test.txt

				cd output/
				./../.tex-to-pdf-exporter.sh ./test.tex
    fi
else 
		func_usage
fi


