library("entropy")
KLDivergence <- function(exp_dist, obs_dist, laplace_smoothing_param){
  
  exp_probs = LaplaceSmoothing(exp_dist, laplace_smoothing_param)
  obs_probs = LaplaceSmoothing(obs_dist, laplace_smoothing_param)
 
  
  return(KL.plugin(exp_probs, obs_probs))
  
}

LaplaceSmoothing <- function(dist, smoothing_param){
  
  d = length(dist)
  return ((dist+smoothing_param)/(sum(dist)+(d*smoothing_param)))
  
}