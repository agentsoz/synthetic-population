
ReadBySA <- function(arr, SAid){
  rwids = which(arr[,1] == SAid)
  return(arr[rwids,])
}

GetAllSAs <- function(filename,startrow,endrow,sacol){
  sas = read.csv(filename,header = F)
  return(sas[startrow:endrow,sacol,sacol])
}

GetMatchingRowIds <- function(arr, col, key){
  rwids = which(arr[,col] == key)
  return(rwids)
}

GetMatchingColIds <- function(arr,row,key){
  colids = which(arr[row,] == key)
  return(colids)
}
