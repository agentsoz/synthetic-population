FreemanTukeyTest <- function(exp_dist, obs_dist) {
  ob_count = length(obs_dist)
  ex_count = length(exp_dist)
  if (ob_count != ex_count) {
    stop("vectors are different in size")
  }
  
  err = 0
  for (i in 1:ob_count) {
    err = err + (sqrt(obs_dist[i]) - sqrt(exp_dist[i])) ^ 2
  }
  FT.statistic = 4 * err
  FT.df = ob_count - 1
  FT.p.value  = pchisq(FT.statistic, FT.df, lower.tail = FALSE)
  
  result <- data.frame(FT.statistic, FT.p.value, FT.df)
  return(result)
} 

FreemanTukeyTest2 <- function(exp_dist_p, obs_dist) {
  exp_dist = exp_dist_p*sum(obs_dist)
  
  
  ob_count = length(obs_dist)
  ex_count = length(exp_dist)
  if (ob_count != ex_count) {
    stop("vectors are different in size")
  }
  
 
  err = 0
  for (i in 1:ob_count) {
    err = err + (sqrt(obs_dist[i]) - sqrt(exp_dist[i])) ^ 2
  }
  FT.statistic = 4 * err
  FT.df = ob_count - 1
  FT.p.value  = pchisq(FT.statistic, FT.df, lower.tail = FALSE)
  
  result <- data.frame(FT.statistic, FT.p.value, FT.df)
  return(result)
} 