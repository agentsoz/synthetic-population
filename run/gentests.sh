#!/usr/bin/env bash

DIR=$(dirname "$0")

RANDOOPJAR=randoop-all-3.1.5.jar
if [ ! -f $DIR/$RANDOOPJAR ] ; then
	cd $DIR && wget https://github.com/randoop/randoop/releases/download/v3.1.5/$RANDOOPJAR
fi

if [ "$#" -ne 1 ]; then
	echo "usage: $0 CLASSNAME"
	exit
fi

FULLCLASS=$1
PACKAGE=$(basename "$FULLCLASS")
CLASS="${PACKAGE##*.}"
PACKAGE="${PACKAGE%.*}"

CP=${DIR}/../populationbuilder/synthesis/target/synthesis.jar:${DIR}/$RANDOOPJAR
JCMD="java -cp ${CP} randoop.main.Main"

CMD="$JCMD gentests \
	--junit-package-name=$PACKAGE \
	--error-test-basename=${CLASS}ErrorTest \
	--regression-test-basename=${CLASS}RegressionTest \
	--testclass ${FULLCLASS}"

echo $CMD
eval $CMD
