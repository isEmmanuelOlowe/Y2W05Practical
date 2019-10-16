#!/bin/bash
JUNITPATH=$TESTDIR/../lib
FILES=$(find . -name '*.java' ! -path './staffprivatetest/*')
javac -Xlint:none -cp "$JUNITPATH/*:." $FILES
