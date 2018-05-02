# Preprocessing ABS Data Files

The data files downloaded from ABS contain random errors introduced to protect privacy. `sa2preprocess.R` script removes the errors using heuristics. We assume household information given in ABS data is more accurate than individual level information. First we adjust household level data to remove any discrepencies. Then individual data is adjusted to match household level data. There can still be differences between individual and household level data. Such cases are handled when constricuting the synthetic population.

Given the SA1 level disaggregated household distribution within each SA2, the program can also be used to remove descrepencies between each SA2's aggregated household distribution and disaggregated SA1 level household distribution of the given SA2. In this case SA1 level household distribution is adjusted proportionally to match the total number of households in the corresponding SA2. This part of the program is triggered by setting `-a` flag and providing relavent inputs as described below.

## Prerequisits
Install following list of R libraries:

* Metrics
* stringr
* optparse
* testthat
* futile.logger
* tools
* lsa

Libraries can be installed using the R shell using something like:
```
$ install.packages("Metrics")
```

## Run Instructions

To run with default settings, change directory to `./rscripts/` and run following command:

```
> ./sa2preprocess.R
```

**Add something about the format of the generated CSV files here. Include column headers and their meanings**

Other commandline arguments

```
Usage: ./sa2preprocess.R [options]
This script pre-processes the files downloaded from ABS TableBuilder in preparation to be used in population synthesis.
1. Removes any impossible entries in household and person SA2 level population distributions based on population heuristics.
2. Compares SA2 level household and person distribution, and cleans the data based on population heuristics. This part assumes household distribution as the accurate one of the two and person types distribution is updated to match household types distribution.
3. Calculates SA1 level household distribution based on the corresponding SA2 household distribution. The calculation ensures valid household types distributions at SA1 level, but not person type distributions. It may not be possible to calculate SA1 level household distribution in some cases due to lack of data.

Options:
	--households=FILE
		Household data file from ABS. The file can be either a zip or a csv. [default= ../data/melbourne/raw/Households_2016_Greater_Melbourne_SA2.zip]

	--persons=FILE
		Person data file from ABS. The file can be either a zip or a csv. [default= ../data/melbourne/raw/Persons_2016_Greater_Melbourne_SA2.zip]

	--output=DIR
		The path of the output directory. [default= ../data/melbourne/generated/SA2/]

	--sa2s=LIST_NAMES
		The list of SA2s that constitutes the area for which the population is to be generated. The parameter can be either "*" - for all SA2s in household and person input files,  a comma seperated list of SA2 names or a plain text file with one SA2 per line [default= *]

	--dosa1
		Set this flag to calculate SA1 level household distribution. [default= TRUE]

	--sa1hh=LIST_FILES
		A list of comma separeted ABS downloaded files giving the SA2s, their SA1s and the number of households in each SA1 by the household types. [default= ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner_East.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner_South.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_North_East.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_North_West.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Outer_East.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_South_East.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_West.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Mornington_Peninsula.zip]

	--sa2codemap=FILE
		The csv file from ABS giving SA2_NAME_2016 and the corresponding SA2_5DIGITCODE_2016. [default= ../data/melbourne/raw/1270055001_sa2_2016_aust_csv.zip]

	-h, --help
		Show this help message and exit
```

## The generated files

The script generates 3 output files describing person types and household types in the population. The generated files are located under `/data/melbourne/generated/SA2/<SA2 Name>/preprocessed` directory. `<SA2 Name>` represents one of the SA2s in the target area.
* person_types.csv.gz  
This file contains the distribution of persons types within an SA2. The person types are represented by following column   headers:
   * SA2 - The name of the SA2 which persons belong to
   * Relationsihp Status - The relationship to other members of the household. The relationship types are: Married, Lone parent, Dependent under 15 child (U15Child), Dependent student, Non-dependent over 15 child (O15Child), Group household, Lone person, Relative )
   * Sex - The sex of the persons
   * Age - Uses following age categories: 0-15, 16-24, 25-39, 40-54, 55-69, 70-84, 85-99, 100 or over
   * Persons count - The number of persons
   
* household_types.csv.gz
This file contains the distribution of household types within an SA2. The file has following columns
   * SA2 - The name of the SA2 which households belong to
   * Household size - The number of persons that can live in an household. The values range from one person to eight or more persons.
   * Family Household type -  Describes the number of families in the household and the type of the primary family. There can be upto 3 families living the same household and the primar family can be eithe Couple family with child, Couple family with no child, One parent family or Other family. In addition to that household can fall under Group households or Lone person households.
   * Households count - The number of households.
   
 * sa1_household_types.csv.gz
 This files contains the distribution of household types by SA1s within an SA2. The file has following columns
   * SA2 - The name of the SA2 which households belong to
   * Household size - The number of persons that can live in an household. The values range from one person to eight or more persons.
   * Family Household type -  Describes the number of families in the household and the type of the primary family. There can be upto 3 families living the same household and the primar family can be eithe Couple family with child, Couple family with no child, One parent family or Other family. In addition to that household can fall under Group households or Lone person households.
   * List of SA1s - This is the list of SA1s in the SA2 that this file represents. The number of in each SA1 is given under these columns.

## Using different statistical areas

The scripts can be used to construct populations of different statistical areas by replacing the SA2 data files with the new statistical area's files. The scripts can be used as they are if the new downloaded data csv files have the same structure as the ones used for SA2s here.

ABS has the habbit of adding and removing rows and columns with descriptive titles when they release new data after a census. These changes usually affects older data sets as well which could render these scripts unusable. This can be avoided by updating row and column indices configurations in `config.R` file. Indices in this file gives which columns and rows should be read in the csv to obtain a certian data.
