library('lsa')

dataHome = Sys.getenv("ANONDATA_HOME")

dataHome = "/home/bhagya/repos/synthetic-population/data"
outputHome = "/home/bhagya/repos/synthetic-population/data/latch/analysis"
sa2s = c("Alphington - Fairfield","Northcote","Thornbury","Bundoora - East","Greensborough","Heidelberg - Rosanna","Heidelberg West","Ivanhoe","Ivanhoe East - Eaglemont","Montmorency - Briar Hill","Viewbank - Yallambie","Watsonia","Kingsbury","Preston","Reservoir - East","Reservoir - West")
algoversion="Bruteforce"
mu =2
alpha = 0.025

PerformSimilarityTests <- function(exp_dist_csv, obs_dist_csv){
  
  expected_df = read.csv(exp_dist_csv)
  expected = expected_df$V5
  
  observed_df <- read.csv(obs_dist_csv,header = T)
  observed <- observed_df$Persons
  
  grt = wilcox.test(x= expected, y = observed, paired = T, alternative = "greater", mu = -mu)
  les = wilcox.test(x= expected, y = observed, paired = T, alternative = "less", mu = mu)
  
  pval = max(c(grt$p.value,les$p.value))
  
  # Do Cosine similarity right here
  cossim = cosine(x=expected, y=observed)
  return(list("p-value"=pval, "alt accept"= (pval < (alpha*2)), "cosine similarity"=cossim))
  
}

cat("Agent distribution of ABS raw input vs. synthetic population\n")

out <- matrix(0,nrow = length(sa2s), ncol = 3)

for(i in 1:length(sa2s)){
  abs_raw_csv = paste(dataHome,"/latch/absprocessed/SA2/",sa2s[i],"/input/absIndiv.csv",sep="")
  synthetic_population_csv = paste(dataHome,"/latch/grouped/SA2/",algoversion,"/",sa2s[i],"/AgentSummary.csv",sep="")
  res = PerformSimilarityTests(abs_raw_csv, synthetic_population_csv)
  out[i,] <- unlist(res)
}

rownames(out) <- sa2s

wilcoxon_test_result = out[,c(1:2)]
colnames(wilcoxon_test_result) <-c(paste("p-value\nmu=",mu), paste("Equivalent\n","alpha=",alpha,"\nconfidence=",(100-(alpha*2*100)),"%",sep=""))
outfile=paste(outputHome, "/abs-raw-vs-generated-tost-wilcoxon.csv",sep="")
write.csv(wilcoxon_test_result,file=outfile)
print("TOST with Wilcoxon Signed Rank Test")
print(wilcoxon_test_result)


cosin_similarity_result <- out[,3,drop=F]
colnames(cosin_similarity_result) <-c("Cosine similarity")
print("Cosine similarity test")
print(cosin_similarity_result)
outfile=paste(outputHome, "/abs-raw-vs-generated-cosine-similarity.csv",sep="")
write.csv(cosin_similarity_result,file=outfile)


cat("Agent distribution of cleaned data vs. synthetic population\n")
for(i in 1:length(sa2s)){
  cleaned_data_csv = paste(dataHome,"/latch/absprocessed/SA2/",sa2s[i],"/Indiv.csv",sep="")
  synthetic_population_csv = paste(dataHome,"/latch/grouped/SA2/",algoversion,"/",sa2s[i],"/AgentSummary.csv",sep="")
  res = PerformSimilarityTests(cleaned_data_csv, synthetic_population_csv)
  out[i,] <- unlist(res)
}

wilcoxon_test_result <- out[,c(1:2)]
colnames(wilcoxon_test_result) <- c(paste("p-value\nmu=",mu), paste("Equivalent\n","alpha=",alpha,"\nconfidence=",(100-(alpha*2*100)),"%",sep=""))
print("TOST with Wilcoxon Signed Rank Test")
print(wilcoxon_test_result)
outfile=paste(outputHome, "/cleaned-vs-generated-tost-wilcoxon.csv",sep="")
write.csv(wilcoxon_test_result,file=outfile)

cosin_similarity_result <- out[,3,drop=F]
colnames(cosin_similarity_result) <-c("Cosine similarity")
print("Cosine similarity test")
print(cosin_similarity_result)
outfile=paste(outputHome, "/cleaned-vs-generated-cosine-similarity.csv",sep="")
write.csv(cosin_similarity_result,file=outfile)


cat("Output saved to:",outputHome,"\n")
cat("Done!\n")

