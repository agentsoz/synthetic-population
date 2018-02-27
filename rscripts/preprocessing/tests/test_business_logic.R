library(tools)
library(stringr)

source("configs_for_tests.R")
source("../config.R")
source("../util.R")
source("../cleaning.R")
source("../estimateSA1HouseholdsUsingSA2.R")

set.seed(randomSeed)
ref_indArr = readRDS(ref_persons_file)
indv = ReadBySA(ref_indArr, test_sa2)

ref_hhArr = readRDS(ref_households_file)
hhs = ReadBySA(ref_hhArr, test_sa2)

log <- capture.output({
  outlist <- clean(
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
})

brunswick_cleaned_indv = readRDS(ref_cleaned_persons_file)
brunswick_cleaned_hhs = readRDS(ref_cleaned_households_file)

test_that("Data cleaning", {
  expect_equal(outlist[[1]], brunswick_cleaned_indv)
  expect_equal(outlist[[2]], brunswick_cleaned_hhs)
})

input_sa1_hh_dist = ReadSA1HouseholdsInSA2(c(abs_sa1_households_file),test_sa2,length(family_hh_cats), length(hh_sizes))
log <- capture.output(func_adjusted_sa1_hh_dist <- EstimateSA1HouseholdsDistribution(test_sa2, brunswick_cleaned_hhs, input_sa1_hh_dist ))

brunswick_adjusted_sa1_hh_dist = readRDS(ref_adjusted_sa1_households_file)

test_that("SA1 household distribution calculating", {
  expect_equal(func_adjusted_sa1_hh_dist, brunswick_adjusted_sa1_hh_dist)
})







