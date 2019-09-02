removeZeros <- function(v1, v2){
  remove = c(0)
  zeros = (v2 %in% remove)
  toremove = which (TRUE == zeros)
  if(length(toremove)>0){
    v1out =  v1[-toremove]
    v2out = v2[-toremove]
  }else{
    v1out=v1
    v2out=v2
  }
  return(list("v1"=v1out,"v2"=v2out))
}

SmartRound <- function(v) {
  y <- floor(v)
  indices <- tail(order(v - y), round(sum(v)) - sum(y))
  y[indices] <- y[indices] + 1
  return(y)
}
