# Building the LATCH ABM Population 

This project allocates dwelling properties to buildings in Darebin and Banyule considering buildings geographical location and distribution of dwelling properties among households given in ABS Census data.

## Build Instructions

There are 4 maven projects (geo, filemanager, util and BuildingProperties) that needs to be built. They are all subprojects of `populationbuilder` project. To build all the projects at once change directory to `buildpopulation/populationbuilder/` and execute below command.

        > mvn clean install

## Run Instructions

To run the code and generate the latch population, change directory to `buildpopulation/populationbuilder/BuildingProperties/` and do:

        > java -jar target/buildingproperties.jar Buildings.properties
 
## Importing the project into Eclipse

This project is nested under `populationbuilder` parent project. We have to import this project as a child project of `populationbuilder` parent project. Follow below instructions to import all the required projects into Eclipse.

   1. File > Import...
   2. In Import console, select Maven> Existing Maven Projects > Next
   3. In Import Maven Project console, set `buildpopulation/populationbuilder` as the Root Directory. This will show available projects
   4. Select only `populationbuilder` and its child projects
   5. Click Finish
  
This will automatically create the required Eclipse configuration files (.project, .classpath, .settings) and import all the projects under `populationbuilder`.

When checking in any changes to the repository do not push .project, .classpath and .settings. Otherwise there will be conflicts when using different Eclipse versions.

## buildings.properties file instructions

This file contains all the properties for constructing the popoulation

  * `SA1PolygonsShapefile` - Shape file of SA1 polygons
  * `FilterSA1sByProperty` - By what attribute should SA1 features (an element in shape file) be filtered when reading. This has to be one of the column names in shape file's Attribute Table. If no attribute is specified, then all the features are read.
  * `FilterSA1sByValues` - SA1 features that has these values for FilterSA1sByProperty are read in. If no values are specified all the features are read. Accepts comma seperated values, e.g. To only read SA1s that are in Darebin - North, Darebin - South, Banyule SA3s, set `FilterSA1sByProperty=SA3_NAME11` and `FilterSA1sByValues=Darebin - North, Darebin - South, Banyule`. To read all SA1s in Australia leave one of the two properties unset, `FilterSA1sByProperty=`.
  * `RefereceKeyInSA1Shapefile` - Data field to be used as unique code for SA1 polygons
  * `BuildingsShapeFileHome` - Location of building shape files. This can be a directory containing zip files or a zip file
  * `BuildingsShapeFileName` - Name of the .shp file inside building zip files (e.g. ADDRESS.shp) 
  * `FilterBuildingsByProperty` - By what attribute should features be filtered when reading. If no attribute is specified, then all the features are read.
  * `FilterBuildingsByValues` - Features that has these values for FilterBuildingsByProperty are read in.
  * `BuildingUniqueKey` - An attribute in building feature that can be used to uniquely identify a building
  * `AreaNameKey` - An attribute in building representing the shape file it belongs to. Mainly used for logging purposes.
  * `SACodesZip` - File containin all SA1 details (../../../data/latch/raw/All-Aus-SA1/1270055001_sa1_2011_aust_csv.zip)
  * `SACodesCsvInSACodesZip` - Csv file in the SACodesZip (SA1_2011_AUST.csv)
  * `ReferenceColumnHeader` - Column to be used as reference (SA1_7DIGITCODE_2011)
  * `TargetColumnHeader` - Column to be used as target column (SA1_MAINCODE_2011)
  * `DwellingPropertyFile` - Dwelling properties zip downloaded from ABS TableBuilder (buildpopulation/data/latch/raw/SA1, BEDD, STRD, TENLLD Tenure and Landlord Type and NPRD.zip)
  * `NewForeignKeyInAddressRecord` - SA1 identifier to be used for buildings when assigning them to relaven SA1 (SA1_MAINCODE_2011)
  * `TemporaryOutputDirectory`- Temporary file location. Use `system` to use system default
  * `OutputFile` - Name of the output file (e.g. buildpopulation/data/latch/locationaldata/BuildingsCumSA1andDwellingProperties.json.gz)
  * `RandomSeed` - Seed for random number generator
  
## Output file

The output file contains a list of buildings in json format. File can be viewed using zcat or zless command line tools. 
```
{
    "features": [
        {
            "geometry": {
                "coordinates": [
                    145.0808,
                    -37.6993
                ],
                "type": "Point"
            },
            "properties": {
                "ADD_CLASS": "S",
                "BEDD": "2 bedroom",
                "BUILDING_TYPE": "RESIDENTIAL",
                "CENSUS_HHSIZE": "Three persons",
                "EZI_ADD": "150 CAMERON PARADE WATSONIA NORTH 3087",
                "LGA_CODE": "303",
                "LOCALITY": "WATSONIA NORTH",
                "POSTCODE": "3087",
                "SA1_7DIG11": "2120409",
                "SA1_MAINCODE_2011": "20901120409",
                "STATE": "VIC",
                "STRD": "Detached House",
                "TENLLD": "Private Renter"
            }
        },
      ...
      ],
      "wkt": "GEOGCS[\"GCS_GDA_1994\", \n  DATUM[\"D_GDA_1994\", \n    SPHEROID[\"GRS_1980\", 6378137.0, 298.257222101]], \n  PRIMEM[\"Greenwich\", 0.0], \n  UNIT[\"degree\", 0.017453292519943295], \n  AXIS[\"Longitude\", EAST], \n  AXIS[\"Latitude\", NORTH]]"
}
```
This json file has two root elements: `features` and `wkt`. 

Features root element gives the list of buildings that are within the SA1s specified by `FilterSA1sByValues` configuration property. Each building element has a set of `properties` and a `geometry`. 

Properties element gives a map of buildings address details and dwelling properties if the building is a residential building. If the building is a non-residential building then dwelling properties are set to null. 

Geometry element gives the `type` of the geometry (e.g. Point, Multipolygon etc.) and a list of `coordinates` representing the building.

WKT root element gives the Coordinate Reference System used for `geometry\coordinates`.