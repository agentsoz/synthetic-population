readHouseholds <- function(inFile) {
  print("Reading households file")
  NofCols = 5
  DatCol = 4
  DatRow = 12
  HeaderRow = 11
  HeaderCol = 1
  
  if (file_ext(inFile) == "zip") {
    csvname = paste(file_path_sans_ext(basename(inFile)),".csv",sep = "")
    inputCsv = unz(inFile,csvname)
  }else{
    inputCsv = inFile
  }
  Hhs <-
    read.csv(
      inputCsv, header = F,sep = ",",fill = T,blank.lines.skip = F, stringsAsFactors =
        FALSE,na.strings=c("","NA"), col.names = paste0("V",seq_len(NofCols))
    )

  dummyVec = 1:length(Hhs[,1])
  SAcoli = 1  # Column number for SA IDs
  SArowi = DatRow	# Row number for SA IDs
  SAgap = 112  # Number of rows between each SA heading
  seq =dummyVec[seq(SArowi, length(dummyVec), SAgap)]
  SAlist = na.omit(as.character(Hhs[seq,SAcoli])) #Array of SA headings
  nSA = length(SAlist)
  lastRow = (nSA * SAgap)+DatRow-1
  
  NofPcoli = 2
  NofProwi = DatRow
  NofPgap = 14
  seq =dummyVec[seq(NofProwi, length(dummyVec), NofPgap)]
  Noplist = na.omit((as.character(Hhs[seq,NofPcoli]))) #Array of SA headings
  nNop = length(Noplist)
  
  Hhs[DatRow:lastRow,SAcoli] = rep(SAlist, each=SAgap)
  Hhs[DatRow:lastRow,nopcol] = rep(Noplist, each=NofPgap)
  hhArr = Hhs[DatRow:lastRow,HeaderCol:DatCol]
  print("Reading households file complete")
  return(hhArr)
}

readIndividuals <- function(inFile) {
  print("Reading individuals file")
  NofCols = 6
  DatCol = 5
  DatRow = 12
  HeaderRow = 11
  HeaderCol = 1
  
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
  SAcoli = 1  # Column number for SA IDs
  SArowi = DatRow	# Row number for SA IDs
  SAgap = 128  # Number of rows between each SA heading
  seq =dummyVec[seq(SArowi, length(dummyVec), SAgap)]
  SAlist = na.omit(as.character(Inds[seq,SAcoli])) #Array of SA headings
  nSA = length(SAlist)
  lastRow = (nSA * SAgap)+DatRow-1
  
  relcoli = 2  # Column number for Relationship statuses
  relrowi = DatRow	# Row number for Relationship statuses
  relgap = 16  # Number of rows between each Relationship status
  seq =dummyVec[seq(relrowi, length(dummyVec), relgap)]
  rellist = na.omit((as.character(Inds[seq,relcoli]))) #Array of Relationship statuses
  nRel = length(rellist)
  
  sexcoli = 3  # Column number for Sex types
  sexrowi = DatRow	# Row number for Sex types
  sexgap = 8  # Number of rows between each Sex type
  seq =dummyVec[seq(sexrowi, length(dummyVec), sexgap)]
  sexlist = na.omit((as.character(Inds[seq,sexcoli]))) #Array of Sex types
  nSex = length(sexlist)
  
  Inds[DatRow:lastRow,SAcoli] = rep(SAlist, each=SAgap)
  Inds[DatRow:lastRow,relcoli] = rep(rellist, each = relgap)
  Inds[DatRow:lastRow,sexcoli] = rep(sexlist, each = sexgap)
  indArr = Inds[DatRow:lastRow,HeaderCol:DatCol]
  print("Reading individuals file complete")
  return(indArr)
}



