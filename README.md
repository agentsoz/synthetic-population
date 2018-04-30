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
        
## Constructing a new population

### 1. Download data using ABS TableBuilder tool

ABS TableBuilder and tutorial can be accessed from this [link](http://www.abs.gov.au/websitedbs/D3310114.nsf/Home/2016%20TableBuilder). You need to know how to create and manage tables, change databases, create custom data fields and download data tables in csv format.

   * Number of persons by Relationship Status (RLHP) by Sex (SEXP) by Age group (AGE5P) by SA2. 

     Construct the below table using "Counting Persons, Place of Enumeration" database. Codes used for table headers are used in TableBuilder. Remove `Total` fields from the table by clicking on "Hide Total" button if it is already selected, and save the table as `synthetic-population/data/melbourne/raw/Persons_2016_Greater_Melbourne_SA2.zip`.
```
|---------------------------| Persons, Place of Usual Residence |
| SA2 | RLHP | SEXP | AGE5P |                                   |
|-----|------|------|-------|-----------------------------------|
|     |      |      |       |                 x                 |
|     |      |      |       |                 x                 |
```
This tool uses custom  Relationship status and Age groups. Custom categories can be formed by combining existing categories in TableBuilder's "My Custom Data" section. The relationship categories are given in `synthetic-population/doc/Custom Individual relationship categories.pdf`. Age groups used here are 0-14, 15-24, 25-34, 35-49, 50-64, ... , 85-99, 100++. 
      
  * Number of households by Number of persons in household (NPRD) by Family Household Composition (Dwelling) (HCFMD). 

    Use Construct below table using "Counting Dwellings, Place of Enumeration" database and save as `synthetic-population/data/melbourne/raw/Households_2016_Greater_Melbourne_SA2.zip`. This table should not have any `Total` fields

```
|--------------------| Dwellings, Location on census night |
| SA2 | NPRD | HCFMD |                                     |
|-----|------|-------|-------------------------------------|
|     |      |       |                  x                  |
|     |      |       |                  x                  |
```   
         
   * Household composition distributions of each SA2 by SA1s in it. 

     Construct below table in TableBuilder using "Dwellings, Location on Census Night" database for all SA2s and save the files. It may require downloading data as multiple files. For example `synthetic-population/data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner.zip`. Remove `Total` fields from the table before saving. Both zip and csv are acceptable for this table.

```
|-----------|------| SA1s  | SA1_CODE1 | SA1_CODE2 | SA1_CODE3 |  ...  |
| SA2 Name  | NPRD | HCFMD |           |           |           |  ...  |
|-----------|------|-------|-----------|-----------|-----------|-------|
| Brunswick |      |       |     x     |     x     |     x     | ..x.. |
|           |      |       |     x     |     x     |     x     | ..x.. |
```   

  * Age distribution of persons in SA2s. 

    Construct the below table using "Counting Persons, Place of Usual Residence" database and save as `synthetic-population/data/melbourne/raw/Persons_percentage_by_age_2016_Greater_Melbourne_SA2s.zip`. Keep `Total` column shown in below structure and remove `Total` row from AGEP column.

```
| SA2s | Brunswick | Brunswick East | Brunswick West |
| AGEP |           |                |                |
|------|-----------|----------------|----------------|
|      |     x     |        x       |       x        |
|      |     x     |        x       |       x        |
```

### 2. Download required Australian Statistical Geography Standard (ASGS) data cubes

  Download following 2016 files from www.abs.gov.au/AUSSTATS/abs@.nsf/DetailsPage/1270.0.55.001July%202016 . Save them in `data/melbourne/raw/`. 
  
   * [Victoria Mesh Blocks ASGS Ed 2016 Digital Boundaries in ESRI Shapefile Format ](http://www.abs.gov.au/AUSSTATS/subscriber.nsf/log?openagent&1270055001_mb_2016_vic_shape.zip&1270.0.55.001&Data%20Cubes&04F12B9E465AE765CA257FED0013B20F&0&July%202016&12.07.2016&Latest)<br />
   This file covers Victoria and files for other states and teritories can also be downloaded
   
   * [Statistical Area Level 2 (SA2) ASGS Edition 2016 in .csv Format ](http://www.abs.gov.au/ausstats/subscriber.nsf/log?openagent&1270055001_sa1_2011_aust_csv.zip&1270.0.55.001&Data%20Cubes&5AD36D669F284E70CA257801000C69BE&0&July%202011&23.12.2010&Latest)<br />
   This file convers whole Australia. So no need to download again if the popuation is built at SA2 level. If a different SA level is used download the corresponding file.

### 3. Download building address shape files from Vicmap Data website

   1. Building Addresses shape files can be downloaded at www.data.vic.gov.au/data/dataset/address-vicmap-address. The already downloaded addresses shapefiles include Local Government Areas (LGAs) covering Greater Melbourne. The Greater Melbourne area accordig to ABS is larger than area covered by Greater Melbourne Metropoliton LGAs. So the downloaded LGAs include additional LGAs that are outside Greater Melbourne Metropoliton area. The 3 already downloaded address files are `SDM494419.zip`, `SDM494198.zip` and `SDM494202.zip` in `synthetic-population/data/raw/`. It is recomended that address files are downloaded as smaller files as above instead of a one large file covering whole Greater Melbourne area because of memory limitations.
  
