#!/usr/bin/env Rscript

readBySA <- function(arr, SAid){
  rwids = which(arr[,1] == SAid)
  return(arr[rwids,])
}

getAllSAs <- function(filename,startrow,endrow,sacol){
  sas = read.csv(filename,header = F)
  return(sas[startrow:endrow,sacol,sacol])
}

getMatchingRowIds <- function(arr, col, key){
  rwids = which(arr[,col] == key)
  return(rwids)
}

getMatchingColIds <- function(arr,row,key){
  colids = which(arr[row,] == key)
  return(colids)
}
