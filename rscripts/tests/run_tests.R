library(testthat) 
library(tools)

source("../preprocessing/config.R")
source("../preprocessing/datareader.R")

test_results <- test_dir(".", reporter="summary")