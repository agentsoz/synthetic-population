readHouseholds <- function(inFile, nofCols, headerStartingCol, valueColi,  valuesStartingRow, SAColi, personCountColi, familyTypeColi) {
  
  print("Reading households file")
  if (file_ext(inFile) == "zip") {
    csvname = paste(file_path_sans_ext(basename(inFile)),".csv",sep = "")
    inputCsv = unz(inFile,csvname)
  }else{
    inputCsv = inFile
  }
  Hhs <-
    read.csv(
      inputCsv, header = F,sep = ",",fill = T,blank.lines.skip = F, stringsAsFactors =
        FALSE,na.strings=c("","NA"), col.names = paste0("V",seq_len(nofCols))
    )

  dummyVec = 1:length(Hhs[,1])
  SArowi = valuesStartingRow	# Row number for SA IDs
  SAgap = 112  # Number of rows between each SA heading
  seq =dummyVec[seq(SArowi, length(dummyVec), SAgap)]
  SAlist = na.omit(as.character(Hhs[seq,SAColi])) #Array of SA headings
  nSA = length(SAlist)
  lastRow = (nSA * SAgap)+valuesStartingRow-1
  
  personCountRowi = valuesStartingRow
  personCountGap = 14
  seq =dummyVec[seq(personCountRowi, length(dummyVec), personCountGap)]
  personCountList = na.omit((as.character(Hhs[seq,personCountColi]))) #Array of SA headings
  nNop = length(personCountList)
  
  Hhs[valuesStartingRow:lastRow,SAColi] = rep(SAlist, each=SAgap)
  Hhs[valuesStartingRow:lastRow,personCountColi] = rep(personCountList, each=personCountGap)
  hhArr = Hhs[valuesStartingRow:lastRow,headerStartingCol:valueColi]
  print("Reading households file complete")
  return(hhArr)
}

readPersons <- function(inFile, NofCols, headerStartingCol, valueColi,  valuesStartingRow, SAColi, relColi, sexColi ) {
  print("Reading individuals file")

  if (file_ext(inFile) == "zip") {
    csvname = paste(file_path_sans_ext(basename(inFile)),".csv",sep = "")
    inputCsv = unz(inFile,csvname)
  }else{
    inputCsv = inFile
  }
  Inds <-
    read.csv(
      inputCsv, header = F,sep = ",",fill = T,blank.lines.skip = F, stringsAsFactors =
        FALSE,na.strings=c("","NA"),col.names = paste0("V",seq_len(NofCols))
    )
  
  dummyVec = 1:length(Inds[,1])
  SArowi = valuesStartingRow	# Row number for SA IDs
  SAgap = 128  # Number of rows between each SA heading
  seq =dummyVec[seq(SArowi, length(dummyVec), SAgap)]
  SAlist = na.omit(as.character(Inds[seq,SAColi])) #Array of SA headings
  nSA = length(SAlist)
  lastRow = (nSA * SAgap)+valuesStartingRow-1
  
  relColi = 2  # Column number for Relationship statuses
  relrowi = valuesStartingRow	# Row number for Relationship statuses
  relgap = 16  # Number of rows between each Relationship status
  seq =dummyVec[seq(relrowi, length(dummyVec), relgap)]
  rellist = na.omit((as.character(Inds[seq,relColi]))) #Array of Relationship statuses
  nRel = length(rellist)
  
  sexrowi = valuesStartingRow	# Row number for Sex types
  sexgap = 8  # Number of rows between each Sex type
  seq =dummyVec[seq(sexrowi, length(dummyVec), sexgap)]
  sexlist = na.omit((as.character(Inds[seq,sexColi]))) #Array of Sex types
  nSex = length(sexlist)
  
  Inds[valuesStartingRow:lastRow,SAColi] = rep(SAlist, each=SAgap)
  Inds[valuesStartingRow:lastRow,relColi] = rep(rellist, each = relgap)
  Inds[valuesStartingRow:lastRow,sexColi] = rep(sexlist, each = sexgap)
  indArr = Inds[valuesStartingRow:lastRow,headerStartingCol:valueColi]
  print("Reading individuals file complete")
  return(indArr)
}



