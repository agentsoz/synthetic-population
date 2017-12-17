#!/usr/bin/env Rscript
library(stringr)
library(tools)


source("DataReadUltraShort.R")
source("util.R")
source("dwellingproperties.R")
source("cleaning.R")
source("estimateSA1HouseholdsUsingSA2.R")
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
pSgeColi = 4
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
      default="Thornbury")
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
  outlist = cleanup(indv,pSAColi, pRelColi, pSexColi, pAgeColi,pValueColi, hhs, hPersonCountColi, hFamilyTypeColi, hValueColi)
  indv = outlist[[1]]
  hhs = outlist[[2]]
  
  #Save the cleaned data files
  saoutpath = paste(outLoc,"/",sa,"/",sep="")
  ifelse(!dir.exists(path=saoutpath),dir.create(path=saoutpath, showWarnings = T, recursive = T),FALSE)
  write.csv(indv,paste(saoutpath,"Indiv.csv",sep=""))
  write.csv(hhs,paste(saoutpath,"Hh.csv",sep=""))
  
  # Secondly, we distirbute SA2 level data among SA1s.
  
  #Find the relavent file with SA1 data from list of SA1sbySA2 files list
  saregex = paste(sa,".(csv|zip)",sep="")
  SA1FileRow= grep(saregex,sa1DistFileslist)
  sa1sfile = paste(sa1bysa2home,sa1DistFileslist[SA1FileRow,1],sep="")
  # Load above selected SA1 info file
  SA1HhsDist = readSA1HouseholdsInSA2(sa1sfile)
 
  #Following code iterates on hh types distributing them among SA1s. i.e each row represent a hh type
  rowcount = nrow(hhs)
  lastcol = ncol(SA1HhsDist)
  for( i in 1:rowcount){
    sa1hhs = SA1HhsDist[(i+1),3:lastcol] #get data cells by skipping row and col headers
    sa1hhsttl = sum(sa1hhs)
    sa2hhttl = hhs[i,4]

    #Distribute SA2 Hhs among SA1s assuming SA2 data is always correct
    if(sa2hhttl == 0){
      #If there are no hhs in SA2 in current row, then there must be no hhs in SA1.
      adjustedSA1Hhs = (sa1hhs*0)
    }else if((sa2hhttl-sa1hhsttl) > 0 & sum(sa1hhs) == 0){
      #If there are extra hhs of current type in SA2, but none in the SA1s, distribute hhs among randomly selected SA1s
      diff = (sa2hhttl-sa1hhsttl)
      adjustedSA1Hhs = sa1hhs
      adjustedSA1Hhs[1,sample(ncol(sa1hhs),size=diff,replace=FALSE)] = 1
      warning(sa, " No households in SA1s, but SA2 has ",sa2hhttl," households - in ", SA1HhsDist[(i+1),1]," ",SA1HhsDist[(i+1),2]," : Placed each of them in a random SA1s")
    } else{
      #Redistribute hhs among SA1 according to the current distribution. At the end of this, total hhs in SA1s match the total in SA2
      adjustedSA1Hhs =fillAccording2Dist(sa1hhs, (sa2hhttl-sa1hhsttl))
    }
    
    SA1HhsDist[(i+1),3:lastcol] = adjustedSA1Hhs
  }
  #Save SA1 hh distribution
  sa1hhsfile = paste(outLoc,sa,"/SA1Hhs.csv",sep="")
  cat("SA1 distribution household distribution matched to SA2 households total\n")
  cat("Updated SA1 household distribution saved to: ",sa1hhsfile,"\n")
  write.csv(SA1HhsDist,sa1hhsfile)
}
warnings()