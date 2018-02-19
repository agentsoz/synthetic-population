library('lsa')

dataHome = Sys.getenv("ANONDATA_HOME")

dataHome = "/home/niroshan/repos/synthetic-population/data"
outputHome = "/home/niroshan/repos/synthetic-population/data/latch/analysis"
sa2s = c("Alphington - Fairfield","Northcote","Thornbury","Bundoora - East","Greensborough","Heidelberg - Rosanna","Heidelberg West","Ivanhoe","Ivanhoe East - Eaglemont","Montmorency - Briar Hill","Viewbank - Yallambie","Watsonia","Kingsbury","Preston","Reservoir - East","Reservoir - West")
algoversion="Bruteforce"
mu =2
alpha = 0.025

PerformSimilarityTests <- function(exp_dist, obs_dist){
  
  grt = wilcox.test(x= exp_dist, y = obs_dist, paired = T, alternative = "greater", mu = -mu)
  les = wilcox.test(x= exp_dist, y = obs_dist, paired = T, alternative = "less", mu = mu)
  
  pval = max(c(grt$p.value,les$p.value))
  
  # Do Cosine similarity right here
  cossim = cosine(x=exp_dist, y=obs_dist)
  return(list("p-value"=pval, "alt accept"= (pval < (alpha)), "cosine similarity"=cossim))
  
}

cat("Agent distribution of ABS raw input vs. synthetic population\n")

out <- matrix(0,nrow = length(sa2s), ncol = 3)
totals <- matrix(0,nrow = length(sa2s), ncol = 4)
rownames(totals) <- sa2s
colnames(totals) <- c("Total persons in cleaned Hhs dist","Total persons in cleaned Indiv dist", "Total persons in synthesised Hhs dist","Total persons in synthesised Indiv dist")

for(i in 1:length(sa2s)){
  abs_raw_csv = paste(dataHome,"/latch/absprocessed/SA2/",sa2s[i],"/input/absIndiv.csv",sep="")
  abs_raw_dist = read.csv(abs_raw_csv)$V5
  synthetic_population_csv = paste(dataHome,"/latch/grouped/SA2/",algoversion,"/",sa2s[i],"/AgentSummary.csv",sep="")
  synthetic_population_dist = read.csv(synthetic_population_csv)$Persons
  res = PerformSimilarityTests(abs_raw_dist, synthetic_population_dist)
  out[i,] <- unlist(res)
}

rownames(out) <- sa2s

wilcoxon_test_result = out[,c(1:2)]
colnames(wilcoxon_test_result) <-c(paste("p-value\nmu=",mu), paste("Equivalent\n","alpha=",alpha,"\nconfidence=",(100-(alpha*2*100)),"%",sep=""))
outfile=paste(outputHome, "/indiv-abs-raw-vs-generated-tost-wilcoxon.csv",sep="")
write.csv(wilcoxon_test_result,file=outfile)
print("TOST with Wilcoxon Signed Rank Test")
print(wilcoxon_test_result)

cosin_similarity_result <- out[,3,drop=F]
colnames(cosin_similarity_result) <-c("Cosine similarity")
print("Cosine similarity test")
print(cosin_similarity_result)
outfile=paste(outputHome, "/indiv-abs-raw-vs-generated-cosine-similarity.csv",sep="")
write.csv(cosin_similarity_result,file=outfile)

cat("Agent distribution of cleaned data vs. synthetic population\n")
for(i in 1:length(sa2s)){
  cleaned_data_csv = paste(dataHome,"/latch/absprocessed/SA2/",sa2s[i],"/Indiv.csv",sep="")
  cleaned_dist = read.csv(cleaned_data_csv)$V5
  synthetic_population_csv = paste(dataHome,"/latch/grouped/SA2/",algoversion,"/",sa2s[i],"/AgentSummary.csv",sep="")
  synthetic_population_dist  = read.csv(synthetic_population_csv)$Persons
  res = PerformSimilarityTests(cleaned_dist, synthetic_population_dist)
  out[i,] <- unlist(res)
  totals[i,2] <- sum(cleaned_dist)
  totals[i,4] <- sum(synthetic_population_dist)
}

wilcoxon_test_result <- out[,c(1:2)]
colnames(wilcoxon_test_result) <- c(paste("p-value\nmu=",mu), paste("Equivalent\n","alpha=",alpha,"\nconfidence=",(100-(alpha*2*100)),"%",sep=""))
print("TOST with Wilcoxon Signed Rank Test")
print(wilcoxon_test_result)
outfile=paste(outputHome, "/indiv-cleaned-vs-generated-tost-wilcoxon.csv",sep="")
write.csv(wilcoxon_test_result,file=outfile)

cosin_similarity_result <- out[,3,drop=F]
colnames(cosin_similarity_result) <-c("Cosine similarity")
print("Cosine similarity test")
print(cosin_similarity_result)
outfile=paste(outputHome, "/indiv-cleaned-vs-generated-cosine-similarity.csv",sep="")
write.csv(cosin_similarity_result,file=outfile)

cat("Household distribution of cleaned data vs. synthetic population\n")
for(i in 1:length(sa2s)){
  cleaned_data_csv = paste(dataHome,"/latch/absprocessed/SA2/",sa2s[i],"/Hh.csv",sep="")
  cleaned_dist = read.csv(cleaned_data_csv)$V4
  synthetic_population_csv = paste(dataHome,"/latch/grouped/SA2/",algoversion,"/",sa2s[i],"/GroupSummary.csv",sep="")
  synthetic_population_dist  = read.csv(synthetic_population_csv)$NofHouseholds
  res = PerformSimilarityTests(cleaned_dist, synthetic_population_dist)
  out[i,] <- unlist(res)
  totals[i,1] <- sum(cleaned_dist*rep(seq(1,8), each = 14))
  totals[i,3] <- sum(synthetic_population_dist*rep(seq(1,8), each = 14))
}

wilcoxon_test_result <- out[,c(1:2)]
colnames(wilcoxon_test_result) <- c(paste("p-value\nmu=",mu), paste("Equivalent\n","alpha=",alpha,"\nconfidence=",(100-(alpha*2*100)),"%",sep=""))
print("TOST with Wilcoxon Signed Rank Test - Households")
print(wilcoxon_test_result)
outfile=paste(outputHome, "/hh-cleaned-vs-generated-tost-wilcoxon.csv",sep="")
write.csv(wilcoxon_test_result,file=outfile)

cosin_similarity_result <- out[,3,drop=F]
colnames(cosin_similarity_result) <-c("Cosine similarity")
print("Cosine similarity test - Households")
print(cosin_similarity_result)
outfile=paste(outputHome, "/hh-cleaned-vs-generated-cosine-similarity.csv",sep="")
write.csv(cosin_similarity_result,file=outfile)

totals_file <- paste(outputHome, "/hh-indiv-totals.csv",sep="")
write.csv(totals, file=totals_file)
print("Comparing ")


cat("Output saved to:",outputHome,"\n")
cat("Done!\n")

