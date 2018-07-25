

ReadHouseholds <-
  function(input_file,
           nof_cols,
           header_start_col,
           value_col,
           values_start_row,
           sa_col,
           nof_persons_col,
           family_hh_col,
           family_hh_cats,
           nof_persons_cats) {
    if (file_ext(input_file) == "zip") {
      csvname = paste(file_path_sans_ext(basename(input_file)), ".csv", sep = "")
      inputCsv = unz(input_file, csvname)
    } else{
      inputCsv = input_file
    }
    Hhs <-
      read.csv(
        inputCsv,
        header = F,
        sep = ",",
        fill = T,
        blank.lines.skip = F,
        stringsAsFactors =
          FALSE,
        na.strings = c("", "NA"),
        col.names = paste0("V", seq_len(nof_cols))
      )
    
    dummyVec = 1:length(Hhs[, 1])
    
    personCountRowi = values_start_row
    personCountGap = length(family_hh_cats) # Number of rows between each number of persons in household heading
    seq = dummyVec[seq(personCountRowi, length(dummyVec), personCountGap)]
    personCountList = na.omit((as.character(Hhs[seq, nof_persons_col])))
    
    SArowi = values_start_row	# Row number for SA IDs
    SAgap = length(family_hh_cats) * length(nof_persons_cats)  # Number of rows between each SA heading
    seq = dummyVec[seq(SArowi, length(dummyVec), SAgap)]
    SAlist = na.omit(as.character(Hhs[seq, sa_col])) #Array of SA headings
    SAlist = gsub("\\{.*\\}", "", SAlist)
    nSA = length(SAlist)
    
    lastRow = (nSA * SAgap) + values_start_row - 1
    Hhs[values_start_row:lastRow, nof_persons_col] = rep(personCountList, each =
                                                           personCountGap)
    Hhs[values_start_row:lastRow, sa_col] = rep(SAlist, each = SAgap)
    
    hhArr = Hhs[values_start_row:lastRow, header_start_col:value_col]
    return(hhArr)
  }

ReadPersons <-
  function(input_file,
           nof_cols,
           header_start_col,
           value_col,
           values_start_row,
           sa_col,
           rel_col,
           sex_col,
           rel_cats,
           sex_cats,
           age_cats) {
    if (file_ext(input_file) == "zip") {
      csvname = paste(file_path_sans_ext(basename(input_file)), ".csv", sep = "")
      inputCsv = unz(input_file, csvname)
    } else{
      inputCsv = input_file
    }
    Inds <-
      read.csv(
        inputCsv,
        header = F,
        sep = ",",
        fill = T,
        blank.lines.skip = F,
        stringsAsFactors =
          FALSE,
        na.strings = c("", "NA"),
        col.names = paste0("V", seq_len(nof_cols))
      )
    
    dummyVec = 1:length(Inds[, 1])
    
    sexrowi = values_start_row	# Row number for Sex types
    sexgap = length(age_cats)  # Number of rows between each Sex type
    seq = dummyVec[seq(sexrowi, length(dummyVec), sexgap)]
    sexlist = na.omit((as.character(Inds[seq, sex_col]))) #Array of Sex types
    sexlist = gsub("\\{.*\\}", "", sexlist) #if we have added custom tags remove them e.g. Married{2011} -> Married
    nSex = length(sexlist)
    
    relrowi = values_start_row	# Row number for Relationship statuses
    relgap = length(age_cats) * length(sex_cats)  # Number of rows between each Relationship status
    seq = dummyVec[seq(relrowi, length(dummyVec), relgap)]
    rellist = na.omit((as.character(Inds[seq, rel_col]))) #Array of Relationship statuses
    rellist = gsub("\\{.*\\}", "", rellist) #if we have added custom tags remove them e.g. Married{2011} -> Married
    nRel = length(rellist)
    
    SArowi = values_start_row	# Row number for SA IDs
    SAgap = length(age_cats) * length(sex_cats) * length(rel_cats)  # Number of rows between each SA heading
    seq = dummyVec[seq(SArowi, length(dummyVec), SAgap)]
    SAlist = na.omit(as.character(Inds[seq, sa_col])) #Array of SA headings
    SAlist = gsub("\\{.*\\}", "", SAlist)
    nSA = length(SAlist)
    lastRow = (nSA * SAgap) + values_start_row - 1
    
    Inds[values_start_row:lastRow, sa_col] = rep(SAlist, each = SAgap)
    Inds[values_start_row:lastRow, rel_col] = rep(rellist, each = relgap)
    Inds[values_start_row:lastRow, sex_col] = rep(sexlist, each = sexgap)
    indArr = Inds[values_start_row:lastRow, header_start_col:value_col]
    return(indArr)
  }

ReadAges <-
  function(input_file,
           nof_sa2s,
           age_col,
           start_age,
           end_age,
           sa_row,
           data_start_row) {
    if (file_ext(input_file) == "zip") {
      csvname = paste(file_path_sans_ext(basename(input_file)), ".csv", sep = "")
      inputCsv = unz(input_file, csvname)
    } else{
      inputCsv = input_file
    }
    
    padding = 2 # Adding 2 to number of coloumns: one for year coloumn and another for pointless last empty coloumn in ABS csv files
    raw_dat <-
      read.csv(
        inputCsv,
        header = F,
        sep = ",",
        fill = T,
        blank.lines.skip = F,
        stringsAsFactors =
          FALSE,
        na.strings = c("", "NA"),
        col.names = paste0("V", seq_len(nof_sa2s + padding)) # Add 2 here: one for year coloumn and another for pointless last empty coloumn in ABS csv files
      )
    headers = c(raw_dat[(data_start_row-1), age_col], raw_dat[sa_row, c(-1, -(nof_sa2s+padding))] )
    data_end_row = data_start_row + end_age
    data = raw_dat[data_start_row:data_end_row, -(nof_sa2s+padding)]
    colnames(data) <- headers
    return(data)
  }
