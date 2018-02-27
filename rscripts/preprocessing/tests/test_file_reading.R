library(testthat)
library(tools)

source("../config.R")
source("../datareader.R")
source("../util.R")
source("../cleaning.R")

abs_persons_file = 'test_data/Persons_2016_Greater_Melbourne_SA2.zip'
abs_households_file = 'test_data/Households_2016_Greater_Melbourne_SA2.zip'
abs_sa1_households_file = 'test_data/SA1_households_dist_in_SA2s_2016_Melbourne_Inner.zip'
ref_persons_file = 'test_data/persons.rds'
ref_households_file = 'test_data/households.rds'
test_sa2 = "Brunswick"
ref_cleaned_households_file = 'test_data/Brunswick_cleaned_households.rds'
ref_cleaned_persons_file = 'test_data/Brunswick_cleaned_persons.rds'

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

indv = ReadBySA(ref_indArr, test_sa2)
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
