#!/bin/bash

start=`date +%s`

DIR=`dirname "$0"`
CDIR=`pwd`

cd $DIR/../rscripts &&
sudo Rscript -e 'install.packages("stringi", dependencies=TRUE, INSTALL_opts = c("--no-lock"), repos="https://cran.csiro.au/")' &&
sudo Rscript -e 'install.packages(c("devtools","testthat","optparse","Metrics","futile.logger"), repos="https://cran.csiro.au/")' &&
cd ../populationbuilder &&
mvn clean install &&
echo "Environment setup and build complete! Now you can take a very quick break! " &&
cd ../rscripts &&
./sa2preprocess.R &&
cd ../populationbuilder &&
java -jar synthesis/target/synthesis.jar population.properties &&
java -Xmx4g -jar addressmapper/target/addressmapper.jar addressmapper.properties -sh &&
cd ../data &&
echo "1. Introduction
----------------
This archive contains the Greater Melbourne synthetic population constructed based on 2016 Australian Census data obtained from Australian Bureau of Statistics (http://abs.gov.au). The population was generated using a tool developed by RMIT University's Agents Group and the source code is available at https://github.com/agentsoz/synthetic-population.

2. Generated Population
------------------------
The population represents persons, families and households of each SA2 within the Greater Melbourne area. There are 5 files under each SA2, which are located under 'melbourne/generated/SA2/<SA2 Name>/population' directories in the archive. <SA2 Name> represents one of the SA2s in the target area.
'persons.csv.gz' gives all the persons, 'families.csv.gz' gives all the families and 'households.csv.gz' gives all the households within a particular SA2. All entities in the files have IDs unique to the whole Greater Melbourne population.
'output_person_types.csv.gz' and 'output_household_types.csv.gz' files gives a summary (marginal) distribution of persons by their person types and households by household types respectively.
These files can be opened using most of the archiving softwares. Perfome following steps to view these files after extracting the containing zip.

Linux:
Option 1: excute the command: 'zcat <file name.csv.gz>' to view on the terminal.
Option 2: Right click on the file and select 'Extract Here'. Then open the extracted csv file using a Text Editor or Calc

Windows:
Install WinRar software or any other compatible tool and extract the '.gz' files. The extracted csv can be opened with Notepad or Microsoft Excel

Mac:
execute the command: 'gunzip <filename.csv.gz>' on the terminal. This will convert the file to a csv ('filename.csv'), which can be opened as a text file or a spreadsheet using a software like Microsoft Excel.

3. Taking the population of a subset of SA2s
---------------------------------------------
Every person, family and household given in the 'persons.csv.gz', 'families.csv.gz' and 'households.csv.gz' files has a unique ID. Because of that all the persons of a subset of SA2s can be easily taken by creating a new sheet on the prefered spreadsheet tool (MsExcel or Calc) and copying the rows in each 'persons.csv.gz' to the new sheet.
In more detail:
1. Open a new sheet in Ms Excel
2. Open the extracted 'persons.csv' of one of the SA2s (assuming 'persons.csv.gz' is already extracted using a tool like WinRar) on another Ms Excel window.
3. Copy and paste the rows in 'persons.csv' to the empty Ms Excel sheet.
4. Open the extracted 'persons.csv' of the next SA2 on a new Ms Excel window
5. Copy all the person rows in the newly opened file (step 4) except for the title row and append them to the persons rows of the file created in step 1 by pasting.
6. Repeat steps 4 and 5 for all the SA2s in the subset.

" > README.txt &&
zip -r melbourne-2016-population.zip melbourne-2016/generated/SA2/*/population/* README.txt &&
rm README.txt &&
cd $CDIR

echo "Duration: $((($(date +%s)-$start)/60)) minutes"
