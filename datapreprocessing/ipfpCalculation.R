library("mipfp")
library("stringr")

ipfpCalculate <- function(rowMarginal, columnMarginal, seed) {
  target.list <- list(1,2)
  target.data = list(rowMarginal, columnMarginal)
  
  if (sum(rowMarginal) == 0 | sum(columnMarginal) == 0) {
    cat("zero sums")
    zerosumlist <- c(zerosumlist, sa)
    ipfresult$p.hat = array(0,dim = c(length(rowMarginal),length(columnMarginal)))
  }else{
    ipfresult <-
      Ipfp(seed, target.list, target.data)
  }
  return(ipfresult)
}

seedLatchAgeLatchRelSex <- function(rowCount, columnCount){
  ##NOTE : Age ranges are sorted in descending order in ABS data files##
  marMseq = 1:7
  marFseq = 9:15
  LParentMseq = 17:23
  LParentFseq = 25:31
  U15cMseq =  40
  U15cFseq =  48
  StuMseq =  55
  StuFseq =  63
  O15cMseq =  65:71
  O15cFseq =  73:79
  GrpMseq = 81:87
  GrpFseq = 89:95
  LPersonMseq = 97:103
  LPersonFseq = 105:111
  RelMseq= 113:119
  RelFseq= 121:127
  
  seed <- array(0,dim=c(rowCount,columnCount))
  #Lone person Hh
  seed[13,c(LPersonMseq,LPersonFseq)] = 1
  #Two person households
  seed[15,c(marMseq,marFseq)] = 1 #2P 1F Couple only
  seed[17,c(LParentMseq,LParentFseq,U15cMseq,U15cFseq,StuMseq,StuFseq,O15cMseq, O15cFseq)] = 1 #2P 1F Lone parent
  seed[18,c(RelMseq,RelFseq)] = 1 #2P 1F Other Family
  seed[28,c(GrpMseq,GrpFseq)] = 1 #2P 1F GrooupHh
  
  #3-8P 1F households
  seed[c(29,43,57,71,85,99),c(marMseq,marFseq,RelMseq,RelFseq)] = 1 #1F Couple only (can have relatives)
  seed[c(30,44,58,72,86,100),c(marMseq,marFseq,U15cMseq,U15cFseq,StuMseq,StuFseq,O15cMseq, O15cFseq,RelMseq,RelFseq)] = 1 #1F Couple with children
  seed[c(31,45,59,73,87,101),c(LParentMseq,LParentFseq,U15cMseq,U15cFseq,StuMseq,StuFseq,O15cMseq, O15cFseq,RelMseq,RelFseq)] = 1 #1F Lone Parent Family (LP+Child+Relative)
  seed[c(32,46,60,74,88,102),c(RelMseq,RelFseq)] = 1 #1F Other family of 3 relatives 
  seed[c(42,56,70,84,98,112),c(GrpMseq,GrpFseq)] = 1 #1F Group household
  
  #4-8P 2F households
  seed[c(47,61,75,89,103),c(marMseq,marFseq,RelMseq,RelFseq)] = 1 #1F Couple only
  seed[c(   62,76,90,104),c(marMseq,marFseq,LParentMseq,LParentFseq,U15cMseq,U15cFseq,StuMseq,StuFseq,O15cMseq,O15cFseq,RelMseq,RelFseq)] = 1 #1F Couple with children
  seed[c(49,63,77,91,105),c(marMseq,marFseq,LParentMseq,LParentFseq,U15cMseq,U15cFseq,StuMseq,StuFseq,O15cMseq,O15cFseq,RelMseq,RelFseq)] = 1 #1F Lone Parent Family (LP+Child+Relative)
  seed[c(50,64,78,92,106),c(marMseq,marFseq,LParentMseq,LParentFseq,U15cMseq,U15cFseq,StuMseq,StuFseq,O15cMseq,O15cFseq,RelMseq,RelFseq)] = 1 #1F Other family of 3 relatives 
  
  #6-8P 3F households
  seed[c(79,93,107),c(marMseq,marFseq,RelMseq,RelFseq)] = 1 #1F Couple only
  seed[c(   94,108),c(marMseq,marFseq,LParentMseq,LParentFseq,U15cMseq,U15cFseq,StuMseq,StuFseq,O15cMseq,O15cFseq,RelMseq,RelFseq)] = 1 #1F Couple with children
  seed[c(81,95,109),c(marMseq,marFseq,LParentMseq,LParentFseq,U15cMseq,U15cFseq,StuMseq,StuFseq,O15cMseq,O15cFseq,RelMseq,RelFseq)] = 1 #1F Lone Parent Family (LP+Child+Relative)
  seed[c(82,96,110),c(marMseq,marFseq,LParentMseq,LParentFseq,U15cMseq,U15cFseq,StuMseq,StuFseq,O15cMseq,O15cFseq,RelMseq,RelFseq)] = 1 #1F Other family of 3 relatives 
  
  return(seed)
}