library("entropy")
library("Matching")

FreemanTukeyTest <- function(exp_dist, obs_dist, simulate_p_value = F, sim_samples = 1000) {
  ob_count = length(obs_dist)
  ex_count = length(exp_dist)
  if (ob_count != ex_count) {
    stop("vectors are different in size")
  }

  ft_result = ft.test(x = obs_dist, p = exp_dist/sum(exp_dist), simulate.p.value = simulate_p_value, B = sim_samples)
  # result <- data.frame(FT.statistic, FT.p.value, FT.df)
  return(ft_result)
}


TostWilcoxon <- function(exp_dist, obs_dist, mu, alpha = 0.05) {

  grt = wilcox.test(
    x = exp_dist,
    y = obs_dist,
    paired = T,
    alternative = "greater",
    mu = -mu
  )
  les = wilcox.test(
    x = exp_dist,
    y = obs_dist,
    paired = T,
    alternative = "less",
    mu = mu
  )

  TOST.p.value = max(c(grt$p.value, les$p.value))
  TOST.alt.accept= (TOST.p.value < alpha)
  return(data.frame(TOST.p.value, TOST.alt.accept))

}

CosineSimilarity <- function(exp_dist, obs_dist)
{
  A = exp_dist
  B = obs_dist
  Cosine.similarity = sum(A*B)/sqrt(sum(A^2)*sum(B^2))
  return(data.frame(Cosine.similarity))
}


KLDivergence <- function(exp_dist, obs_dist, laplace_smoothing_param = NA){

  if(is.na(laplace_smoothing_param)){
    smooth_exp = exp_dist
    smooth_obs = obs_dist
  }else{
    smooth_exp = LaplaceSmoothing(exp_dist, laplace_smoothing_param)
    smooth_obs = LaplaceSmoothing(obs_dist, laplace_smoothing_param)
  }

  if(sum(smooth_obs) == 0){
    return(NA)
  }
  return(entropy::KL.plugin(smooth_exp, smooth_obs))

}

LaplaceSmoothing <- function(dist, smoothing_param){

  d = length(dist)
  smoothed_probs = (dist+smoothing_param)/(sum(dist)+(d*smoothing_param))
  renormalised_dist = smoothed_probs*sum(dist)
  return (renormalised_dist)

}

TOSTKolmogorovSmirnov <- function(exp_dist, obs_dist){

  grt = ks.boot(Tr = obs_dist, Co = exp_dist, nboots=500, alternative = "greater")
  les = ks.boot(Tr = obs_dist, Co = exp_dist, nboots=500, alternative = "less")

  TOST.p.value = max(c(grt$ks.boot.pvalue, les$ks.boot.pvalue))
  return(data.frame(TOST.p.value))

}

Error <-function(exp_dist, obs_dist){
  err_vec = exp_dist - obs_dist
  err.median = median(err_vec)
  err.sd = median(err_vec)
  sae = sum(abs(err_vec))/sum(exp_dist)
  return(list(err.median = err.median, err.sd = err.sd, sae = sae))
}

ChiSquaredGOFTest <- function(exp_dist, obs_dist, laplace_smooth_param = NA, simulate_p_value = F, sim_samples = 1000){
  ob_len = length(obs_dist)
  ex_len = length(exp_dist)
  if (ob_len != ex_len) {
    stop("Vectors are different in size. They should be similar.")
  }
  if(!is.na(laplace_smooth_param)){
    exp_dist = LaplaceSmoothing(exp_dist, laplace_smooth_param)
    obs_dist = LaplaceSmoothing(obs_dist, laplace_smooth_param)
  }

  chisq_result = chisq.test(x = obs_dist, p = exp_dist/sum(exp_dist), simulate.p.value = simulate_p_value, B = sim_samples, rescale.p = T)

  return(chisq_result)

}

ChiSquaredHomogeneityTest <- function(exp_dist, obs_dist, laplace_smooth_param = NA, simulate_p_value = F){
  ob_len = length(obs_dist)
  ex_len = length(exp_dist)
  if (ob_len != ex_len) {
    stop("Vectors are different in size. They should be similar.")
  }
  if(!is.na(laplace_smooth_param)){
    exp_dist = LaplaceSmoothing(exp_dist, laplace_smooth_param)
    obs_dist = LaplaceSmoothing(obs_dist, laplace_smooth_param)
  }

  chisq_result = chisq.test(x = rbind(exp_dist, obs_dist), simulate.p.value = simulate_p_value, correct = F, B = 1000)
  chisq_result$laplace.smoothing.param = laplace_smooth_param

  return(chisq_result)
}


ft.test <- function (x, y = NULL, correct = FALSE, p = rep(1/length(x), length(x)), rescale.p = FALSE,
                     simulate.p.value = FALSE,B = 2000){
  DNAME <- deparse(substitute(x))
  if (is.data.frame(x))
    x <- as.matrix(x)
  if (is.matrix(x)) {
    if (min(dim(x)) == 1L)
      x <- as.vector(x)
  }
  if (!is.matrix(x) && !is.null(y)) {
    if (length(x) != length(y))
      stop("'x' and 'y' must have the same length")
    DNAME2 <- deparse(substitute(y))
    xname <- if (length(DNAME) > 1L || nchar(DNAME, "w") >
                 30)
      ""
    else DNAME
    yname <- if (length(DNAME2) > 1L || nchar(DNAME2, "w") >
                 30)
      ""
    else DNAME2
    OK <- complete.cases(x, y)
    x <- factor(x[OK])
    y <- factor(y[OK])
    if ((nlevels(x) < 2L) || (nlevels(y) < 2L))
      warning("'x' and 'y' do not have at least 2 levels")
    x <- table(x, y)
    names(dimnames(x)) <- c(xname, yname)
    DNAME <- paste(paste(DNAME, collapse = "\n"), "and",
                   paste(DNAME2, collapse = "\n"))
  }
  if (any(x < 0) || anyNA(x))
    stop("all entries of 'x' must be nonnegative and finite")
  if ((n <- sum(x)) == 0){
    warning("all entries of 'x' are zero. using total in 'y' as the expected population size")
    if((n <- sum(y)) == 0){
      warning("all entries of 'y' are also zero or you have provided 'p'. cannot estimate expected population size. returning NA")
      return(structure(list(statistic = NA, parameter = NA,
         p.value = NA, method = "Freeman-Tukey's test", data.name = NA,
         observed = x, expected = NA, residuals = NA,
         stdres = NA), class = "htest"))
    }
  }
  if (simulate.p.value) {
    setMETH <- function() METHOD <<- paste(METHOD, "with simulated p-value\n\t (based on",
                                           B, "replicates)")
    almost.1 <- 1 - 64 * .Machine$double.eps
  }
  if (is.matrix(x)) {
    METHOD <- "Freeman-Tukey's test"
    nr <- as.integer(nrow(x))
    nc <- as.integer(ncol(x))
    if (is.na(nr) || is.na(nc) || is.na(nr * nc))
      stop("invalid nrow(x) or ncol(x)", domain = NA)
    sr <- rowSums(x)
    sc <- colSums(x)
    E <- outer(sr, sc, "*")/n
    v <- function(r, c, n) c * r * (n - r) * (n - c)/n^3
    V <- outer(sr, sc, v, n)
    dimnames(E) <- dimnames(x)
    if (simulate.p.value && all(sr > 0) && all(sc > 0)) {
      setMETH()
      tmp <- .Call(stats:::C_chisq_sim, sr, sc, B, E, PACKAGE="stats")
      STATISTIC <- 4*sum(sort((sqrt(x) - sqrt(E))^2, decreasing = TRUE))
      PARAMETER <- NA
      PVAL <- (1 + sum(tmp >= almost.1 * STATISTIC))/(B +
                                                        1)
    }
    else {
      # if (simulate.p.value)
      #   warning("cannot compute simulated p-value with zero marginals")
      # if (correct && nrow(x) == 2L && ncol(x) == 2L) {
      #   YATES <- min(0.5, abs(x - E))
      #   if (YATES > 0)
      #     METHOD <- paste(METHOD, "with Yates' continuity correction")
      # }
      # else YATES <- 0
      STATISTIC <-  4*sum((sqrt(x) - sqrt(E))^2)
      PARAMETER <- (nr - 1L) * (nc - 1L)
      PVAL <- pchisq(STATISTIC, PARAMETER, lower.tail = FALSE)
    }
  }
  else {
    if (length(dim(x)) > 2L)
      stop("invalid 'x'")
    if (length(x) == 1L)
      stop("'x' must at least have 2 elements")
    if (length(x) != length(p))
      stop("'x' and 'p' must have the same number of elements")
    if (any(p < 0))
      stop("probabilities must be non-negative.")
    if (abs(sum(p) - 1) > sqrt(.Machine$double.eps)) {
      if (rescale.p)
        p <- p/sum(p)
      else stop("probabilities must sum to 1.")
    }
    METHOD <- "Freeman-Tukey's test for given probabilities"
    E <- n * p
    V <- n * p * (1 - p)
    STATISTIC <-  4*sum((sqrt(x) - sqrt(E))^2)
    names(E) <- names(x)
    if (simulate.p.value) {
      setMETH()
      nx <- length(x)
      tt <- tryCatch(matrix(sample.int(nx, B * n, TRUE, prob = p),
                      nrow = n))
      sm <- matrix(sample.int(nx, B * n, TRUE, prob = p),
                   nrow = n)
      ss <- apply(sm, 2L, function(x, E, k) {
        4*sum((sqrt(table(factor(x, levels = 1L:k))) - sqrt(E))^2)
      }, E = E, k = nx)
      PARAMETER <- NA
      PVAL <- (1 + sum(ss >= almost.1 * STATISTIC))/(B + 1)
    }
    else {
      PARAMETER <- length(x) - 1
      PVAL <- pchisq(STATISTIC, PARAMETER, lower.tail = FALSE)
    }
  }
  names(STATISTIC) <- "X-squared"
  names(PARAMETER) <- "df"
  if (any(E < 5) && is.finite(PARAMETER))
    warning("Chi-squared approximation of Freeman-Tukey's Statistic may be incorrect")
  structure(list(statistic = STATISTIC, parameter = PARAMETER,
                 p.value = PVAL, method = METHOD, data.name = DNAME,
                 observed = x, expected = E, residuals = (x - E)/sqrt(E),
                 stdres = (x - E)/sqrt(V)), class = "htest")
}

APD <- function(exp_dist, obs_dist, laplace_smooth_param = NA){

  if(!is.na(laplace_smooth_param)){
    exp_dist = LaplaceSmoothing(exp_dist, laplace_smooth_param)
    obs_dist = LaplaceSmoothing(obs_dist, laplace_smooth_param)
  }

  apd = abs(exp_dist - obs_dist)/exp_dist * 100
  aapd = mean(apd)

  return(list(APD = apd, AAPD = aapd))
}
