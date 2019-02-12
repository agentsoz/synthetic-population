randomSeed =  1

#Person types
rel_status_cats = c(
  'Married',
  'LoneParent',
  'U15Child',
  'Student',
  'O15Child',
  'GroupHhold',
  'LonePerson',
  'Relative'
)
sex_cats = c('Male', 'Female')
age_cats = c(
  '0-14 years',
  '15-24 years',
  '25-39 years',
  '40-54 years',
  '55-69 years',
  '70-84 years',
  '85-99 years',
  '100 years and over'
)
# 1 indicates age categories that can have agents according to relationship type, while 0 indicates impossible cases. 
#The order of the vector is same as age_cats vector
married_age = c(0, 1, 1, 1, 1, 1, 1, 1)
loneparent_age = c(0, 1, 1, 1, 1, 1, 1, 1)
u15child_age = c(1, 0, 0, 0, 0, 0, 0, 0)
student_age = c(0, 1, 0, 0, 0, 0, 0, 0)
o15_age = c(0, 1, 1, 1, 1, 1, 1, 1)
grouphh_age = c(0, 1, 1, 1, 1, 1, 1, 1)
loneperson_age = c(0, 1, 1, 1, 1, 1, 1, 1)
relatives_age = c(1, 1, 1, 1, 1, 1, 1, 1)

# Household types
hh_sizes = c(
  "One person",
  "Two persons",
  "Three persons",
  "Four persons",
  "Five persons",
  "Six persons",
  "Seven persons",
  "Eight or more persons"
)
family_hh_cats = c(
  'One family household: Couple family with no children',
  'One family household: Couple family with children',
  'One family household: One parent family',
  'One family household: Other family',
  'Two family household: Couple family with no children',
  'Two family household: Couple family with children',
  'Two family household: One parent family',
  'Two family household: Other family',
  'Three or more family household: Couple family with no children',
  'Three or more family household: Couple family with children',
  'Three or more family household: One parent family',
  'Three or more family household: Other family',
  'Lone person household',
  'Group household'
)
# 1 indicates family types that can have households and 0 indicates impossible ones
# The order of the vector is same as family_hh_cats vector
one_person = c(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0)
two_persons = c(1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)
three_persons = c(1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)
four_persons = c(1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1)
five_persons = c(1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1)
six_persons = c(1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1)
seven_persons = c(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1)
eight_persons = c(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1)

#ABS csv file specifics
## Persons file
# Following three variables specify the data block that needs to be read from persons csv file

#number of columns
p_nof_cols = 6 
# The starting column including row headers in persons csv. Columns before this are skipped.
p_header_start_col = 1
# The first row where values start. The row above this is usually a header row, we are not interested in that 
# row because p_sa_col, p_rel_col, p_sex_col, p_age_col and p_value_col variables below represent them
p_value_start_row = 12 

p_sa_col = 1 # SA names column index
p_rel_col = 2 # Relationship categories column index
p_sex_col = 3 # Sex categories column index
p_age_col = 4 # Age categories column index
p_value_col = 5 # The index of the colum with number of persons

## Households file

#The data block
h_nof_cols = 5 # number of relavent columns in households file.
h_header_start_col = 1 # The starting column.
h_values_start_row = 12 # The first row where values start. Same as above

h_sa_col = 1 #SA names column index
h_nof_persons_col = 2 #Number of persons in household column index
h_family_hh_type_col = 3 #Family household type column index
h_value_col = 4 #The number of households column index

## SA1 household distribution file

#R's read.csv failes to read 'SA1 household distribution file' properly. Below properties tell our custom reader what rows and columns to read.
sa2_col = 1 #SA1 codes column index
family_hh_type_col = 3 # Family household type column index
hh_size_col = 2 # household size column index
sa1_start_col = 4 # The starting column of SA1 codes 
sa1_row = 7 # SA1 codes row
colname_row = 8 # Column names row
sa1_data_start_row = 9 # The row that data starts
hh_types_count = length(hh_sizes)* length(family_hh_cats)
# End ABS csv file specifics

#Output file configurations
persons_file_name ="preprocessed/person_types.csv.gz"
households_file_name = "preprocessed/household_types.csv.gz"
sa1_households_file_name = "preprocessed/sa1_household_types.csv.gz"
raw_persons_file_name = "preprocessed/raw_person_types.csv.gz"
raw_households_file_name = "preprocessed/raw_household_types.csv.gz"

##Age distribution file
a_age_col  =1
a_start_age = 0
a_end_age = 115
a_nof_sa2s = 309
a_sa_row = 11
a_data_start_row = 13

##SA2 code map file
m_sa2_codes_csv = "SA2_2016_AUST.csv"
