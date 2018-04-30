# Constructing The Population

The population covers Greater Melbourne area as specifed in Australian Bureau of Statistics (ABS) data. This includes 309 SA2s. The population statistics were obtained using TableBuilder Pro tool provided in Australian Bureau of Statics (ABS) website(www.abs.gov.au). ESRI shapefiles of mesh block area boundaries and SA2 code specifications were also downloaded from ABS website. ESRI shapefiles of building addresses were taken from Vicmap Data provided in www.land.vic.gov.au.

## Prerequisits
- R statistical package (https://www.r-project.org). Required R extra libraries are:
     - stringr
     - optparse
     - Metrics
     - tools
     - testthat

- JAVA 8 (https://java.com/en/download/)
- Maven (https://maven.apache.org/download.cgi)
- ABS TableBuilder Pro access to download data

## Components

### Address Mapper
Locates the SA1 that each address belongs to<br />
The addresses obtained from Vicmaps does not have references to ABS Statistical Areas. This component locates the SA1 of each address by matching `MESH_BLOCK` ID property of address with `MB_CODE16` ID property of ABS mesh block area. If a matching mesh block id cannot be found in ABS mesh block area file, the program searches the polygon that the address point is contained geographically. This adds two new properties to address shape file from Vicmap: `ABS_MB16` - the mesh block id used in ABS mesh block area file and `SA1_MAIN16` - the 11 digit SA1 main code. The default updated file is `synthetic-population/data/melbourne-2016-addresses.zip`. This also generates a `synthetic-population/data/melbourne-2016-addresses.json.gz` with all the addresses.
To run the addressmapper execute following commands. This is a time consuming process and may take about 10 hours for whole Greater Melbourne.

        > cd synthetic-poplation/populationbuilder
        > mvn clean install //This builds to project
        > java -jar addressmapper/target/addressmapper.jar addressmapper.properties

### Preprocesser
Performes data cleaning routines on household and persons data distributions obtained from ABS.<br />
The data downloaded from ABS often have inconsistencies between the households and persons. This component removes these inconsistencies as much as possible using heuristics observed in human populations. The processed files are saved to `synthetic-population/data/melbourne/generated/SA2/preprocessed`. To run the program with already downloaded data exectute below commands. This will complete in about a minute.

        > cd synthetic-poplation/rscripts
        >./sa2preprocess.R

### Population Synthesiser
Synthesises the population using preprocessed ABS census data<br />
This program constructs housholds and families using the census data using heuristics on human relationships. This program infers the relationships and families of persons and put them in the households. This also identifies the Statistical Area (SA2 by default) and randomly assigns an address to the household. The main inputs are preprocessed ABS census data files and the json file giving addresses and the corresponding SA1 codes. The full list of input properties can be found in `population.properties` file.

If the program is not already built execute following command
 
        > cd synthetic-population/populationbuilder
        > mvn clean install
        
To run the program execute

        > cd synthetic-population/populationbuilder
        > java -jar synthesis/target/synthesis.jar population.properties
        
