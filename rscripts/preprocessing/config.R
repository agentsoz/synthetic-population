#Person types
rel_status_cats = c(
  'Married',
  'Lone parent',
  'U15Child',
  'Student',
  'O15Child',
  'GroupHhold',
  'Lone person',
  'Relatives'
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
p_nof_cols = 6
p_header_start_col = 1
p_value_start_row = 12

p_sa_col = 1
p_rel_col = 2
p_sex_col = 3
p_age_col = 4
p_value_col = 5

## Households file
h_nof_cols = 5
h_header_start_col = 1
h_values_start_row = 12

h_sa_col = 1
h_nof_persons_col = 2
h_family_hh_type_col = 3
h_value_col = 4

## SA1 household distribution file
sa2_col = 1
family_hh_type_col = 3
hh_size_col = 2
sa1_start_col = 4
sa1_row = 7
colname_row = 8
data_start_row = 9
# End ABS csv file specifics

