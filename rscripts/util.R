
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

CreateDir <-function(directory_path){
  ifelse(
    !dir.exists(path = directory_path),
    dir.create(
      path = directory_path,
      showWarnings = T,
      recursive = T
    ),
    FALSE
  )
}

FillAccording2Dist <- function(dataarray, amount) {
  if (sum(dataarray) == 0) {
    #if all data array cells are 0, we cannot approximate a distribution. So we assign the amount to random cells assuming a uniform distribution.
    idx = ceiling(runif(amount, 0, length(dataarray)))
    for (i in idx) {
      dataarray[i] <- dataarray[i] + 1
    }
  } else{
    dist = dataarray / sum(dataarray)
    newAddition = dist * amount
    if ((round(sum(newAddition)) - sum(floor(newAddition))) != 0) {
      newAddition = SmartRound(newAddition)
    }
    dataarray = dataarray + newAddition
  }
  return(dataarray)
}

SmartRound <- function(x) {
  y <- floor(x)
  indices <- tail(order(x - y), round(sum(x)) - sum(y))
  y[indices] <- y[indices] + 1
  return(y)
}