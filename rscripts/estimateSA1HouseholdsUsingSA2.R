library(tools)
library(stringr)


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
                        fill = F,
                        strip.white = T)
      end_line = sa1_data_start_row + (floor(nrow(txt)/hh_types_count)*hh_types_count)-1
      
      # get the SA1s
      sa1s = unlist(strsplit(as.character(txt[sa1_row, 1]), ","))
      sa1s = sa1s[sa1_start_col:length(sa1s)]
      
      # get household type column names
      hhtype_col_names = unlist(strsplit(as.character(txt[colname_row, 1]), ","))[1:3]
      col_names <- c(hhtype_col_names, sa1s)
      
      # convert the data part into a matrix by seperating each line by comma.
      data = txt[c(sa1_data_start_row:end_line), 1]
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

EstimateSA1HouseholdsDistribution <- function(sa2, sa2_hh_dist,sa1_hhs_dist){
  #Following code iterates on hh types distributing them among SA1s. i.e each row represent a hh type
  rowcount = nrow(sa2_hh_dist)
  lastcol = ncol(sa1_hhs_dist)
  
  sa2_sa1_conflicts = FALSE
  mismatching_hh_types = c()
  #If none of the SA1s have households in them, SA1HhDist only has 3 columns with SA2 name, hh size and
  #family houshoeld type. So lastcol = 3 and sa1_start_col = 4
  if (sa1_start_col < lastcol) {
    #If this SA2's SA1 level distribution has any households we can approximate a suitable distribution.
    #If there none of the SA1s have households according to the distribution we don't know where to put them.
    #So we skip such SA2s
    for (i in 1:rowcount) {
      sa1hhs = as.numeric(sa1_hhs_dist[i, sa1_start_col:lastcol]) #get data cells by skipping row and col headers
      sa1hhsttl = sum(sa1hhs)
      sa2hhttl = sa2_hh_dist[i, 4]
      
      #Distribute SA2 Hhs among SA1s assuming SA2 data is always correct
      if (sa2hhttl == 0) {
        #If there are no hhs in SA2 in current row, then there must be no hhs in SA1.
        adjustedSA1Hhs = (sa1hhs * 0)
      } else if ((sa2hhttl - sa1hhsttl) > 0 &
                 sum(sa1hhs) == 0) {
        #There are extra hhs of current type in SA2, but none in the SA1s.
        # In this case FillAccording2Dist function distributes hhs among randomly selected SA1s
        adjustedSA1Hhs = FillAccording2Dist(sa1hhs, (sa2hhttl - sa1hhsttl))
        sa2_sa1_conflicts = TRUE
        mismatching_hh_types = c(mismatching_hh_types,unname(unlist(sa1_hhs_dist[i,c(2:3)])))
      } else{
        #Redistribute hhs among SA1 according to the current distribution. At the end of this, total hhs in SA1s match the total in SA2
        adjustedSA1Hhs = FillAccording2Dist(sa1hhs, (sa2hhttl - sa1hhsttl))
      }
      
      sa1_hhs_dist[i, sa1_start_col:lastcol] = adjustedSA1Hhs
    }
    if(sa2_sa1_conflicts){
      flog.info("Some household types were represented in SA2 data but not in SA1 data. These households were assigned to randomly selected SA1s")
      flog.info(mismatching_hh_types)
    }
    return(sa1_hhs_dist)
  } else{
    return(NULL)
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
