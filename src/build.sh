#!/bin/bash
echo "Builing Project: hexalpha"

cd hexalpha
shopt -s globstar
javac **/*.java
cd ..
jar cvfm HexAlpha.jar manifest.txt hexalpha
