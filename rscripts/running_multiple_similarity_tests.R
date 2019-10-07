#!/usr/bin/env Rscript

# for(i in c(1:20)){
#   cat("--------Processing instance",i,"---------------\n")
#   params = paste("--generateddata ../data/melbourne-2016/generated",i,"/SA2 ",
#                  "--output ../data/melbourne-2016/analysis",i,"/reduced-cats/",sep="")
#   print(params)
#   status = system(paste("Rscript similarity_tests_2016_with_reduced_cats.R",params), intern = F)
#   if(status != 0){
#     cat("###############Failed!##############\n")
#     quit(status=1)
#   }
# }

cat("COMBINING PVALS\n")

pvals = c()
for(i in c(1:20)){
  results = read.csv(
    paste(
      "../data/melbourne-2016/analysis",
      i,
      "/reduced-cats/persons-preprocessed-vs-generated-sae-reduced-cats.csv",
      sep = ""
    )
  )
  if(i == 1){
    saes = results[,c(1,2)]
  }else{
    saes=cbind(saes, results[,2])
  }
  
}

saes