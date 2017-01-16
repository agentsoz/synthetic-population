#!/bin/bash
cd ../datapreprocessing
./sa2preprocess.R
cd ../populationbuilder
mvn clean install
cd latchpop
java -jar target/latchpop.jar population.properties
cd ../BuildingProperties
java -jar target/buildingproperties.jar Buildings.properties
