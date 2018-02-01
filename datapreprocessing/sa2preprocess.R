#!/usr/bin/env Rscript
library(stringr)
library(tools)

source("DataReadUltraShort.R")
source("util.R")
source("dwellingproperties.R")
source("cleaning.R")
source("ipfpCalculation.R")

#ABS csv file specifics
## Persons file
pNofCols = 6
pColHeaderStartingCol = 1
pRowHeaderStartingRow = 11
pValuesStartingRow = 12

pSAColi = 1
pRelColi = 2
pSexColi = 3
pAgeColi = 4
pValueColi = 5

## Households file
hNofCols = 5
hColHeaderStartingCol = 1
hRowHeaderStartingRow  = 11
hValuesStartingRow = 12

hSAColi = 1
hPerCountColi = 2
hFamilyTypeColi = 3
hValueColi = 4
# End ABS csv file specifics


option_list = list(
  make_option(c("-hi", "--householdinput"), type="character", default="../data/latch/raw/SA2, NPRD HCFMD.csv", help="Household data from ABS[default= %default]",metavar="character"),
  make_option(c("-ii", "--individualinput"), type="character", default="../data/latch/raw/SA2, RLHP SEXP AGE5P.csv", help="Individual data from ABS[default= %default]", metavar="character"),
  make_option(c("-sa1tosa2", "--sa1bysa2home"), type="character", default="../data/latch/raw/Hh-SA1-in-each-SA2/", help="Household distribution in SA1 by SA2s [default= %default]",metavar="character"),
  make_option(c("-o", "--output"), type="character", default="../data/latch/absprocessed/SA2/", help="output file location [default= %default]", metavar="character"),
  make_option(c("-sa2", "--sa2list"), type="character", help="list of SA2s to process [default= %default]", metavar="character",
      default="Alphington - Fairfield,Northcote,Thornbury,Bundoora - East,Greensborough,Heidelberg - Rosanna,Heidelberg West,Ivanhoe,Ivanhoe East - Eaglemont,Montmorency - Briar Hill,Viewbank - Yallambie,Watsonia,Kingsbury,Preston,Reservoir - East,Reservoir - West")
  ); 
opt_parser = OptionParser(option_list=option_list);
opt = parse_args(opt_parser);

hhinput <- opt$householdinput
indinput<-opt$individualinput
sa1bysa2home<-opt$sa1bysa2home
outLoc <- opt$output
sa2list <- unlist(strsplit(opt$sa2list,","))

#Load data from ABS csv files
hhArr = readHouseholds(hhinput,hNofCols, hColHeaderStartingCol, hValueColi, hValuesStartingRow, hSAColi, hPerCountColi, hFamilyTypeColi)
indArr = readPersons(indinput,pNofCols, pColHeaderStartingCol, pValueColi, pValuesStartingRow, pSAColi, pRelColi, pSexColi)
sa1DistFileslist = matrix(list.files(sa1bysa2home)) # List of SA1 information files

for (sa in sa2list) {
  cat("------------ Processing", sa," --------------\n")

  # We first clean the data at SA2 level
  indv = readBySA(indArr,sa)
  hhs = readBySA(hhArr,sa)

  # Clean the data - this function removes descrepancies between individuals file and households file as much as we can. There can be differences
  # even after this
  outlist = cleanup(indv,pSAColi, pRelColi, pSexColi, pAgeColi,pValueColi, hhs, hPerCountColi, hFamilyTypeColi, hValueColi)
  indv = outlist[[1]]
  hhs = outlist[[2]]
  
  #Save the cleaned data files
  saoutpath = paste(outLoc,"/",sa,"/",sep="")
  ifelse(!dir.exists(path=saoutpath),dir.create(path=saoutpath, showWarnings = T, recursive = T),FALSE)
  write.csv(indv,paste(saoutpath,"Indiv.csv",sep=""))
  write.csv(hhs,paste(saoutpath,"Hh.csv",sep=""))
}
cat("Output files are saved under: ",outLoc,"\n")
warnings()