

ReadBySA <- function(arr, SAid) {
  rwids = which(arr[, 1] == SAid)
  return(arr[rwids, ])
}

GetAllSAs <- function(filename, startrow, endrow, sacol) {
  sas = read.csv(filename, header = F)
  return(sas[startrow:endrow, sacol, sacol])
}

GetMatchingRowIds <- function(arr, col, key) {
  rwids = as.numeric(rownames(subset(arr, arr[,col] == key)))
  # rwids = which(arr[, col] == key)
  return(rwids)
}

GetMatchingColIds <- function(arr, row, key) {
  colids = as.numeric(colnames(subset(arr, arr[row,] == key)))
  return(colids)
}

CreateDir <- function(directory_path) {
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

SA2FilePrefix <- function(sa2) {
  sa2_prefix = unlist(strsplit(gsub("-|\\(|\\)|\\.", "", sa2), "\\s+"))
  if (length(sa2_prefix) > 1) {
    sa2_prefix = tolower(paste(substr(sa2_prefix, 1, 4), collapse = "_"))
  } else{
    sa2_prefix = tolower(sa2_prefix)
  }
  return(sa2_prefix)
}

GetCsvInZip <- function(input_file, csv_name = NULL) {
  if (file_ext(input_file) == "zip") {
    if (is.null(csv_name)) {
      csv_name = paste(file_path_sans_ext(basename(input_file)), ".csv", sep = "")
    }
    inputCsv = unz(input_file, csv_name)
  } else{
    inputCsv = input_file
  }
}

OrderAgeDescending <- function(p_marg){
  block_start = 0
  block_end = 0
  age_blocks = nrow(p_marg)/length(age_cats)
  for(i in c(1:age_blocks)) {
    block_start = block_end + 1
    block_end = block_end+length(age_cats)
    block = p_marg[c(block_start:block_end),]
    age_desc_row_order = match(block[,4], rev(age_cats)) 
    p_marg[c(block_start:block_end),] <- block[age_desc_row_order,]
  }
  return(p_marg)
}