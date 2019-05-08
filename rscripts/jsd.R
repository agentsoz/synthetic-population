library('philentropy')

JSDivergence <- function(exp_dist, obs_dist){
  if(sum(exp_dist)== 0){
    exp_prob = exp_dist
  }else{
    exp_prob = exp_dist/sum(exp_dist)
  }
  if(sum(obs_dist)== 0){
    obs_prob = obs_dist
  }else{
    obs_prob = obs_dist/sum(obs_dist)
  }
  k <- rbind(exp_prob, obs_prob)
  return(JSD(k, unit="log2" ))
  
}