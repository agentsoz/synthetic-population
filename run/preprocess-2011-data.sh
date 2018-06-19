#!/bin/bash

#This scripts calls sa2preprocess.R script with parameters for 2011 census data. This only generates population at SA2 level. SA1 level distributions are not generated.
RSCRIPTDIR="../rscripts/"
CDIR=`pwd`

cd $RSCRIPTDIR &&
echo "Change dir:" $RSCRIPTDIR &&
./sa2preprocess.R --households ../data/melbourne-2011/raw/Households_2011_Greater_Melbourne_SA2.zip --persons ../data/melbourne-2011/raw/Persons_2011_Greater_Melbourne_SA2.zip --output ../data/melbourne-2011/generated/SA2/ --errorfile ../data/melbourne-2011/analysis/cleaning_error.csv &&
cd $CDIR &&

