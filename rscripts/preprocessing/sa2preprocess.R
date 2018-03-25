#!/usr/bin/env Rscript
library(stringr)
library(tools)

source("config.R")
source("datareader.R")
source("util.R")
source("dwellingproperties.R")
source("cleaning.R")
source("estimateSA1HouseholdsUsingSA2.R")

set.seed(randomSeed)
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
    c("-o", "--output"),
    default = "../../data/melbourne/generated/SA2/",
    help = "The path of the output directory. [default= %default]",
    metavar = "DIR"
  ),
  make_option(
    c("--sa2s"),
    help = "The list of SA2s to process. The parameter can be either \"*\" - for all SA2s in household and person input files,  a comma seperated list of SA2 names or a plain text file with one SA2 per line [default= %default]",
    metavar = "LIST_NAMES",
    default = "*"
  ),
  make_option(
    c("-a", "--a"),
    action = "store_true",
    default = FALSE,
    help = "Set this flag to calculate SA1 level household distribution. [default= %default]",
    metavar = "logical"
  ),
  make_option(
    c("--sa1s"),
    default = "../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner_East.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner_South.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_North_East.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_North_West.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Outer_East.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_South_East.zip,
    ../../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_West.zip",
    help = "A list of comma separeted ABS downloaded files giving the SA2s, their SA1s and the number of households in each SA1 by the household types. [default= %default]",
    metavar = "LIST_FILES"
  )
)
script_description = "This script pre-processes the files downloaded from ABS TableBuilder in preparation to be used in population synthesis.
1. Removes any impossible entries in household and person SA2 level population distributions based on population heuristics.
2. Compares SA2 level household and person distribution, and cleans the data based on population heuristics. This part assumes household distribution as the accurate one of the two and person types distribution is updated to match household types distribution.
3. Calculates SA1 level household distribution based on the corresponding SA2 household distribution. The calculation ensures valid household types distributions at SA1 level, but not person type distributions. It may not be possible to calculate SA1 level household distribution in some cases due to lack of data."
opt_parser = OptionParser(option_list = option_list, description = script_description)
opt = parse_args(opt_parser)

#Load household distribution from ABS files
print("Reading households file")
hh_input <- opt$households
hhArr = ReadHouseholds(
  hh_input,
  h_nof_cols,
  h_header_start_col,
  h_value_col,
  h_values_start_row,
  h_sa_col,
  h_nof_persons_col,
  h_family_hh_type_col,
  family_hh_cats,
  hh_sizes
)

#Load persons distribution from ABS files
print("Reading persons file")
ind_input <- opt$persons
indArr = ReadPersons(
  ind_input,
  p_nof_cols,
  p_header_start_col,
  p_value_col,
  p_value_start_row,
  p_sa_col,
  p_rel_col,
  p_sex_col,
  rel_status_cats,
  sex_cats,
  age_cats
)
print("Persons and households file reading complete")

#Read the list of SA2s
isStar <- F
if (opt$sa2s == "*") {
  sa2_list <- unique(hhArr[h_sa_col]$V1)
  isStar <- T
} else if (file.exists(opt$sa2s)) {
  d = read.table(opt$sa2s,
                 sep = "\n",
                 fill = FALSE,
                 strip.white = TRUE)
  sa2_list <- unique(d$V1)
} else{
  sa2_list <- unique(unlist(strsplit(opt$sa2s, ",")))
}

#Verify whether we have input data for the SA2 that we are going to process.
## Get the SA2 lists in persons distribution and households distribution
pSA2s = unique(indArr[p_sa_col]$V1)
hSA2s = unique(hhArr[h_sa_col]$V1)

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
sa1_files <- unlist(lapply(strsplit(sub("\\n","",opt$sa1s), ","),trimws))

errors <-
  matrix(
    NA,
    nrow = length(sa2_list),
    ncol = 2,
    dimnames = list(sa2_list, c("start_error%", "end_error%"))
  )
sa2s_with_no_sa1s = c() #Book-keeping SA2s that have all empty SA1s according to input data.
sa2_count = 0
for (sa2 in sa2_list) {
  sa2_count = sa2_count + 1
  cat("------------ Processing", sa2," (",sa2_count,"/",length(sa2_list),") --------------\n")
  
  # We first clean the data at SA2 level
  indv = ReadBySA(indArr, sa2)
  hhs = ReadBySA(hhArr, sa2)
  
  if ((sum(indv[, p_value_col]) == 0) &
      (sum(hhs[, h_value_col]) == 0)) {
    
  } else{
    ### Clean the data - this function removes descrepancies between individuals file and households file as much as we can. There can be differences
    # even after this
    outlist = clean(
      indv,
      p_sa_col,
      p_rel_col,
      p_sex_col,
      p_age_col,
      p_value_col,
      hhs,
      h_nof_persons_col,
      h_family_hh_type_col,
      h_value_col
    )
    indv = outlist[[1]]
    hhs = outlist[[2]]
    errors[sa2,] = c(outlist[[3]], outlist[[4]])
    
    colnames(indv)[p_sa_col] <- "SA"
    colnames(indv)[p_rel_col] <- "Relationship status"
    colnames(indv)[p_sex_col] <- "Sex"
    colnames(indv)[p_age_col] <- "Age"
    colnames(indv)[p_value_col] <- "Persons count"
    
    colnames(hhs)[h_sa_col] <- "SA"
    colnames(hhs)[h_nof_persons_col] <- "Household Size"
    colnames(hhs)[h_family_hh_type_col] <- "Family household type"
    colnames(hhs)[h_value_col] <- "Households count"
    
    #Save the cleaned data files
    saoutpath = paste(out_loc, sa2,"/", sep = "")
    
    pgz <- gzfile(paste(saoutpath, persons_file_name, sep = ""))
    CreateDir(dirname(summary(pgz)$description))
    write.csv(indv, pgz, row.names = FALSE)
    
    hgz <- gzfile(paste(saoutpath, households_file_name, sep = ""))
    CreateDir(dirname(summary(hgz)$description))
    write.csv(hhs, hgz, row.names = FALSE)
    
    cat("Processing  ",sa2_count,"/",length(sa2_list),"\r")
    ## distirbute SA2 level data among SA1s.
    if (do_sa1) {
      cat("Estimating SA1 households distribution ...\r")
      # Load above selected SA1 info file
      raw_sa1_hh_dist = ReadSA1HouseholdsInSA2(sa1_files, sa2, 14, 8)
      
      adjusted_sa1_hh_dist = EstimateSA1HouseholdsDistribution(sa2,  hhs, raw_sa1_hh_dist)
      
      if(!is.null(adjusted_sa1_hh_dist)){
        #Save SA1 hh distribution
        sa1hhsgzfile <- gzfile(paste(saoutpath, sa1_households_file_name, sep = ""))
        cat("SA1 distribution household distribution matched to SA2 households total\n")
        cat("Updated SA1 household distribution saved to: ",
            summary(sa1hhsgzfile)$description,
            "\n")
        CreateDir(dirname(summary(sa1hhsgzfile)$description))
        write.csv(adjusted_sa1_hh_dist, sa1hhsgzfile, row.names = FALSE)
      }else{
        sa2s_with_no_sa1s = c(sa2s_with_no_sa1s, sa2)
        cat("All SA1s are empty in this SA2             \n")
      }
    }
  }
}
cat("===============================================================\n")
cat("\nEmpty SA2s\n")
print(unlist(rownames(errors[is.na(errors[, "start_error%"]),])))
errors <- subset(errors, !is.na(errors[, "start_error%"]))
cat("\nSA2s above 5% error\n")
cat("The difference between the number of persons in household distirbution and persons in distributions as a percentage of persons in household distribution. (-) values indicate persons distribution having more persons than household distribution and (+) values indicate the opposite.\n")
high_error <-
  errors[c(abs(errors[, "start_error%"]) >= 5 &
             abs(errors[, "end_error%"]) >= 5),]
if (length(high_error) > 0) {
  print(high_error)
} else{
  print("None")
}

if (do_sa1) {
  cat("\nSA2s where all SA1s are empty, thus no SA1 distribution constructed\n")
  if (length(sa2s_with_no_sa1s) > 0) {
    print(sa2s_with_no_sa1s)
  } else{
    print("None")
  }
}

cat("\nOutput files are saved under: ", out_loc, "\n")
cat("Done!")
