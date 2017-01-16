#Preprocessing ABS Data Files

The data files downloaded from ABS contain random errors introduced to protect privacy of persons. `sa2preprocess.R` script removes the errors using heuristics. We assume household information given in ABS data is more accurate than individual level information. First we adjust household level data to remove any discrepencies. Then individual data is adjusted to match household level data. Still there can be differences between individual and household level data. Such cases are handled when constricuting the synthetic population.

## Prerequisits
Install following list of R libraries

* Metrics
* stringr
* optparse

`$ANONDATA_HOME` environment variable set to `anonymous/data/` directory.

e.g. on linux execute: `export ANONDATA_HOME="<path to anonymous repository location>/data/"`

## Run Instructions

To run with default settings, change directory to `anonymous/sources/RProjects/latch/populationconstruction/` and run following command:

```
#!shell
> ./sa2preprocess.R
```

Other commandline arguments

```
#!shell
Usage: ./sa2preprocess.R [options]


Options:
	-hi CHARACTER, --householdinput=CHARACTER
		Household data from ABS[default= $ANONDATA_HOME/latch/raw/SA2, NPRD and HCFMD.csv]

	-ii CHARACTER, --individualinput=CHARACTER
		Individual data from ABS[default= $ANONDATA_HOME/latch/raw/SA2, RLHP Relationship in Household, SEXP and AGE5P.csv]

	-sa1tosa2 CHARACTER, --sa1bysa2home=CHARACTER
		Household distribution in SA1 by SA2s [default= $ANONDATA_HOME/latch/raw/Hh-SA1-in-each-SA2/]

	-o CHARACTER, --output=CHARACTER
		output file location [default= $ANONDATA_HOME/latch/absprocessed/SA2/]

	-sa2 CHARACTER, --sa2list=CHARACTER
		list of SA2s to process [default= Alphington - Fairfield,Northcote,Thornbury,Bundoora - East,Greensborough,Heidelberg - Rosanna,Heidelberg West,Ivanhoe,Ivanhoe East - Eaglemont,Montmorency - Briar Hill,Viewbank - Yallambie,Watsonia,Kingsbury,Preston,Reservoir - East,Reservoir - West]

	-h, --help
		Show this help message and exit
```

## Input files from ABS TableBuilder

Default input files are located in `anonymous/data/latch/raw/` directory. Default location for the processed files is `anonymous/data/latch/absprocessed/SA2/`.