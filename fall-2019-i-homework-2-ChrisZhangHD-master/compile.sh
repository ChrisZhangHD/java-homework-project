#!/usr/bin/env bash

# TODO - ensure `target/classes` exists (creating it if it doesn't)
# TODO - compile all the Java files within the project and output them into `target/classes`

mkdir target
mkdir target/classes
javac -d target/classes -sourcepath src/main/java src/main/java/edu/nyu/cs9053/homework2/*.java  