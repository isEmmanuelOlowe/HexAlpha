#!/bin/bash
./build.sh
javac -cp junit-4.12.jar:. Tests/*.java
java -cp junit-4.12.jar:. Tests.GameTests
