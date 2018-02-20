#!/usr/bin/env Rscript
library(stringr)
library(tools)

source("DataReadUltraShort.R")
source("util.R")
source("dwellingproperties.R")
source("cleaning.R")
source("estimateSA1HouseholdsUsingSA2.R")

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
  make_option(
    c("--households"),
    type = "character",
    default = "../../data/melbourne/raw/Households_2016_Greater_Melbourne_SA2.zip",
    help = "Household data file from ABS. The file can be either a zip or a csv. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("--persons"),
    default = "../../data/melbourne/raw/Persons_2016_Greater_Melbourne_SA2.zip",
    help = "Person data file from ABS. The file can be either a zip or a csv. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("-o","--output"),
    default = "../../data/melbourne/processed/SA2/",
    help = "The path of the output directory. [default= %default]",
    metavar = "DIR"
  ),
  make_option(
    c("--sa2s"),
    help = "The list of SA2s to process. The parameter can be either \"*\" - for all SA2s in household and person input files,  a comma seperated list of SA2 names or a plain text file with one SA2 per line [default= %default]",
    metavar = "LIST_IDS",
    default = "*"
  ),
  make_option(
    c("-a","--a"),
    action = "store_true",
    default = FALSE,
    help = "Set this flag to calculate SA1 level household distribution. [default= %default]",
    metavar = "logical"
  ),
  make_option(
    c("--sa1s"),
    default = "../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_Inner.zip,
    ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_Inner_East.zip,
    ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_Inner_South.zip,
    ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_North_East.zip,
    ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_North_West.zip,
    ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_Outer_East.zip,
    ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_South_East.zip,
    ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_West.zip",
    help = "A list of comma separeted ABS downloaded files giving the SA2s, their SA1s and the number of households in each SA1 by the household types. [default= %default]",
    metavar = "LIST_FILES"
  )
)
script_description = "This script pre-processes the files downloaded from ABS TableBuilder in preparation to be used in population synthesis. 
1. Removes any impossible entries in household and person SA2 level population distributions based on population heuristics.
2. Compares SA2 level household and person distribution, and cleans the data based on population heuristics. This part assumes household distribution as the accurate one of the two and person types distribution is updated to match household types distribution.
3. Calculates SA1 level household distribution based on the corresponding SA2 household distribution. The calculation ensures valid household types distributions at SA1 level, but not person type distributions."
opt_parser = OptionParser(option_list = option_list, description = script_description)
opt = parse_args(opt_parser)

#Load household distribution from ABS files
hhinput <- opt$households
print(hhinput)
hhArr = ReadHouseholds(
  hhinput,
  hNofCols,
  hColHeaderStartingCol,
  hValueColi,
  hValuesStartingRow,
  hSAColi,
  hPerCountColi,
  hFamilyTypeColi
)

#Load persons distribution from ABS files
indinput <- opt$persons
indArr = ReadPersons(
  indinput,
  pNofCols,
  pColHeaderStartingCol,
  pValueColi,
  pValuesStartingRow,
  pSAColi,
  pRelColi,
  pSexColi
)

#Read the list of SA2s
isStar <- F
if (opt$sa2s == "*") {
  sa2_list <- unique(hhArr[hSAColi]$V1)
  isStar <- T
} else if (file.exists(opt$sa2s)) {
  d = read.table(opt$sa2s,
                 sep = "\n",
                 fill = FALSE,
                 strip.white = TRUE)
  sa2_list <- unique(d$V1)
} else{
  sa2_list <- unique(unlist(strsplit(opt$sa2list, ",")))
}

#Verify whether we have input data for the SA2 that we are going to process.
## Get the SA2 lists in persons distribution and households distribution
pSA2s = unique(indArr[pSAColi]$V1)
hSA2s = unique(hhArr[hSAColi]$V1)

## Check wheather SA2s in the sa2_list are available in the households distribution. If the user has specifed * for --sa2list option, this will match anyway.
not_found_sa2s = sa2_list[which(!(sa2_list %in% hSA2s))]
if (length(not_found_sa2s) > 0) {
  not_found_sa2s_str = paste(not_found_sa2s, collapse = ", ")
  stop(
    paste(
      "Following SA2s are not found in input households distribution:",
      not_found_sa2s_str
    )
  )
}
## Check wheather SA2 in the sa2_list are available in the persons distribution.
not_found_sa2s = sa2_list[which(!(sa2_list %in% pSA2s))]
if (length(not_found_sa2s) > 0) {
  not_found_sa2s_str = paste(not_found_sa2s, collapse = ", ")
  stop(
    paste(
      "Following SA2s are not found in input persons distribution:",
      not_found_sa2s_str
    )
  )
}
## Following checks whether persons input has more SA2s than households distribution. This check is only needed if the user has given * option (because
## we take SA2s in household file as the sa2_list)
if (isStar) {
  not_found_sa2s = hSA2s[which(!(pSA2s %in% hSA2s))]
  
  if (length(not_found_sa2s) > 0) {
    not_found_sa2s_str = paste(not_found_sa2s, collapse = ", ")
    stop(
      paste(
        "Following SA2s are not found in input household distribution:",
        not_found_sa2s_str
      )
    )
  }
}

out_loc <- opt$output
do_sa1 <- opt$a
sa1_files <- unlist(strsplit(opt$sa1s, ","))

start_errors <- list()
for (sa2 in sa2_list) {
  cat("------------ Processing", sa2, " --------------\n")
  
  # We first clean the data at SA2 level
  indv = ReadBySA(indArr, sa2)
  hhs = ReadBySA(hhArr, sa2)
  
  ### Clean the data - this function removes descrepancies between individuals file and households file as much as we can. There can be differences
  # even after this
  outlist = cleanup(
    indv,
    pSAColi,
    pRelColi,
    pSexColi,
    pAgeColi,
    pValueColi,
    hhs,
    hPerCountColi,
    hFamilyTypeColi,
    hValueColi
  )
  indv = outlist[[1]]
  hhs = outlist[[2]]
  start_errors[[sa2]] = outlist[[3]]
  
  #Save the cleaned data files
  saoutpath = paste(out_loc, "/", sa2, "/", sep = "")
  ifelse(
    !dir.exists(path = saoutpath),
    dir.create(
      path = saoutpath,
      showWarnings = T,
      recursive = T
    ),
    FALSE
  )
  
  colnames(indv)[pSAColi] <- "SA"
  colnames(indv)[pRelColi] <- "Relationship status"
  colnames(indv)[pSexColi] <- "Sex"
  colnames(indv)[pAgeColi] <- "Age"
  colnames(indv)[pValueColi] <- "Persons count"
  colnames(hhs)[hSAColi] <- "SA"
  colnames(hhs)[hPerCountColi] <- "Household Size"
  colnames(hhs)[hFamilyTypeColi] <- "Family household type"
  colnames(hhs)[hValueColi] <- "Households count"
  
  pgz <- gzfile(paste(saoutpath, "persons.csv.gz", sep = ""))
  hgz <- gzfile(paste(saoutpath, "households.csv.gz", sep = ""))
  write.csv(indv, pgz)
  write.csv(hhs, hgz)
  
  sa1_start_coli = 4
  ## distirbute SA2 level data among SA1s.
  if (do_sa1) {
    # Load above selected SA1 info file
    SA1HhsDist = readSA1HouseholdsInSA2(sa1_files, sa2,14,8)
    
    #Following code iterates on hh types distributing them among SA1s. i.e each row represent a hh type
    rowcount = nrow(hhs)
    lastcol = ncol(SA1HhsDist)
    for (i in 1:rowcount) {
      sa1hhs = as.numeric(SA1HhsDist[i, sa1_start_coli:lastcol]) #get data cells by skipping row and col headers
      sa1hhsttl = sum(sa1hhs)
      sa2hhttl = hhs[i, 4]
      
      #Distribute SA2 Hhs among SA1s assuming SA2 data is always correct
      if (sa2hhttl == 0) {
        #If there are no hhs in SA2 in current row, then there must be no hhs in SA1.
        adjustedSA1Hhs = (sa1hhs * 0)
      } else if ((sa2hhttl - sa1hhsttl) > 0 & sum(sa1hhs) == 0) {
        #If there are extra hhs of current type in SA2, but none in the SA1s, distribute hhs among randomly selected SA1s
        diff = (sa2hhttl - sa1hhsttl)
        adjustedSA1Hhs = sa1hhs
        adjustedSA1Hhs[1, sample(ncol(sa1hhs), size = diff, replace = FALSE)] = 1
        warning(
          sa2,
          " No households in SA1s, but SA2 has ",
          sa2hhttl,
          " households - in ",
          SA1HhsDist[i, 1],
          " ",
          SA1HhsDist[i, 2],
          " : Placed each of them in a random SA1s"
        )
      } else{
        #Redistribute hhs among SA1 according to the current distribution. At the end of this, total hhs in SA1s match the total in SA2
        adjustedSA1Hhs = fillAccording2Dist(sa1hhs, (sa2hhttl - sa1hhsttl))
      }
      
      SA1HhsDist[i, sa1_start_coli:lastcol] = adjustedSA1Hhs
    }
    #Save SA1 hh distribution
    sa1hhsfile = paste(out_loc, sa2, "/SA1Hhs.csv", sep = "")
    cat("SA1 distribution household distribution matched to SA2 households total\n")
    cat("Updated SA1 household distribution saved to: ",
        sa1hhsfile,
        "\n")
    write.csv(SA1HhsDist, sa1hhsfile)
  }
}
print(start_errors)
cat("Output files are saved under: ", out_loc, "\n")
warnings()