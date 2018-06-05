#!/usr/bin/env Rscript
library(stringr)
library(tools)
library(optparse)
library(futile.logger)

start_time <- Sys.time()

source("config.R")
source("datareader.R")
source("util.R")
source("cleaning.R")
source("estimateSA1HouseholdsUsingSA2.R")

l <- flog.logger("ROOT", INFO, appender=appender.file('sa2preprocess.log'))

set.seed(randomSeed)
option_list = list(
  make_option(
    c("--households"),
    default = "../data/melbourne/raw/Households_2016_Greater_Melbourne_SA2.zip",
    help = "Household data file from ABS. The file can be either a zip or a csv. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("--persons"),
    default = "../data/melbourne/raw/Persons_2016_Greater_Melbourne_SA2.zip",
    help = "Person data file from ABS. The file can be either a zip or a csv. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("--output"),
    default = "../data/melbourne/generated/SA2/",
    help = "The path of the output directory. [default= %default]",
    metavar = "DIR"
  ),
  make_option(
    c("--sa2s"),
    help = "The list of SA2s that constitutes the area for which the population is to be generated. The parameter can be either \"*\" - for all SA2s in household and person input files,  a comma seperated list of SA2 names or a plain text file with one SA2 per line [default= %default]",
    metavar = "LIST_NAMES",
    default = "*"
  ),
  make_option(
    c("--dosa1"),
    action = "store_true",
    default = F,
    help = "Set this flag to calculate SA1 level household distribution. [default= %default]",
    metavar = "LOGICAL"
  ),
  make_option(
    c("--sa1hh"),
    default = "../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner_East.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner_South.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_North_East.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_North_West.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Outer_East.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_South_East.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_West.zip,
    ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Mornington_Peninsula.zip",
    help = "A list of comma separeted ABS downloaded files giving the SA2s, their SA1s and the number of households in each SA1 by the household types. Only applicable if --dosa1 flag is set. [default= %default]",
    metavar = "LIST_FILES"
  ),
  make_option(
    c("--sa2codemap"),
    default = "../data/melbourne/raw/1270055001_sa2_2016_aust_csv.zip",
    help = "The csv file from ABS giving SA2_NAME_2016 and the corresponding SA2_5DIGITCODE_2016. Required to find the SA1 in an SA2. Only applicable if --dosa1 flag is set. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("--errorfile"),
    default = "../data/melbourne/analysis/cleaning_error.csv",
    help = "The csv file to save error percentage before and after cleaning. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("--rawfile"),
    default = F,
    help = "Set this flag to save raw ABS input distributions of each SA2 as seperate files. [default= %default]",
    metavar = "LOGICAL"
  )
)
script_description = "This script pre-processes the files downloaded from ABS TableBuilder in preparation to be used in population synthesis.
1. Removes any impossible entries in household and person SA2 level population distributions based on population heuristics.
2. Compares SA2 level household and person distribution, and cleans the data based on population heuristics. This part assumes household distribution as the accurate one of the two and person types distribution is updated to match household types distribution.
3. Calculates SA1 level household distribution based on the corresponding SA2 household distribution. The calculation ensures valid household types distributions at SA1 level, but not person type distributions. It may not be possible to calculate SA1 level household distribution in some cases due to lack of data."
opt_parser = OptionParser(option_list = option_list, description = script_description)
opt = parse_args(opt_parser)

#Load household distribution from ABS files
cat("Reading households file\n")
flog.info("Reading households file")
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
cat("Reading persons file\n")
flog.info("Reading persons file")
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

cat("Read the list of SA2s\n")
flog.info("Read the list of SA2s")
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
  sa2_list <- unique(trimws(unlist(strsplit(opt$sa2s, ","))))
}

save_raw=opt$rawfile

#Verify whether we have input data for the SA2 that we are going to process.
## Get the SA2 lists in persons distribution and households distribution
pSA2s = unique(trimws(indArr[p_sa_col]$V1))
hSA2s = unique(trimws(hhArr[h_sa_col]$V1))

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
do_sa1 <- opt$dosa1
sa2_code_map_file <-opt$sa2codemap

if(do_sa1){
  sa1_files <- unlist(lapply(strsplit(sub("\\n","",opt$sa1hh), ","),trimws))
  # Load SA1 household distribution of this SA2
  cat("Loading all SA1 household distributions\n")
  flog.debug("Loading all SA1 household distributions")
  all_sa1_hh_dists = LoadSA1HouseholdsInSA2(sa1_files)
}
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
  
  flog.info("------ Processing %s (%d/%d) ------", sa2,sa2_count,length(sa2_list))
  cat("Processing ",sa2_count,"/",length(sa2_list),"SA2s\r")
  
  #First check whether SA1 household distribution is available if --dosa1 flag is true
  if(do_sa1){
    raw_sa1_hh_dist = GetSA1HouseholdDistInSA2(all_sa1_hh_dists, sa2, length(family_hh_cats), length(hh_sizes),sa2_code_map_file)
    if(is.null(raw_sa1_hh_dist)){
      cat("Skipping",sa2,"- cannot find SA1 household distribution\n")
      flog.info("User has set --dosa1 flag to %s but the SA1 household distributions are not found in --sa1hh files list", do_sa1, sa2)
      next
    }
  }
  
  # We first clean the data at SA2 level
  indv = ReadBySA(indArr, sa2)
  rownames(indv) <- c(1:nrow(indv))
  indv = OrderAgeDescending(indv)
  hhs = ReadBySA(hhArr, sa2)
  rownames(hhs)<- c(1:nrow(hhs))

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
    cln_indv = outlist[[1]]
    cln_hhs = outlist[[2]]
    errors[sa2,] = c(outlist[[3]], outlist[[4]])

    colnames(cln_indv)[p_sa_col] <- "SA"
    colnames(cln_indv)[p_rel_col] <- "Relationship status"
    colnames(cln_indv)[p_sex_col] <- "Sex"
    colnames(cln_indv)[p_age_col] <- "Age"
    colnames(cln_indv)[p_value_col] <- "Persons count"

    colnames(cln_hhs)[h_sa_col] <- "SA"
    colnames(cln_hhs)[h_nof_persons_col] <- "Household Size"
    colnames(cln_hhs)[h_family_hh_type_col] <- "Family household type"
    colnames(cln_hhs)[h_value_col] <- "Households count"

    #Save the cleaned data files
    saoutpath = paste(out_loc, sa2,"/", sep = "")

    pgz <- gzfile(paste(saoutpath, persons_file_name, sep = ""))
    CreateDir(dirname(summary(pgz)$description))
    write.csv(cln_indv, pgz, row.names = FALSE)

    hgz <- gzfile(paste(saoutpath, households_file_name, sep = ""))
    CreateDir(dirname(summary(hgz)$description))
    write.csv(cln_hhs, hgz, row.names = FALSE)
    
    if(save_raw){
      raw_pgz = gzfile(paste(saoutpath, raw_persons_file_name, sep = ""))
      CreateDir(dirname(summary(raw_pgz)$description))
      colnames(indv) <- c("SA","Relationship status","Sex","Age","Persons count")
      write.csv(indv, raw_pgz, row.names = FALSE)
      
      raw_hgz = gzfile(paste(saoutpath, raw_households_file_name, sep = ""))
      CreateDir(dirname(summary(raw_hgz)$description))
      colnames(hhs) <- c("SA","Household Size","Family household type","Households count")
      write.csv(hhs, raw_hgz, row.names = FALSE)
    }

    ## distirbute SA2 level data among SA1s.
    if (do_sa1) {
      flog.info("Estimating SA1 households distribution...")
      
      adjusted_sa1_hh_dist = EstimateSA1HouseholdsDistribution(sa2,  cln_hhs, raw_sa1_hh_dist)

      if(!is.null(adjusted_sa1_hh_dist)){
        #Save SA1 hh distribution
        sa1hhsgzfile <- gzfile(paste(saoutpath, sa1_households_file_name, sep = ""))
        flog.info("SA1 distribution household distribution matched to SA2 households total")
        flog.info("Updated SA1 household distribution saved to: %s",
            summary(sa1hhsgzfile)$description)
        CreateDir(dirname(summary(sa1hhsgzfile)$description))
        df <- as.data.frame(adjusted_sa1_hh_dist)
        write.csv(df, sa1hhsgzfile, row.names = FALSE, quote = F)
      }else{
        sa2s_with_no_sa1s = c(sa2s_with_no_sa1s, sa2)
        flog.info("All SA1s are empty in this SA2 ")
      }
    }
  }
}
cat("Processed",sa2_count,"/",length(sa2_list),"SA2s         \n")
flog.info(paste("Processed",sa2_count,"/",length(sa2_list),"SA2s"))


cat("\nEmpty SA2s\n")
cat(paste(rownames(errors[is.na(errors[, "start_error%"]),]), collapse=", "),"\n")
flog.info("Empty SA2s: ",unlist(rownames(errors[is.na(errors[, "start_error%"]),])))

cat("\nBefore and after error saved at",opt$errorfile,"\n")
success <- CreateDir(dirname(opt$errorfile))
write.csv(errors, opt$errorfile)

desc = "\nSA2s above 5% error\n The difference between the number of persons in household and person distributions as a percentage of persons in household distribution. (-) values indicate persons distribution having more persons than household distribution and (+) values indicate the opposite.\n"
cat(desc)
flog.info(desc)

errors <- subset(errors, !is.na(errors[, "start_error%"]))

high_error <-
  errors[c(abs(errors[, "start_error%"]) >= 5 &
             abs(errors[, "end_error%"]) >= 5),]
if (length(high_error) > 0) {
  print(high_error)
  flog.info(paste("SA2", unlist(colnames(high_error))))
  for(e in high_error){
    flog.info(paste(rownames(e),unlist(e)))
  }
} else{
  print("None")
  flog.info("None")
}

if (do_sa1) {
  desc = "\nSA2s where all SA1s are empty, thus no SA1 distribution constructed\n"
  cat(desc)
  flog.info(desc)
  if (length(sa2s_with_no_sa1s) > 0) {
    print(sa2s_with_no_sa1s)
    flog.info(sa2s_with_no_sa1s)
  } else{
    print("None")
    flog.info("None")
  }
}

end_time <- Sys.time()

cat("\nOutput files are saved under: ", out_loc, "\n")
flog.info(paste("Output files are saved under: ", out_loc))
cat("Execution time: ", (end_time - start_time), "\n")
flog.info(paste("Execution time:", (end_time - start_time)))
cat("Done!")
flog.info("Done!")
