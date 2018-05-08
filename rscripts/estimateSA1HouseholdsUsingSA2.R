library(tools)
library(stringr)

sa1_dist_data <- list()

LoadSA1HouseholdsInSA2 <- function(sa1_files) {
  sa1_dist_data <- list()
  for (i in 1:length(sa1_files)) {
    inFile = sa1_files[i]
    csvname = paste(file_path_sans_ext(basename(inFile)), ".csv", sep = "")
    inputCsv = unz(inFile, csvname)
    #This csv cannot be read as a normal csv for some reason. So it is processed manually. When we do read.table we get a one column data set where
    #each row has a sequence of values seperated by commas. We have to divide them into columns to do further process.
    txt <- read.table(inputCsv,
                      sep = "\n",
                      fill = F,
                      strip.white = T)
    end_line = sa1_data_start_row + (floor(nrow(txt) / hh_types_count) *
                                       hh_types_count) - 1
    
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
    sa1_dist_data[[i]] <- data_mat
  }
  
  return(sa1_dist_data)
}

GetSA1HouseholdDistInSA2 <-
  function(sa1_hh_dists,
           sa2,
           family_types_count,
           hh_sizes_count,
           sa2_code_map_file) {
    for (data_mat in sa1_hh_dists) {
      row_count_per_sa2 = family_types_count * hh_sizes_count
      
      #find the chunk in the data matrix that is relevant to the SA2 we process
      sa2_row_id = which(data_mat[, sa2_col] == sa2)
      
      if (length(sa2_row_id) != 0) {
        sa2_chunk <-
          data_mat[c(sa2_row_id:(sa2_row_id + row_count_per_sa2 - 1)), ]
        nop_titles = sa2_chunk[which(sa2_chunk[, hh_size_col] != ""), hh_size_col]
        
        #SA2 codes and SA1 codes have following relationship. So if we know the SA2 5 digit code we can figure out its SA1s from a list
        #Example: SA2 51041
        # S/T SA2
        # 5   1041
        
        #Example: SA1 5104118
        # S/T	SA2	  SA1
        # 5	  1041  18
        
        code_map_csv = GetCsvInZip(sa2_code_map_file, m_sa2_codes_csv)
        code_map = read.csv(code_map_csv)
        sa2_5digcode = code_map[code_map$SA2_NAME_2016 == sa2, "SA2_5DIGITCODE_2016"]
        
        sa1_prefix_pattern = paste("^",sa2_5digcode,sep="")
        sa1s = colnames(sa2_chunk)[grepl(sa1_prefix_pattern, colnames(sa2_chunk))]
        selected_cols = c(colnames(sa2_chunk)[1:3], sa1s) # prepending sa2 name, num of persons and family hh type columns
        sa2_chunk <- sa2_chunk[,selected_cols]
        
        sa2_chunk[, hh_size_col] <- rep(nop_titles, each = family_types_count)
        sa2_chunk[, sa2_col] <- sa2
        
        return(sa2_chunk)
      }
      
    }
  }

EstimateSA1HouseholdsDistribution <-
  function(sa2, sa2_hh_dist, sa1_hhs_dist) {
    #Following code iterates on hh types distributing them among SA1s. i.e each row represent a hh type
    rowcount = nrow(sa2_hh_dist)
    lastcol = ncol(sa1_hhs_dist)
    
    sa2_sa1_conflicts = FALSE
    mismatching_hh_types = c()
    #If at least one of the SA1s have households in them, sa1_hhs_dist must at least have 4 columns: SA2 name, hh size, family houshoeld type and one sa1 coloumn.
    #So lastcol in the sa1_hh_dist must be >= 4. As sa1_start_col = 4 in config.R, following is true if there are sa1 households
    if (sa1_start_col <= lastcol) {
      #If this SA2's SA1 level distribution has any households we can approximate a suitable distribution.
      #If none of the SA1s have households according to the distribution we don't know where to put them.
      #So we skip such SA2s
      value_cells <-sa1_hhs_dist[ ,sa1_start_col:lastcol] #get data cells by skipping row and col headers
      class(value_cells) <- "numeric"
      for (i in 1:rowcount) {
        
        sa1hhs = value_cells[i, ] 
        sa1hhsttl = sum(sa1hhs)
        sa2hhttl = sa2_hh_dist[i, 4]
        if(i == 49){
          print("X")
        }
        #Distribute SA2 Hhs among SA1s assuming SA2 data is always correct
        if (sa2hhttl == 0) {
          #If there are no hhs in SA2 in current row, then there must be no hhs in SA1.
          adjustedSA1Hhs = (sa1hhs * 0)
        } else if ((sa2hhttl - sa1hhsttl) > 0 &
                   sum(sa1hhs) == 0) {
          #There are extra hhs of current type in SA2, but none in the SA1s.
          # In this case we get the SA1s that had different household types and randomly distribute the households
          non_empty_sa1s = which(colSums(value_cells) > 0) #Get the SA1s in that are not empty in whole SA2
          adjustedSA1Hhs = sa1hhs #book keeping
          #FillAccording2Dist function randomly assigns items to specified vector if the vector sum is 0. Here we pass the SA1s that are known 
          #to have other household types though there are no household of current type. This way we don't assign households to SA1s covering parks and
          #industrial areas.
          adjustedSA1Hhs[non_empty_sa1s] = FillAccording2Dist(sa1hhs[non_empty_sa1s], (sa2hhttl - sa1hhsttl))
          sa2_sa1_conflicts = TRUE
          mismatching_hh_types = c(mismatching_hh_types, unname(unlist(sa1_hhs_dist[i, c(2:3)])))
        } else{
          #Redistribute hhs among SA1 according to the current distribution. At the end of this, total hhs in SA1s match the total in SA2
          adjustedSA1Hhs = FillAccording2Dist(sa1hhs, (sa2hhttl - sa1hhsttl))
        }
        
        sa1_hhs_dist[i, sa1_start_col:lastcol] = adjustedSA1Hhs
      }
      if (sa2_sa1_conflicts) {
        flog.info(
          "Some household types were represented in SA2 data but not in SA1 data. These households were assigned to randomly selected SA1s"
        )
        flog.info(mismatching_hh_types)
      }
      return(sa1_hhs_dist)
    } else{
      return(NULL)
    }
  }

if (FALSE) {
  source("config.R")
  test_files = "../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner.zip,
  ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner_East.zip,
  ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Inner_South.zip,
  ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_North_East.zip,
  ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_North_West.zip,
  ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_Outer_East.zip,
  ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_South_East.zip,
  ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Melbourne_West.zip,
  ../data/melbourne/raw/SA1_households_dist_in_SA2s_2016_Mornington_Peninsula.zip"
  all_sa1_hh_dists <-
    LoadSA1HouseholdsInSA2(unlist(lapply(strsplit(
      sub("\\n", "", test_files), ","
    ), trimws)))
  
  sa1_hh_dist = GetSA1HouseholdDistInSA2(all_sa1_hh_dists, "Port Melbourne Industrial", 14, 8,"../data/melbourne/raw/1270055001_sa2_2016_aust_csv.zip")
}
