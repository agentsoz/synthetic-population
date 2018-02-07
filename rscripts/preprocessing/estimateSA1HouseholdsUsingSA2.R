library(tools)
source("util.R")

readSA1HouseholdsInSA2 <- function(inFile) {

  if (file_ext(inFile) == "zip") {
    csvname = paste(file_path_sans_ext(basename(inFile)),".csv",sep = "")
    inputCsv = unz(inFile,csvname)

    naline = 5
    #below two considers naline will be deleted from data 
    datStartRow  = 4
    datEndRow = 116
  }else{
    inputCsv = inFile
    naline = 6
    #below two considers naline will be deleted from data 
    datStartRow  =5
    datEndRow = 117
  }
  Hhs <-
    read.csv(
      inputCsv, header = F,sep = ",",fill = T,blank.lines.skip = F, stringsAsFactors =
        FALSE,na.strings=c("","NA")
    )
  
  Hhs <- Hhs[-naline,] # Deleteing a nuisance  empty row
  Hhs <- Hhs[,-1]
  updatedHhs = Hhs[datStartRow:datEndRow,-ncol(Hhs)]
  
  dummyVec = 1:length(updatedHhs[,1])
  NOPcoli = 1  # Column number for SA IDs
  NOProwi = 2	# Row number for SA IDs
  NOPgap = 14  # Number of rows between each SA heading
  seq =dummyVec[seq(NOProwi, length(dummyVec), NOPgap)]
  NOPlist = na.omit(as.character(updatedHhs[seq,NOPcoli])) #Array of SA headings
  nNOP = length(NOPlist)
  lastRow = (nNOP * NOPgap)+1

  updatedHhs[NOProwi:lastRow,1] = rep(NOPlist, each=NOPgap)
  
  
  return(updatedHhs)
  
}

if(FALSE){

  dataHome = Sys.getenv("ANONDATA_HOME")
  hhinput = paste(dataHome,"latch/raw/Hh-SA1-in-each-SA2/Ivanhoe.csv",sep="")
  indinput = paste(dataHome,"/latch/raw/SA2, RLHP Relationship in Household, SEXP and AGE5P.csv",sep="")
  readSA1HouseholdsInSA2(hhinput);
}