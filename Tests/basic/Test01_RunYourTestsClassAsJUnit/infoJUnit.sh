#!/bin/bash
JUNITPATH=$TESTDIR/../../lib

java -cp "$JUNITPATH/*:." org.junit.platform.console.ConsoleLauncher --disable-ansi-colors --details=tree -p=test | grep --invert-match -e 'at [osj][rua][gnv].[jr][ue][nf][il][te]'
