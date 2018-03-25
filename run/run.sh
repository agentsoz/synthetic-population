#!/bin/bash
cd ../datapreprocessing
./sa2preprocess.R
cd ../populationbuilder
mvn clean install
java -jar synthesis/target/synthesis.jar population.properties
java -jar BuildingProperties/target/buildingproperties.jar Buildings.properties
