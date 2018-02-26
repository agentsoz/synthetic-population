# Preprocessing ABS Data Files

The data files downloaded from ABS contain random errors introduced to protect privacy. `sa2preprocess.R` script removes the errors using heuristics. We assume household information given in ABS data is more accurate than individual level information. First we adjust household level data to remove any discrepencies. Then individual data is adjusted to match household level data. There can still be differences between individual and household level data. Such cases are handled when constricuting the synthetic population.

## Prerequisits
Install following list of R libraries:

* Metrics
* stringr
* optparse
* testthat
* tools

Libraries can be installed using the R shell using something like:
```
$ install.packages("Metrics")
```

## Run Instructions

To run with default settings, change directory to `./rscripts/preprocessing/` and run following command:

```
> ./sa2preprocess.R
```

**Add something about the format of the generated CSV files here. Include column headers and their meanings**

Other commandline arguments

```
Usage: ./sa2preprocess.R [options]
This script pre-processes the files downloaded from ABS TableBuilder in preparation to be used in population synthesis. 
1. Removes any impossible entries in household and person SA2 level population distributions based on population heuristics.
2. Compares SA2 level household and person distributions, and cleans the data based on population heuristics. This part assumes household distribution as the accurate one of the two and person types distribution is updated to match the requirements of household types distribution.
3. Calculates SA1 level household distribution based on the corresponding SA2 household distribution. The calculation ensures valid household types distributions at SA1 level, but not person type distributions. It may not be possible to calculate SA1 level household distribution in some cases due to lack of data.

Options:
	--households=FILE
		Household data file from ABS. The file can be either a zip or a csv. [default= ../../data/melbourne/raw/Households_2016_Greater_Melbourne_SA2.zip]

	--persons=FILE
		Person data file from ABS. The file can be either a zip or a csv. [default= ../../data/melbourne/raw/Persons_2016_Greater_Melbourne_SA2.zip]

	-o DIR, --output=DIR
		The path of the output directory. [default= ../../data/melbourne/processed/SA2/]

	--sa2s=LIST_IDS
		The list of SA2s to process. The parameter can be either "*" - for all SA2s in household and person input files,  a comma seperated list of SA2 names or a plain text file with one SA2 per line [default= *]

	-a, --a
		Set this flag to calculate SA1 level household distribution. [default= FALSE]

	--sa1s=LIST_FILES
		A list of comma separeted ABS downloaded files giving the SA2s, their SA1s and the number of households in each SA1 by the household types. [default= ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner_East.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner_South.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_North_East.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_North_West.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Outer_East.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_South_East.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_West.zip]

	-h, --help
		Show this help message and exit
```

## Input files from ABS TableBuilder

Default input files are located in `synthetic-population/data/melbourne/raw/` directory. Default location for the processed files is `synthetic-population/data/melbourne/processed/SA2/`.

## Using different statistical areas

The scripts can be used to construct populations of different statistical areas by replacing the SA2 data files with the new statistical area's files. The scripts can be used as they are if the new downloaded data csv files have the same structure as the ones used for SA2s here.

ABS has the habbit of adding and removing rows and columns with descriptive titles when they release new data after a census. These changes usually affects older data sets as well which could render these scripts unusable. This can be avoided by updating row and column indices configurations in `config.R` file. Indices in this file gives which columns and rows should be read in the csv to obtain a certian data.
