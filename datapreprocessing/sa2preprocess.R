#!/usr/bin/env Rscript
library(stringr)
library(tools)


source("./DataReadUltraShort.R")
source("util.R")
source("dwellingproperties.R")
source("cleaning.R")
source("estimateSA1HouseholdsUsingSA2.R")
source("ipfpCalculation.R")

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

hhArr = readHouseholds(hhinput)
indArr = readIndividuals(indinput)
sa1DistFileslist = matrix(list.files(sa1bysa2home))

for (sa in sa2list) {
  cat("------------ Processing", sa," --------------\n")
  indv = readBySA(indArr,sa)
  hhs = readBySA(hhArr,sa)
  
  hhSArwid = getMatchingRowIds(hhArr,1,sa)
  indSArwid = getMatchingRowIds(indArr,1,sa)
  
  outlist = cleanup(indv,hhs)
  indv = outlist[[1]]
  hhs = outlist[[2]]

  hhArr[hhSArwid,4] = hhs[,4]
  
  saoutpath = paste(outLoc,"/",sa,"/",sep="")
  ifelse(!dir.exists(path=saoutpath),dir.create(path=saoutpath, showWarnings = T, recursive = T),FALSE)
  write.csv(indv,paste(saoutpath,"Indiv.csv",sep=""))
  write.csv(hhs,paste(saoutpath,"Hh.csv",sep=""))
  
  if(TRUE){
    cat("Running IPFP..... ")
    hhSizeOfEachRow = rep(c(1:8),each =14)
    ttlIndividuals = sum(hhs[,4]*hhSizeOfEachRow)
    seed = seedLatchAgeLatchRelSex(length(hhs[,4]), length(indv[,5]))
    hhinds <- (hhs[,4]*hhSizeOfEachRow)
    ipfresult = ipfpCalculate((hhs[,4]*hhSizeOfEachRow),indv[,5],seed)
  
    colnames(ipfresult$p.hat) <- 0:(ncol(ipfresult$p.hat)-1)
    rownames(ipfresult$p.hat) <- 0:(nrow(ipfresult$p.hat)-1)
    cat("\rIPFP complete, saving data..... ")
    write.csv(ipfresult$p.hat, paste(saoutpath,"ipfraw.csv",  sep=""))
    
    write.csv(smart.round(ipfresult$p.hat*ttlIndividuals), paste(saoutpath,"ipfresult.csv",  sep=""))
    
    colnames(seed) <- 0:(ncol(seed)-1)
    rownames(seed) <- 0:(nrow(seed)-1)
    write.csv(seed,paste(saoutpath,"seed.csv",sep=""))
  }
  
  indv[,5] <- smart.round((indv[,5]/sum(indv[,5]))*ttlIndividuals)
  write.csv(indv, paste(saoutpath,"Indivround.csv",sep=""))

  saregex = paste(sa,".(csv|zip)",sep="")
  SA1FileRow= grep(saregex,sa1DistFileslist)
  
  sa1sfile = paste(sa1bysa2home,sa1DistFileslist[SA1FileRow,1],sep="")
  SA1HhsDist = readSA1HouseholdsInSA2(sa1sfile)
 
  rowcount = nrow(hhs)
  lastcol = ncol(SA1HhsDist)
  for( i in 1:rowcount){
    sa1hhs = SA1HhsDist[(i+1),3:lastcol]
    sa1hhsttl = sum(sa1hhs)
    sa2hhttl = hhs[i,4]

    if(sa2hhttl == 0){
      adjustedSA1Hhs = (sa1hhs*0)
    }else if((sa2hhttl-sa1hhsttl) > 0 & sum(sa1hhs) == 0){
      diff = (sa2hhttl-sa1hhsttl)
      adjustedSA1Hhs = sa1hhs
      adjustedSA1Hhs[1,sample(ncol(sa1hhs),size=diff,replace=FALSE)] = 1
      warning(sa, " No households in SA1s, but SA2 has ",sa2hhttl," households - in ", SA1HhsDist[(i+1),1]," ",SA1HhsDist[(i+1),2]," : Placed each of them in a random SA1s")
    } else{
      adjustedSA1Hhs =fillAccording2Dist(sa1hhs, (sa2hhttl-sa1hhsttl))
    }
    
    SA1HhsDist[(i+1),3:lastcol] = adjustedSA1Hhs
  }
  sa1hhsfile = paste(outLoc,sa,"/SA1Hhs.csv",sep="")
  cat("SA1 distribution household distribution matched to SA2 households total\n")
  cat("Updated SA1 household distribution saved to: ",sa1hhsfile,"\n")
  write.csv(SA1HhsDist,sa1hhsfile)
}
warnings()