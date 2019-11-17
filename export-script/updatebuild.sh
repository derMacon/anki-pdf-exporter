#!/bin/bash

echo builds the java project and copies the generated jar to the working dir.

cd ../anki-tex-generator/
mvn clean compile assembly:single
OLD_NAME=anki-exporter-1.0-SNAPSHOT-jar-with-dependencies.jar
cp ./target/${OLD_NAME} ../export-script/

NEW_NAME=txt-to-tex-exporter.jar
cd ../export-script/
rm ${NEW_NAME}
mv ${OLD_NAME} ${NEW_NAME}
chmod +x *

