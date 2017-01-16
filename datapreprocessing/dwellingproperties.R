#!/usr/bin/env Rscript
library(optparse)
library(tools)
library(stringr)

procDwellProperties <-function(dwellZip,outputDir){

csvFile = paste(file_path_sans_ext(basename(dwellZip)),".csv",sep="")
saCount = 613
valueCol = 6
dataStartCol = 2
dataStartRow = 6
STRDcats = 5
BEDDcats = 6
TENLLDcats = 4
rows4SA1 = STRDcats*BEDDcats*TENLLDcats
ttlRows = rows4SA1*saCount
dataEndRow = dataStartRow+ttlRows-1

data = read.csv(unz(dwellZip, csvFile), header=F, blank.lines.skip = F, sep=",",stringsAsFactors=FALSE)

startingRowSeq = seq(dataStartRow,dataEndRow-1,rows4SA1)
endingRowSeq = seq(dataStartRow+rows4SA1-1, dataEndRow, rows4SA1)
allValues = as.numeric(as.character(data[dataStartRow:dataEndRow,valueCol]))
allValuesBySA1 = matrix(unlist(allValues), nrow = saCount, byrow = T)


sumsBySA1 = rowSums(allValuesBySA1)

proportions = allValuesBySA1/sumsBySA1
data[dataStartRow:dataEndRow,valueCol]=as.character(matrix(t(proportions), ncol=1))
data = data[(dataStartRow-1):dataEndRow,dataStartCol:valueCol]
data = sapply(data,function(x) trimws(gsub("[{B}]","",x)))
data[is.na(data)] <- " "
write.table(as.data.frame(data),paste(outputDir,csvFile,sep="/"),col.names = F, row.names = F,sep=",")
}