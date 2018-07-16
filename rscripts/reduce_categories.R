library(plyr)
CombineChildCatsInInput <- function(p_marg){
  child_cats = c("U15Child", "Student", "O15Child")
  child_rows_ids = which(p_marg$Relationship.status %in% child_cats)
  children_nums = rowSums(matrix(p_marg[child_rows_ids,5], ncol = length(child_cats)))
  
  p_marg <- p_marg[-which(p_marg$Relationship.status %in% c("Student", "O15Child")),]
  revalue(p_marg$Relationship.status, c("U15Child" = "Children")) -> p_marg$Relationship.status
  p_marg[which(p_marg$Relationship.status == "Children"),"Persons.count"] = children_nums
  return(p_marg)
}

CombineChildCatsInOutput <- function(p_marg){
  child_cats = c("U15_CHILD", "STUDENT", "O15_CHILD")
  child_rows_ids = which(p_marg$Relationship %in% child_cats)
  children_nums = rowSums(matrix(p_marg[child_rows_ids,"Persons"], ncol = length(child_cats)))
  
  p_marg <- p_marg[-which(p_marg$Relationship %in% c("STUDENT", "O15_CHILD")),]
  revalue(p_marg$Relationship, c("U15_CHILD" = "CHILDREN")) -> p_marg$Relationship
  p_marg[which(p_marg$Relationship == "CHILDREN"),"Persons"] = children_nums
  return(p_marg)
}

Combine85orMoreCatsInInput <- function(p_marg){
  above85_cats = c("85-99 years", "100 years and over")
  above85_ids = which(p_marg$Age %in% above85_cats)
  above85_nums = rowSums(matrix(p_marg[above85_ids,"Persons.count"], ncol = length(above85_cats), byrow = T))
  
  p_marg <- p_marg[-which(p_marg$Age == "100 years and over"),]
  revalue(p_marg$Age, c("85-99 years" = "85 years and over")) -> p_marg$Age
  p_marg[which(p_marg$Age == "85 years and over"),"Persons.count"] = above85_nums
  return(p_marg)
}

Combine85orMoreCatsInOutput <- function(p_marg){
  above85_cats = c("A85_99", "A100_110")
  above85_ids = which(p_marg$Age %in% above85_cats)
  above85_nums = rowSums(matrix(p_marg[above85_ids,"Persons"], ncol = length(above85_cats), byrow = T))
  
  p_marg <- p_marg[-which(p_marg$Age == "A100_110"),]
  revalue(p_marg$Age, c("A85_99" = "A85_110")) -> p_marg$Age
  p_marg[which(p_marg$Age == "A85_110"),"Persons"] = above85_nums
  return(p_marg)
}

ReverseAgeOrder <- function(p_marg){
  
  mat = matrix(c(1:nrow(p_marg)), ncol = length(age_cats), byrow = T)
  age_desc_order = as.vector(t(mat[ ,ncol(mat):1]))
  p_marg <- p_marg[age_desc_order,]
  return(p_marg)
  
}


CombineTwoThreeFamilyHhsInput <- function(h_marg) {
  f2 = c(
    "Two family household: Couple family with no children",
    "Two family household: Couple family with children",
    "Two family household: One parent family",
    "Two family household: Other family"
  )
  f3 = c(
    "Three or more family household: Couple family with no children",
    "Three or more family household: Couple family with children",
    "Three or more family household: One parent family",
    "Three or more family household: Other family"
  )
  f2_types = h_marg[which(h_marg$Family.household.type %in% f2), ]
  f3_types = h_marg[which(h_marg$Family.household.type %in% f3), ]
  
  p_counts = rowSums(cbind(f2_types$Households.count, f3_types$Households.count))
  
  h_marg[which(h_marg$Family.household.type %in% f2),"Households.count"] <- p_counts
  h_marg <- h_marg[-which(h_marg$Family.household.type %in% f3),]
  
  revalue(
    h_marg$Family.household.type,
    c(
      "Two family household: Couple family with no children" = "Two or more family household: Couple family with no children",
      "Two family household: Couple family with children" = "Two or more family household: Couple family with children",
      "Two family household: One parent family" = "Two or more family household: One parent family",
      "Two family household: Other family" = "Two or more family household: Other family"
    )
  ) -> h_marg$Family.household.type
  
  return(h_marg)
}

CombineTwoThreeFamilyHhsOutput <- function(h_marg) {
  f2 = c(
    "Two family household: Couple family with no children",
    "Two family household: Couple family with children",
    "Two family household: One parent family",
    "Two family household: Other family"
  )
  f3 = c(
    "Three or more family household: Couple family with no children",
    "Three or more family household: Couple family with children",
    "Three or more family household: One parent family",
    "Three or more family household: Other family"
  )
  f2_types = h_marg[which(h_marg$NofFamilies == "2 Family"), ]
  f3_types = h_marg[which(h_marg$NofFamilies == "3 Family"), ]
  
  p_counts = rowSums(cbind(f2_types$NofHouseholds, f3_types$NofHouseholds))
  
  h_marg[which(h_marg$NofFamilies == "2 Family"),"NofHouseholds"] <- p_counts
  h_marg <- h_marg[-which(h_marg$NofFamilies == "3 Family"),]
  
  return(h_marg)
}

CombineSixSevenEightHhsInput <- function(h_marg){
  
  hhsize_6_7_8 = h_marg[which(h_marg$Household.Size %in% c("Six persons","Seven persons", "Eight or more persons")),]
  six_or_more_hh_count = rowSums(matrix(hhsize_6_7_8$Households.count, ncol = 3))
  h_marg[which(h_marg$Household.Size == "Six persons"),"Households.count"] <- six_or_more_hh_count
  h_marg <- h_marg[-which(h_marg$Household.Size %in% c("Seven persons", "Eight or more persons")),]
  h_marg$Household.Size <- revalue(h_marg$Household.Size, c("Six persons" = "Six or more persons"))
  return(h_marg)
}


CombineSixSevenEightHhsOutput <- function(h_marg){
  
  hhsize_6_7_8 = h_marg[which(h_marg$NofPersons %in% c(6, 7, 8)),]
  six_or_more_hh_count = rowSums(matrix(hhsize_6_7_8$NofHouseholds, ncol = 3))
  h_marg[which(h_marg$NofPersons == 6),"NofHouseholds"] <- six_or_more_hh_count
  h_marg <- h_marg[-which(h_marg$NofPersons %in% c(7, 8)),]
  return(h_marg)
}