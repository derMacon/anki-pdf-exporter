#!/bin/bash

# Test exporter stops render with error and only renders once overall

MEDIA_DIR="/home/silasUser/.local/share/Anki2/User 1/collection.media/"

func_usage() {
(echo "Usage:
exporter.sh -h | exporter.sh --help
  prints this help and exits

exporter.sh INPUT 
  INPUT is a valid deck file (.txt generated by anki)

if not working please check if media path is correct:")
echo "  " ${MEDIA_DIR}
}


# Checks if there is only one arg and if the help is desired or not
if [ "$#" -ne 1 ] || [ "X$1" = "X-h" ] || [ "X$1" = "X--help" ]
then
	func_usage
else
	# generate the fully qualified input path
	FQ_INPUT="$(realpath $1)"

	# remove extension, but keep fully qualified input path
	FQ_FILE=$(basename ${FQ_INPUT} .txt)

	# generate output directory
	OUTPUT_DIR="${FQ_FILE}-export/"

	# get the script directory
	SCRIPT_DIR="$(realpath $(dirname $0))"

	# delete last generated output
	rm -rf ${OUTPUT_DIR}

	# generate tex file
	java -jar ${SCRIPT_DIR}/.txt-to-tex-exporter.jar ${FQ_INPUT} ${OUTPUT_DIR} "${MEDIA_DIR}"

	cd ${OUTPUT_DIR}
	# echo dir:  ${SCRIPT_DIR}
	bash ${SCRIPT_DIR}/.test_tex-to-pdf-exporter.sh $FQ_FILE.tex

	#>/dev/null 
fi

