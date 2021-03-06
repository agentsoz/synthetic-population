#!/usr/bin/env Rscript
library(lsa)
library(optparse)
library(tools)

start_time <- Sys.time()
source("config.R")
source("datareader.R")
source("util.R")
source("drawplots.R")
source("reduce_categories.R")
source("stat_tests.R")


option_list = list(
  make_option(
    c("--generateddata"),
    default = "../data/melbourne-2016/generated/SA2",
    help = "Generated data files location. [default= %default]",
    metavar = "DIR"
  ),
  make_option(
    c("--rawhouseholds"),
    default = "../data/melbourne-2016/raw/Households_2016_Greater_Melbourne_SA2.zip",
    help = "Household data file from ABS. The file can be either a zip or a csv. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("--rawpersons"),
    default = "../data/melbourne-2016/raw/Persons_2016_Greater_Melbourne_SA2.zip",
    help = "Person data file from ABS. The file can be either a zip or a csv. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("--sa2agedist"),
    default = "../data/melbourne-2016/raw/Persons_percentage_by_age_2016_Greater_Melbourne_SA2s.zip",
    help = "Distribution of person percentages by Age in each SA2. [default= %default]",
    metavar = "FILE"
  ),
  make_option(
    c("--output"),
    default = "../analysis/melbourne-2016/reduced-cats/",
    help = "The path of the output directory. [default= %default]",
    metavar = "DIR"
  ),
  make_option(
    c("--sa2s"),
    help = "The list of SA2s to perform statistical analysis. The parameter can be either \"*\" - performes analysis on all the SA2 directories under the directory specified under --data option,  a comma seperated list of SA2 names or a plain text file with one SA2 per line [default= %default]",
    metavar = "LIST_NAMES",
    default = "*"
  ),
  make_option(
    c("--mu"),
    default = 2,
    help = "Tolerable median difference for TOST Wilcoxon Signed-rank Tests. [default= %default]",
    metavar = "NUMBER"
  ),
  make_option(
    c("--alpha"),
    default = 0.05,
    help = "Significance level for TOST Wilcoxon Signed-rank Test. [default= %default]",
    metavar = "NUMBER"
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
  sa2_list <- basename(list.dirs(data_home, recursive = FALSE))
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

print("Reading the expected age distributions in all SA2s appear in persons file")
age_input <- opt$sa2agedist
expected_age_dist <-
  ReadAges(
    age_input,
    nof_sa2s = length(unique(indArr$V1)),
    a_age_col,
    start_age = 0,
    end_age = 115,
    sa_row = a_sa_row,
    data_start_row = a_data_start_row
  )

print("Raw input data reading complete")
mu = opt$mu
alpha = opt$alpha

PerformSimilarityTests <- function(exp_dist, obs_dist) {
  
  grt = wilcox.test(
    x = exp_dist,
    y = obs_dist,
    paired = T,
    alternative = "greater",
    mu = -mu
  )
  les = wilcox.test(
    x = exp_dist,
    y = obs_dist,
    paired = T,
    alternative = "less",
    mu = mu
  )
  
  pval = max(c(grt$p.value, les$p.value))
  
  # Do Cosine similarity right here
  cossim = cosine(x = exp_dist, y = obs_dist)
  
  #Do Freeman-Tukey test
  ft_result = FreemanTukeyTest(exp_dist, obs_dist, simulate_p_value = T)
  
  #Do APD
  apd = sum(abs(exp_dist - obs_dist))/sum(exp_dist)
  pop_size = sum(exp_dist)
  
  #DO SAE
  err = Error(exp_dist=exp_dist, obs_dist = obs_dist)
  
  return(list(
    "TOST p-value" = pval,
    "TOST alt accept" = (pval < (alpha)),
    "Cossine similarity" = cossim,
    "FT statistic" = ft_result$statistic,
    "FT p-value" = ft_result$p.value,
    "FT degrees of freedom" = ft_result$parameter,
    "APD" = apd,
    "APD%" = (apd*100),
    "Pop size"= pop_size,
    "SAE" = err$sae
  ))
  
}

EvaluatePersonsProcessedVsSynthesised <- function() {
  cat("Persons distribution of preprocessed data vs. synthetic population\n")
  
  wilcoxon_test_result = matrix(0, nrow = length(sa2_list), ncol = 3)
  cossim_test_result = matrix(0, nrow = length(sa2_list), ncol = 2)
  ft_test_result <- matrix(0, nrow = length(sa2_list), ncol = 4)
  apd_test_result = matrix(0, nrow = length(sa2_list), ncol = 4)
  totals = matrix(0, nrow = length(sa2_list), ncol = 5)
  sae_test_result = matrix(0, nrow = length(sa2_list), ncol = 2)
  
  for (i in 1:length(sa2_list)) {
    cat("\rprocessing", i, "/", length(sa2_list),sa2_list[i],"                      ")
    cleaned_data_csv = paste(data_home,
                             "/",
                             sa2_list[i],
                             "/preprocessed/person_types.csv.gz",
                             sep = "")
    cleaned_dist = read.csv(cleaned_data_csv)
   
    if(sa2_list[i] == "Melbourne"){
      person_types = paste(cleaned_dist$Relationship, cleaned_dist$Sex, cleaned_dist$Age, sep=",")
      person_types[-c(87, 6)] = NA
    }else{
      person_types = NULL
    }
    cleaned_dist = cleaned_dist$Persons.count
    
    synthetic_population_csv = paste(data_home,
                                     "/",
                                     sa2_list[i],
                                     "/population/output_person_types.csv.gz",
                                     sep = "")
    synthetic_population_dist  = read.csv(synthetic_population_csv)
    
    synthetic_population_dist = synthetic_population_dist$Persons
    
    # hh_data_csv = paste(data_home,
    #                          "/",
    #                          sa2_list[i],
    #                          "/preprocessed/household_types.csv.gz",
    #                          sep = "")
    # hh_data = read.csv(hh_data_csv)
    # hh_sizes = ceiling(c(1:nrow(hh_data)/14))
    # hh_pesons_sum = sum(hh_data$Households.count*hh_sizes)
    # cleaned_dist = cleaned_dist/sum(cleaned_dist)*hh_pesons_sum
    
    #Removing impossible categories
    cleaned_dist = cleaned_dist[-c(8,16,24,32,33:39,41:47,49:54,56:62,64,72,80,88,96,104,112)]
    synthetic_population_dist = synthetic_population_dist[-c(8,16,24,32,33:39,41:47,49:54,56:62,64,72,80,88,96,104,112)]
    person_types = person_types[-c(8,16,24,32,33:39,41:47,49:54,56:62,64,72,80,88,96,104,112)]
    
    
    res = PerformSimilarityTests(cleaned_dist, synthetic_population_dist)
    
    wilcoxon_test_result[i,] <- c(sa2_list[i], unlist(res)[1:2])
    cossim_test_result[i, ] <- c(sa2_list[i], unlist(res)[3])
    ft_test_result[i,] <- c(sa2_list[i], unlist(res)[4:6])
    apd_test_result[i,] <- c(sa2_list[i], unlist(res)[7:9])
    sae_test_result[i,] <- c(sa2_list[i], unlist(res)[10])
    
    totals[i, 3] <- sum(cleaned_dist)
    totals[i, 5] <- sum(synthetic_population_dist)
    
    file_prefix = SA2FilePrefix(sa2_list[i])
    out_file = paste(
      outputHome,
      "plots",
      sa2_list[i],
      paste(file_prefix,"bar_persons_preprocessed_vs_synthetic.pdf", sep = "_"),
      sep = "/"
    )
    xlabels = rep("",length(cleaned_dist))
    xlabels[2] = "Married male"
    DrawBarSA2Plot(
      cleaned_dist,
      synthetic_population_dist,
      out_file,
      "Preprocessed data vs synthetic population",
      "Person types",
      "Number of persons",
      xlabels,
      c("Census preprocessed", "Synthesised"),
      sa2_list[i]
    )
    
    qqplot_pdf = paste(
      outputHome,
      "plots",
      sa2_list[i],
      paste(file_prefix,"qqplot_persons_preprocessed_vs_synthetic.pdf", sep = "_"),
      sep = "/"
    )
    
    DrawQQPlot(cleaned_dist, synthetic_population_dist,qqplot_pdf,"", "Preprocessed-census distribution","Synthesised distribution",sa2 = sa2_list[i], point_labels = person_types)
  
  }
  
  print("TOST with Wilcoxon Signed Rank Test")
  colnames(wilcoxon_test_result) <-
    c(
      "SA2",
      paste("p-value\nmu=", mu),
      paste(
        "Equivalent\n",
        "alpha=",
        alpha,
        "\nconfidence=",
        (100 - (alpha * 2 * 100)),
        "%",
        sep = ""
      )
    )
  outfile = paste(outputHome,
                  "/persons-preprocessed-vs-generated-tost-wilcoxon-reduced-cats.csv",
                  sep = "")
  write.csv(wilcoxon_test_result,
            file = outfile,
            row.names = F)
  
  print("Cosine similarity test")
  colnames(cossim_test_result) <- c("SA2", "Cosine similarity")
  outfile = paste(outputHome,
                  "/persons-preprocessed-vs-generated-cosine-similarity-reduced-cats.csv",
                  sep = "")
  write.csv(cossim_test_result, file = outfile, row.names = F, quote = F)
  
  print("Freeman Tukey Test")
  colnames(ft_test_result) <-
    c(
      "SA2",
      "FT statistic",
      "FT p-value",
      "FT degrees of freedom"
    )
  outfile = paste(outputHome,
                  "/persons-preprocessed-vs-generated-freeman-tukey-reduced-cats.csv",
                  sep = "")
  write.csv(ft_test_result,
            file = outfile,
            row.names = F, quote = F)
  
  print("APD test")
  colnames(apd_test_result) <- c("SA2", "APD","APD%","Population size")
  outfile = paste(outputHome,
                  "/persons-preprocessed-vs-generated-absolute-percentage-deviation-reduced-cats.csv",
                  sep = "")
  write.csv(apd_test_result, file = outfile, row.names = F, quote = F)
  
  print("SAE test")
  colnames(sae_test_result) <- c("SA2", "SAE")
  outfile = paste(outputHome,
                  "/persons-preprocessed-vs-generated-sae-reduced-cats.csv",
                  sep = "")
  write.csv(sae_test_result, file = outfile, row.names = F, quote = F)
}

EvaluateHouseholdProcessedVsSynthesised <- function() {
  cat("Household distribution of proprocessed data vs. synthetic population\n")
  
  wilcoxon_test_result = matrix(0, nrow = length(sa2_list), ncol = 3)
  cossim_test_result = matrix(0, nrow = length(sa2_list), ncol = 2)
  ft_test_result <- matrix(0, nrow = length(sa2_list), ncol = 4)
  apd_test_result = matrix(0, nrow = length(sa2_list), ncol = 4)
  totals = matrix(0, nrow = length(sa2_list), ncol = 5)
  
  for (i in 1:length(sa2_list)) {
    cat("\rprocessing", i, "/", length(sa2_list),sa2_list[i],"                      ")
    cleaned_data_csv = paste(data_home,
                             "/",
                             sa2_list[i],
                             "/preprocessed/household_types.csv.gz",
                             sep = "")
    cleaned_dist = read.csv(cleaned_data_csv)
    cleaned_dist = cleaned_dist$Households.count
    
    synthetic_population_csv = paste(data_home,
                                     "/",
                                     sa2_list[i],
                                     "/population/output_household_types.csv.gz",
                                     sep = "")
    synthetic_population_dist  = read.csv(synthetic_population_csv)
    synthetic_population_dist = synthetic_population_dist$NofHouseholds
    
    totals[i, 2] <- sum(cleaned_dist * rep(seq(1, 8), each = 14))
    totals[i, 4] <- sum(synthetic_population_dist * rep(seq(1, 8), each = 14))
    
    #Removing impossible categories
    cleaned_dist = cleaned_dist[-c(1:12,14,16,19:27,33:41,48,51:55,65:69,80,83,97,111)]
    synthetic_population_dist = synthetic_population_dist[-c(1:12,14,16,19:27,33:41,48,51:55,65:69,80,83,97,111)]
    
    res = PerformSimilarityTests(cleaned_dist, synthetic_population_dist)
    
    wilcoxon_test_result[i,] <- c(sa2_list[i], unlist(res)[1:2])
    cossim_test_result[i,] <- c(sa2_list[i], unlist(res)[3])
    ft_test_result[i,] <- c(sa2_list[i], unlist(res)[4:6])
    apd_test_result[i,] <- c(sa2_list[i], unlist(res)[7:9])
    
    file_prefix = SA2FilePrefix(sa2_list[i])
    out_file = paste(
      outputHome,
      "plots",
      sa2_list[i],
      paste(file_prefix,"bar_households_preprocessed_vs_synthetic.pdf", sep = "_"),
      sep = "/"
    )
    
    DrawBarSA2Plot(
      cleaned_dist,
      synthetic_population_dist,
      out_file,
      "Preprocessed data vs synthetic population",
      "Household types",
      "Number of households",
      c(),
      c("Census preprocessed", "Synthesised"),
      sa2_list[i]
    )
    
    qqplot_pdf = paste(
      outputHome,
      "plots",
      sa2_list[i],
      paste(file_prefix,"qqplot_households_preprocessed_vs_synthetic.pdf", sep = "_"),
      sep = "/"
    )
    DrawQQPlot(cleaned_dist, synthetic_population_dist,qqplot_pdf,"", "Preprocessed-census households","Synthesised households",sa2 = sa2_list[i])
  }
  
  colnames(wilcoxon_test_result) <-
    c(
      "SA2",
      paste("p-value\nmu=", mu),
      paste(
        "Equivalent\n",
        "alpha=",
        alpha,
        "\nconfidence=",
        (100 - (alpha * 2 * 100)),
        "%",
        sep = ""
      )
    )
  print("TOST with Wilcoxon Signed Rank Test - Households")
  outfile = paste(outputHome,
                  "/households-preprocessed-vs-generated-tost-wilcoxon-reduced-cats.csv",
                  sep = "")
  write.csv(wilcoxon_test_result, file = outfile,quote = F)
  
  colnames(cossim_test_result) <- c("SA2", "Cosine similarity")
  print("Cosine similarity test - Households")
  outfile = paste(
    outputHome,
    "/households-preprocessed-vs-generated-cosine-similarity-reduced-cats-reduced-cats.csv",
    sep = ""
  )
  write.csv(cossim_test_result, file = outfile, quote = F)
  
  totals_file <-
    paste(outputHome, "/households-persons-totals.csv", sep = "")
  write.csv(totals, file = totals_file)
  
  print("Freeman Tukey Test")
  colnames(ft_test_result) <-
    c(
      "SA2",
      "FT statistic",
      "FT p-value",
      "FT degrees of freedom"
    )
  outfile = paste(outputHome,
                  "/households-preprocessed-vs-generated-freeman-tukey-reduced-cats.csv",
                  sep = "")
  write.csv(ft_test_result,
            file = outfile,
            row.names = F, quote = F)
  
  print("APD test")
  colnames(apd_test_result) <- c("SA2", "APD","APD%","Population size")
  outfile = paste(outputHome,
                  "/households-preprocessed-vs-generated-absolute-percentage-deviation-reduced-cats.csv",
                  sep = "")
  write.csv(apd_test_result, file = outfile, row.names = F, quote = F)
  
}

EvaluateAgeCatsPersonsProcessedVsSynthesised <- function() {
  cat("Persons age distribution of preprocessed data vs. synthetic population\n")
  
  wilcoxon_test_result = matrix(0, nrow = length(sa2_list), ncol = 3)
  cossim_test_result = matrix(0, nrow = length(sa2_list), ncol = 2)
  ft_test_result <- matrix(0, nrow = length(sa2_list), ncol = 4)
  apd_test_result = matrix(0, nrow = length(sa2_list), ncol = 4)
  totals = matrix(0, nrow = length(sa2_list), ncol = 5)
  
  for (i in 1:length(sa2_list)) {
    cat("\rprocessing", i, "/", length(sa2_list),sa2_list[i],"                      ")
    cleaned_data_csv = paste(data_home,
                             "/",
                             sa2_list[i],
                             "/preprocessed/person_types.csv.gz",
                             sep = "")
    cleaned_dist = read.csv(cleaned_data_csv)
    cleaned_dist = cleaned_dist$Persons.count
    
    cleaned_dist = rowSums(matrix(cleaned_dist, nrow = 8))
    
    synthetic_population_csv = paste(data_home,
                                     "/",
                                     sa2_list[i],
                                     "/population/output_person_types.csv.gz",
                                     sep = "")
    synthetic_population_dist  = read.csv(synthetic_population_csv)
    synthetic_population_dist  = synthetic_population_dist$Persons
    
    synthetic_population_dist = rowSums(matrix(synthetic_population_dist, nrow = 8))
    
    # hh_data_csv = paste(data_home,
    #                     "/",
    #                     sa2_list[i],
    #                     "/preprocessed/household_types.csv.gz",
    #                     sep = "")
    # hh_data = read.csv(hh_data_csv)
    # hh_sum = sum(hh_data$Households.count*hh_data$Household.size)
    # 
    # cleaned_dist = cleaned_dist/sum(cleaned_dist)*hh_sum
    res = PerformSimilarityTests(cleaned_dist, synthetic_population_dist)
    
    wilcoxon_test_result[i,] <- c(sa2_list[i], unlist(res)[1:2])
    cossim_test_result[i, ] <- c(sa2_list[i], unlist(res)[3])
    ft_test_result[i,] <- c(sa2_list[i], unlist(res)[4:6])
    apd_test_result[i,] <- c(sa2_list[i], unlist(res)[7:9])
    
    
    
    totals[i, 3] <- sum(cleaned_dist)
    totals[i, 5] <- sum(synthetic_population_dist)
    
    file_prefix = SA2FilePrefix(sa2_list[i])
    out_file = paste(
      outputHome,
      "plots",
      sa2_list[i],
      paste(file_prefix,"bar_age_cats_persons_preprocessed_vs_synthetic.pdf", sep = "_"),
      sep = "/"
    )
    xlabels = rep("",length(cleaned_dist))
    xlabels[2] = "Married male"
    DrawBarSA2Plot(
      cleaned_dist,
      synthetic_population_dist,
      out_file,
      "Preprocessed data vs synthetic population",
      "Person types",
      "Number of persons",
      xlabels,
      c("Census preprocessed", "Synthesised"),
      sa2_list[i]
    )
    
    qqplot_pdf = paste(
      outputHome,
      "plots",
      sa2_list[i],
      paste(file_prefix,"qqplot_age_cats_persons_preprocessed_vs_synthetic.pdf", sep = "_"),
      sep = "/"
    )
    DrawQQPlot(cleaned_dist, synthetic_population_dist,qqplot_pdf,"", "Preprocessed-census persons","Synthesised persons",sa2 = sa2_list[i])
    
  }
  
  print("TOST with Wilcoxon Signed Rank Test")
  colnames(wilcoxon_test_result) <-
    c(
      "SA2",
      paste("p-value\nmu=", mu),
      paste(
        "Equivalent\n",
        "alpha=",
        alpha,
        "\nconfidence=",
        (100 - (alpha * 2 * 100)),
        "%",
        sep = ""
      )
    )
  outfile = paste(outputHome,
                  "/persons-age-cats-preprocessed-vs-generated-tost-wilcoxon-reduced-cats.csv",
                  sep = "")
  write.csv(wilcoxon_test_result,
            file = outfile,
            row.names = F)
  
  print("Cosine similarity test")
  colnames(cossim_test_result) <- c("SA2", "Cosine similarity")
  outfile = paste(outputHome,
                  "/persons-age-cats-preprocessed-vs-generated-cosine-similarity-reduced-cats.csv",
                  sep = "")
  write.csv(cossim_test_result, file = outfile, row.names = F)
  
  print("Freeman Tukey Test")
  colnames(ft_test_result) <-
    c(
      "SA2",
      "FT statistic",
      "FT p-value",
      "FT degrees of freedom"
    )
  outfile = paste(outputHome,
                  "/persons-age-cats-preprocessed-vs-generated-freeman-tukey-reduced-cats.csv",
                  sep = "")
  write.csv(ft_test_result,
            file = outfile,
            row.names = F, quote = F)
  
  print("APD test")
  colnames(apd_test_result) <- c("SA2", "APD","APD%","Population size")
  outfile = paste(outputHome,
                  "/persons-age-cats-preprocessed-vs-generated-absolute-percentage-deviation-reduced-cats.csv",
                  sep = "")
  write.csv(apd_test_result, file = outfile, row.names = F, quote = F)
}

EvaluatePersonsProcessedVsSynthesised()
EvaluateAgeCatsPersonsProcessedVsSynthesised()
EvaluateHouseholdProcessedVsSynthesised()

end_time <- Sys.time()

cat("Output saved to:", outputHome, "\n")
cat("Execution time: ", (end_time - start_time), "\n")
cat("Done!\n")
