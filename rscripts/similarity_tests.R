library('lsa')
library(optparse)

source("config.R")
source("datareader.R")
source("util.R")


option_list = list(
  make_option(
    c("--generateddata"),
    default = "../data/melbourne/generated/SA2",
    help = "Generated data files location. [default= %default]",
    metavar = "DIR"
  ),
  make_option(
    c("--rawhouseholds"),
    default = "../data/melbourne/raw/Households_2016_Greater_Melbourne_SA2.zip",
    help = "Household data file from ABS. The file can be either a zip or a csv. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("--rawpersons"),
    default = "../data/melbourne/raw/Persons_2016_Greater_Melbourne_SA2.zip",
    help = "Person data file from ABS. The file can be either a zip or a csv. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("--output"),
    default = "../data/melbourne/analysis",
    help = "The path of the output directory. [default= %default]",
    metavar = "DIR"
  ),
  make_option(
    c("--sa2s"),
    help = "The list of SA2s to perform statistical analysis. The parameter can be either \"*\" - performes analysis on all the SA2 directories under the directory specified under --data option,  a comma seperated list of SA2 names or a plain text file with one SA2 per line [default= %default]",
    metavar = "LIST_NAMES",
    default = "*"
  )
)
script_description = "This script performs the statistical analysis of the synthesised population"
opt_parser = OptionParser(option_list = option_list, description = script_description)
opt = parse_args(opt_parser)

data_home = opt$generateddata 
outputHome = opt$output

#Read the list of SA2s
isStar <- F
if (opt$sa2s == "*") {
  sa2_list <- basename(list.dirs(data_home, recursive=FALSE))
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
cat("SA2s list reading complete\n")

#Load household distribution from ABS files
print("Reading households file")
hh_input <- opt$rawhouseholds
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
ind_input <- opt$rawpersons
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
print("Raw persons and households files reading complete")


mu =2
alpha = 0.025

PerformSimilarityTests <- function(exp_dist, obs_dist){
  
  grt = wilcox.test(x= exp_dist, y = obs_dist, paired = T, alternative = "greater", mu = -mu)
  les = wilcox.test(x= exp_dist, y = obs_dist, paired = T, alternative = "less", mu = mu)
  
  pval = max(c(grt$p.value,les$p.value))
  
  # Do Cosine similarity right here
  cossim = cosine(x=exp_dist, y=obs_dist)
  return(list("p-value"=pval, "alt accept"= (pval < (alpha)), "Cossine similarity"=cossim))
  
}

cat("Person types distribution of ABS raw input vs. synthetic population\n")


totals <- matrix(0,nrow = length(sa2_list), ncol = 5)
totals[,1] <- sa2_list
colnames(totals) <- c("SA2","Total persons in preprocessed households dist","Total persons in preprocessed persons dist", "Total persons in synthesised households dist","Total persons in synthesised persons dist")

wilcoxon_test_result <- matrix(0,nrow = length(sa2_list), ncol = 3)
cossim_test_result <- matrix(0,nrow = length(sa2_list), ncol = 2)
for(i in 1:length(sa2_list)){
  
  abs_raw_dist = ReadBySA(indArr, sa2_list[i])$V5
  
  synthetic_population_csv = paste(data_home,"/",sa2_list[i],"/population/output_person_types.csv.gz",sep="")
  synthetic_population_dist = read.csv(synthetic_population_csv)$Persons
  res = PerformSimilarityTests(abs_raw_dist, synthetic_population_dist)
  wilcoxon_test_result[i,] <- c(sa2_list[i], unlist(res)[1:2])
  cossim_test_result[i, ] <-c(sa2_list[i], unlist(res)[3])
}

colnames(wilcoxon_test_result) <-c("SA2", paste("p-value\nmu=",mu), paste("Equivalent (0 - no, 1 - yes )\n","alpha=",alpha,"\nconfidence=",(100-(alpha*2*100)),"%",sep=""))
outfile=paste(outputHome, "/persons-abs-raw-vs-generated-tost-wilcoxon.csv",sep="")
write.csv(wilcoxon_test_result,file=outfile, row.names = F)
print("TOST with Wilcoxon Signed Rank Test")

colnames(cossim_test_result) <-c("SA2","Cosine similarity")
print("Cosine similarity test")
outfile=paste(outputHome, "/persons-abs-raw-vs-generated-cosine-similarity.csv",sep="")
write.csv(cossim_test_result,file=outfile, row.names = F)

cat("Persons distribution of preprocessed data vs. synthetic population\n")
for(i in 1:length(sa2_list)){
  cleaned_data_csv = paste(data_home,"/",sa2_list[i],"/preprocessed/person_types.csv.gz",sep="")
  cleaned_dist = read.csv(cleaned_data_csv)$Persons.count
  synthetic_population_csv = paste(data_home,"/",sa2_list[i],"/population/output_person_types.csv.gz",sep="")
  synthetic_population_dist  = read.csv(synthetic_population_csv)$Persons
  
  res = PerformSimilarityTests(cleaned_dist, synthetic_population_dist)
  
  wilcoxon_test_result[i,] <- c(sa2_list[i], unlist(res)[1:2])
  cossim_test_result[i, ] <-c(sa2_list[i], unlist(res)[3])
  totals[i,3] <- sum(cleaned_dist)
  totals[i,5] <- sum(synthetic_population_dist)
}

print("TOST with Wilcoxon Signed Rank Test")
outfile=paste(outputHome, "/persons-preprocessed-vs-generated-tost-wilcoxon.csv",sep="")
write.csv(wilcoxon_test_result,file=outfile, row.names = F)

print("Cosine similarity test")
outfile=paste(outputHome, "/persons-preprocessed-vs-generated-cosine-similarity.csv",sep="")
write.csv(cossim_test_result,file=outfile, row.names = F)

cat("Household distribution of proprocessed data vs. synthetic population\n")
for(i in 1:length(sa2_list)){
  cleaned_data_csv = paste(data_home,"/",sa2_list[i],"/preprocessed/household_types.csv.gz",sep="")
  cleaned_dist = read.csv(cleaned_data_csv)$Households.count
  synthetic_population_csv = paste(data_home,"/",sa2_list[i],"/population/output_household_types.csv.gz",sep="")
  synthetic_population_dist  = read.csv(synthetic_population_csv)$NofHouseholds
  res = PerformSimilarityTests(cleaned_dist, synthetic_population_dist)
  
  wilcoxon_test_result[i,] <- c(sa2_list[i], unlist(res)[1:2])
  cossim_test_result[i,] <- c(sa2_list[i], unlist(res)[3])
  
  totals[i,2] <- sum(cleaned_dist*rep(seq(1,8), each = 14))
  totals[i,4] <- sum(synthetic_population_dist*rep(seq(1,8), each = 14))
}

colnames(wilcoxon_test_result) <- c("SA2",paste("p-value\nmu=",mu), paste("Equivalent\n","alpha=",alpha,"\nconfidence=",(100-(alpha*2*100)),"%",sep=""))
print("TOST with Wilcoxon Signed Rank Test - Households")
outfile=paste(outputHome, "/households-preprocessed-vs-generated-tost-wilcoxon.csv",sep="")
write.csv(wilcoxon_test_result,file=outfile)

colnames(cossim_test_result) <-c("SA2","Cosine similarity")
print("Cosine similarity test - Households")
outfile=paste(outputHome, "/households-preprocessed-vs-generated-cosine-similarity.csv",sep="")
write.csv(cossim_test_result,file=outfile)

totals_file <- paste(outputHome, "/households-persons-totals.csv",sep="")
write.csv(totals, file=totals_file)

cat("Output saved to:",outputHome,"\n")
cat("Done!\n")

