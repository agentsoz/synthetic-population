options(width=200)
data_home="../data/melbourne-2016/generated/SA2"

#sa2_list=c("Yarraville")
sa2_list=list.files(data_home)

chiGoodness <- function(sa2_list) {
  diffs=0
  totalSA2s=0
  for (i in 1:length(sa2_list)) {
    cleaned_data_csv = paste(data_home, "/", sa2_list[i], "/preprocessed/person_types.csv.gz", sep = "")
    synthetic_population_csv = paste(data_home, "/", sa2_list[i], "/population/output_person_types.csv.gz", sep = "")
    
    cleaned_dist = read.csv(cleaned_data_csv)
    synthetic_population_dist  = read.csv(synthetic_population_csv)
    synthetic_population_dist = as.data.frame(synthetic_population_dist$Persons)
    colnames(synthetic_population_dist)=c("Synthetic.count")
    
    dd = cbind(cleaned_dist, synthetic_population_dist)
      dd$Persons.count = (dd$Persons.count/sum(dd$Persons.count)) * sum(dd$Synthetic.count) # match counts
      dd = dd[dd$Persons.count>5,] # remove low cats after reweighting counts
      if (length(dd$Persons.count)<1) next; # skip if nothing left
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
      
      
      if (pvalue <= 0.05) diffs = diffs+1
      cat(sprintf("%s=%07.3f dof=%03d pval=%5.3f | %s\n", testname, testval, dof, pvalue, sa2_list[i]))

    totalSA2s<-totalSA2s+1
    
    
  }
  cat("\nNumber of SA2s out of",totalSA2s,"where synthetic is significantly different to census\n")
  print(diffs)
  diffsPercent = round(100*(diffs/totalSA2s),2)
  cat("\nPercentage of SA2s out of",totalSA2s,"where synthetic is significantly different to census\n")
  print(diffsPercent)
}


chiGoodnessPerRelationshipType <- function(sa2_list) {
for (i in 1:length(sa2_list)) {
  cleaned_data_csv = paste(data_home, "/", sa2_list[i], "/preprocessed/person_types.csv.gz", sep = "")
  synthetic_population_csv = paste(data_home, "/", sa2_list[i], "/population/output_person_types.csv.gz", sep = "")
  
  cleaned_dist = read.csv(cleaned_data_csv)
  synthetic_population_dist  = read.csv(synthetic_population_csv)
  synthetic_population_dist = as.data.frame(synthetic_population_dist$Persons)
  colnames(synthetic_population_dist)=c("Synthetic.count")
  
  df = cbind(cleaned_dist, synthetic_population_dist)
  df = df[df$Persons.count>0,] # remove zero expected frequencies
  if (length(df$Persons.count)<1) next; # skip if nothing left

  relationships = unique(df$Relationship.status)
  
  if (i==1) {
    # counters for each relationship type
    diffs=as.data.frame(as.list(rep(0,length(relationships))))
    colnames(diffs)=as.vector(relationships); rownames(diffs)=c("Value")
    totalSA2s=0
  }
  
  for (relation in relationships) {
    dd = df[df$Relationship.status==relation,] # get the subset for this relationship
    dd$Persons.count = (dd$Persons.count/sum(dd$Persons.count)) * sum(dd$Synthetic.count) # match counts
    dd = dd[dd$Persons.count>5,] # remove low cats after reweighting counts
    if (length(dd$Persons.count)<1) next; # skip if nothing left
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
  
  totalSA2s<-totalSA2s+1
  
  
}
cat("\nNumber of SA2s out of",totalSA2s,"where synthetic is significantly different to census\n")
print(diffs)
diffsPercent = round(100*(diffs/totalSA2s),2)
cat("\nPercentage of SA2s out of",totalSA2s,"where synthetic is significantly different to census\n")
print(diffsPercent)
}

cat('\n\n### Chi-squared goodness per relationship category\n\n')
chiGoodnessPerRelationshipType(sa2_list)
cat('\n\n### Chi-squared goodness across full set of categories\n\n')
chiGoodness(sa2_list)
