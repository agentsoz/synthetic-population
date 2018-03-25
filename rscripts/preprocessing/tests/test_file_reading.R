library(testthat)
library(tools)

source("configs_for_tests.R")
source("../config.R")
source("../datareader.R")
source("../util.R")


#Verifys whether households distribution is read properly
func_hhArr = ReadHouseholds(
  abs_households_file,
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

ref_hhArr = readRDS(ref_households_file)

test_that("ABS Household Distribution reading", {
  expect_equal(func_hhArr, ref_hhArr)
})

#Verifys whether persons distribution is read properly
func_indArr = ReadPersons(
  abs_persons_file,
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

ref_indArr = readRDS(ref_persons_file)

test_that("ABS Persons Distribution reading", {
  expect_equal(func_indArr, ref_indArr)
})

ref_sa1_hh_dist = readRDS(ref_sa1_households_file)
func_sa1_hh_dist = ReadSA1HouseholdsInSA2(c(abs_sa1_households_file),test_sa2,length(family_hh_cats), length(hh_sizes))

test_that("SA1 household distribution reading", {
  expect_equal(func_sa1_hh_dist, ref_sa1_hh_dist)
})


