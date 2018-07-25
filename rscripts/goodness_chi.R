options(width=200)
data_home="../data/melbourne-2016/generated/SA2"

sa2_list=c("Yarraville")
sa2_list=list.files(data_home)



for (i in 1:length(sa2_list)) {
  cleaned_data_csv = paste(data_home, "/", sa2_list[i], "/preprocessed/person_types.csv.gz", sep = "")
  synthetic_population_csv = paste(data_home, "/", sa2_list[i], "/population/output_person_types.csv.gz", sep = "")
  
  cleaned_dist = read.csv(cleaned_data_csv)
  synthetic_population_dist  = read.csv(synthetic_population_csv)
  synthetic_population_dist = as.data.frame(synthetic_population_dist$Persons)
  colnames(synthetic_population_dist)=c("Synthetic.count")
  
  df = cbind(cleaned_dist, synthetic_population_dist)
  df = df[-c(8,16,24,32,33:39,41:47,49:54,56:62,64,72,80,88,96,104,112),] # remove impossibles
  df = df[df$Persons.count>0,] # remove zero expected frequencies
  relationships = unique(df$Relationship.status)
  
  if (length(df$Persons.count)<1) next; # skip if nothing left

  if (i==1) {
    # counters for each relationship type
    diffs=as.data.frame(as.list(rep(0,length(relationships))))
    colnames(diffs)=as.vector(relationships); rownames(diffs)=c("Value")
    totalSA2s=0
  }
  
  totalSA2s<-totalSA2s+1
  
  for (relation in relationships) {
    dd = df[df$Relationship.status==relation,] # get the subset for this relationship
    cat("\n"); print(dd)

    # Calculate chi-square
    testname="chisq"
    chisq = sum((dd$Synthetic.count - dd$Persons.count)^2/dd$Persons.count)
    dof = length(dd$Persons.count)-1
    pvalue = pchisq(chisq, df=dof, lower.tail = FALSE)
    testval=chisq
    
    # Calculate Freeman-Tukey 
    #testname="ft"
    #ft <- 4*sum((sqrt(dd$Synthetic.count) - sqrt(dd$Persons.count))^2)
    #dof<-length(dd$Persons.count)-1
    #pvalue<-pchisq(ft,df=dof, lower.tail = FALSE)
    #testval=ft

    
    if (pvalue <= 0.05) diffs[relation] = diffs[relation]+1
    cat(sprintf("%s=%07.3f dof=%03d pval=%5.3f | %12s | %s\n", testname, testval, dof, pvalue, relation, sa2_list[i]))
  }
}
cat("\nNumber of SA2s out of",totalSA2s,"where synthetic is significantly different to census\n")
print(diffs)
diffsPercent = round(100*(diffs/totalSA2s),2)
cat("\nPercentage of SA2s out of",totalSA2s,"where synthetic is significantly different to census\n")
print(diffsPercent)
