#!/bin/bash
bash build.sh
javac -cp "tests/junit-4.12.jar:tests/hamcrest-core-1.3.jar:." tests/*.java
java -cp "tests/junit-4.12.jar:tests/hamcrest-core-1.3.jar:." org.junit.runner.JUnitCore tests.GameTests
