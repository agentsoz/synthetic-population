library(tools)
library(stringr)
source("util.R")
source("config.R")


ReadSA1HouseholdsInSA2 <-
  function(sa1_files,
           sa2,
           family_types_count,
           hh_sizes_count) {
    for (inFile in sa1_files) {
      csvname = paste(file_path_sans_ext(basename(inFile)), ".csv", sep = "")
      inputCsv = unz(inFile, csvname)
      #This csv cannot be read as a normal csv for some reason. So it is processed manually. When we do read.table we get a one column data set where
      #each row has a sequence of values seperated by commas. We have to divide them into columns to do further process.
      txt <- read.table(inputCsv,
                        sep = "\n",
                        fill = FALSE,
                        strip.white = TRUE)
      end_line = nrow(txt)
      
      # get the SA1s
      sa1s = unlist(strsplit(as.character(txt[sa1_row, 1]), ","))
      sa1s = sa1s[sa1_start_col:length(sa1s)]
      
      # get household type column names
      hhtype_col_names = unlist(strsplit(as.character(txt[colname_row, 1]), ","))[1:3]
      col_names <- c(hhtype_col_names, sa1s)
      
      # convert the data part into a matrix by seperating each line by comma.
      data = txt[c(data_start_row:end_line), 1]
      data = unlist(strsplit(as.character(data), ","))
      data_mat = matrix(data, ncol = length(col_names), byrow = T)
      colnames(data_mat) <- col_names # finally assign column names
      
      row_count_per_sa2 = family_types_count * hh_sizes_count
      
      #find the chunk in the data matrix that is relevant to the SA2 we process
      sa2_row_id = which(data_mat[, sa2_col] == sa2)
      
      if (length(sa2_row_id) != 0) {
        sa2_chunk <- data_mat[c(sa2_row_id:(sa2_row_id+row_count_per_sa2-1)),]
        val_only_cells =
          sa2_chunk[,-sa2_col:-family_hh_type_col]
        
        #If there are no households in an SA1 that means the SA1 is not part of the SA2 we process at the moment.
        nonempty_sa1_coli_vec =
          which(colSums(matrix(
            as.numeric(unlist(val_only_cells)), nrow = nrow(val_only_cells)
          )) > 0)
        real_non_empty_coli_vec = c(1:3, (nonempty_sa1_coli_vec + 3))# prepending hh type name columns
        
        sa2_chunk <- sa2_chunk[, real_non_empty_coli_vec]
        
        nop_titles = sa2_chunk[which(sa2_chunk[, hh_size_col] != ""), hh_size_col]
        sa2_chunk[, hh_size_col] <-
          rep(nop_titles, each = family_types_count)
        sa2_chunk[, sa2_col] <- sa2
        
        return(sa2_chunk)
      }
      
    }
  }

if (FALSE) {
  test_files = "../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_Inner.zip,
  ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_Inner_East.zip,
  ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_Inner_South.zip,
  ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_North_East.zip,
  ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_North_West.zip,
  ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_Outer_East.zip,
  ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_South_East.zip,
  ../../data/melbourne/raw/Household_2016_by_SA2_Melbourne_West.zip"
  readSA1HouseholdsInSA2(unlist(strsplit(test_files, ",")), "Brunswick - East", 14, 8)
  
}