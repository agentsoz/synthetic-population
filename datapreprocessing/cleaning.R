library(Metrics)

source("util.R")
source("DataReadUltraShort.R")
source("util.R")
source("dwellingproperties.R")

IndPossibles = c(1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0, #married
                 1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0, #lone parent
                 0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1, #U15child
                 0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0, #student
                 1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0, #O15child
                 1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0, #GrpHh
                 1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0, #Lone person
                 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1) #Relative
HhPossibles = c(0,0,0,0,0,0,0,0,0,0,0,0,1,0,
                1,0,1,1,0,0,0,0,0,0,0,0,0,1,
                1,1,1,1,0,0,0,0,0,0,0,0,0,1,
                1,1,1,1,1,0,1,1,0,0,0,0,0,1,
                1,1,1,1,1,1,1,1,0,0,0,0,0,1,
                1,1,1,1,1,1,1,1,1,0,1,1,0,1,
                1,1,1,1,1,1,1,1,1,1,1,1,0,1,
                1,1,1,1,1,1,1,1,1,1,1,1,0,1)

fillAccording2Dist<- function(dataarray, amount){
  dist = dataarray/sum(dataarray)
  newAddition = dist*amount
  if((round(sum(newAddition)) - sum(floor(newAddition))) != 0){
    newAddition = smart.round(newAddition)
  }
  dataarray = dataarray + newAddition
  return(dataarray)
}
smart.round <- function(x) {
  y <- floor(x)
  indices <- tail(order(x-y), round(sum(x)) - sum(y))
  y[indices] <- y[indices] + 1
  return(y)
}


cleanup <- function(person, pSAColi, pRelColi, pSexColi, pAgeColi,pValColi, hhold, hPersonCountColi, hFamilyTypeColi, hValColi){
  
  person[,pValColi] <- as.numeric(person[,pValColi])
  hhold[,hValColi] <- as.numeric(hhold[,hValColi])
  
  cat("      Summary      \n")
  totalpersonsNeededByHhs =sum(hhold[,hValColi]*rep(seq(1,8), each = 14))
  totalExistingpersons = sum(person[,pValColi])
  difference = totalpersonsNeededByHhs - totalExistingpersons
  percentage = difference/ totalpersonsNeededByHhs*100
  cat("In households file:", totalpersonsNeededByHhs,"\n")
  cat("In persons file:",totalExistingpersons,"\n")
  cat("Difference:",difference," - ",percentage,"%\n\n")
  
  cat("Removed impossible values:\n")
  indPossibles = person[,pValColi]*IndPossibles
  indDetectedImpossibles = person[,pValColi] - indPossibles
  indImpossibleRwids = which(indDetectedImpossibles > 0)
  cat("persons:")
  if(length(indImpossibleRwids) > 0){
    print(person[indImpossibleRwids,-1])
  }else{
    cat("none\n")
  }
  
  hhPossibles = hhold[,hValColi]*HhPossibles
  hhDetectedImpossibles = hhold[,hValColi] - hhPossibles
  hhImpossibleRwids = which(hhDetectedImpossibles > 0)
  cat("Households:")
  if(length(hhImpossibleRwids)>0){
    cat("\n")
    print(hhold[hhImpossibleRwids,-1])
    cat("\n")
  }else{
    cat("none\n\n")
  }
  
  
  person[,pValColi] <- person[,pValColi]*IndPossibles
  hhold[,hValColi] <- hhold[,hValColi]*HhPossibles
  
  
  extra = 0
  
  cat("       Summary      \n")
  totalpersonsNeededByHhs =sum(hhold[,hValColi]*rep(seq(1,8), each = 14))
  totalExistingpersons = sum(person[,pValColi])
  difference = totalpersonsNeededByHhs - totalExistingpersons
  percentage = difference/ totalpersonsNeededByHhs*100
  cat("In households file:", totalpersonsNeededByHhs,"\n")
  cat("In persons file:",totalExistingpersons,"\n")
  cat("Difference:",difference," - ",percentage,"%\n\n")
  
  
  #Check grouphouseholds
  grpIndrwids = getMatchingRowIds(person, pRelColi, "GroupHhold")
  ttlgrpin = sum(person[grpIndrwids,pValColi])
  cat("Group households: total persons in personiduals file",ttlgrpin,"\n")
  grpHhsrwids = getMatchingRowIds(hhold,hFamilyTypeColi,"Group household");
  ttlgrphh = sum(hhold[grpHhsrwids,hValColi]*seq(1,8))
  cat("Group households: total persons in households file",ttlgrphh,"\n")
  
  pdiff = ttlgrphh - ttlgrpin
  if(pdiff > 0){
    percent = pdiff/ttlgrpin*100
    person[grpIndrwids,pValColi] = fillAccording2Dist(person[grpIndrwids,pValColi], pdiff)
    cat("Group households: less persons than households, adding new agents:", pdiff,"(",percent,"%)\n")
  }else if(pdiff == 0){
    cat("Group households: No difference\n")
  }else{
    percent = pdiff/ttlgrpin*100
    cat("Group households: more persons than households, removing extra agents", pdiff,"(",percent,"%)\n")
    person[grpIndrwids,pValColi] = fillAccording2Dist(person[grpIndrwids,pValColi], pdiff)
    extra = extra + pdiff
  }
  
  #Check lone person households
  lnpersonrwids = getMatchingRowIds(person, pRelColi, "Lone person")
  ttllnpersons = sum(person[lnpersonrwids,pValColi])
  cat("Lone persons: total persons in persons file",ttllnpersons,"\n")
  lnpersonhhsrwids = getMatchingRowIds(hhold, hFamilyTypeColi,"Lone person household")
  ttllnpersonhhs = sum(hhold[lnpersonhhsrwids,hValColi])
  cat("Lone persons: total persons in household files",ttllnpersonhhs,"\n")
  pdiff = ttllnpersonhhs - ttllnpersons
  if(pdiff >0){
    percent = pdiff/ttllnpersons*100
    person[lnpersonrwids,pValColi]= fillAccording2Dist(person[lnpersonrwids,pValColi],pdiff)
    cat("Lone person: less persons than households, adding new agents:", pdiff,"(",percent,"%)\n")
  }else if(pdiff == 0){
    print("Lone person: No difference")
  }else{
    percent = pdiff/ttllnpersonhhs*100
    cat("Lone persons: more persons than households, removing extra agents:", pdiff,"(",percent,"%)\n")
    person[lnpersonrwids,pValColi]= fillAccording2Dist(person[lnpersonrwids,pValColi],pdiff)
    extra = extra + pdiff
  }
  
  #Match married males to married females
  marriedrwids = getMatchingRowIds(person, pRelColi, "Married")
  marMalerwids = getMatchingRowIds(person[marriedrwids,], pSexColi, "Male")
  marFemalerwids = getMatchingRowIds(person[marriedrwids,], pSexColi,"Female")
  ttlMarriedMales = sum(person[marMalerwids,pValColi])
  cat("Married couples: total married males in persons file:",ttlMarriedMales,"\n")
  ttlMarriedFemales = sum(person[marFemalerwids,pValColi])
  cat("Married couples: total married females in persons file:",ttlMarriedFemales,"\n")
  
  f1cplonlyrwids = getMatchingRowIds(hhold, hFamilyTypeColi, "One family household: Couple family with no children")
  f2cplonlyrwids =getMatchingRowIds(hhold, hFamilyTypeColi, "Two family household: Couple family with no children")
  f3cplonlyrwids =getMatchingRowIds(hhold, hFamilyTypeColi, "Three or more family household: Couple family with no children")
  
  f1cplYschildrwids = getMatchingRowIds(hhold, hFamilyTypeColi, "One family household: Couple family with children")
  f2cplYschildrwids =getMatchingRowIds(hhold, hFamilyTypeColi, "Two family household: Couple family with children")
  f3cplYschildrwids =getMatchingRowIds(hhold, hFamilyTypeColi, "Three or more family household: Couple family with children")
  
  sumcplonly = sum(hhold[f1cplonlyrwids,hValColi],hhold[f2cplonlyrwids,hValColi],hhold[f3cplonlyrwids,hValColi])
  cat("Married couples: total couple families with no children in household file:",sumcplonly,"\n")
  sumcplyschld = sum(hhold[f1cplYschildrwids,hValColi],hhold[f2cplYschildrwids,hValColi],hhold[f3cplYschildrwids,hValColi])
  cat("Married couples: total couple families with children in household file:",sumcplyschld,"\n")
  mincpls = sum(sumcplyschld,sumcplonly)
  cat("Married couples: minimum required number of couples:",mincpls,"\n")
  
  if(ttlMarriedFemales >= ttlMarriedMales){
    if(ttlMarriedFemales > ttlMarriedMales){
      cat("Married Couples: more married females than married males\n")
    }else{
      cat("Married Couples: married females equal to married males\n")
    }
    pdiff = mincpls - ttlMarriedFemales
    if(pdiff > 0){
      percent = pdiff/ttlMarriedFemales *100
      person[marFemalerwids,pValColi] = fillAccording2Dist(person[marFemalerwids,pValColi], pdiff)
      cat("Married Couples: there are not enough married females to form min required couples, increase married females by :", pdiff,"(",percent,"%)\n")
    }else if(pdiff ==0){
      cat("Married Couples: married females are equal to couple families\n")
    }else{
      cat("Married Couples: there are more married females than couple families, no problem\n")
    }
    ttlMarriedFemales =sum(person[marFemalerwids,pValColi])
    pdiff = ttlMarriedFemales - ttlMarriedMales
    if(pdiff > 0){
      percent =  pdiff/ttlMarriedMales*100
      cat("Married Couples: increase married males to match married females:",pdiff,"(",percent,"%)\n")  
      person[marMalerwids,pValColi] =  fillAccording2Dist(person[marMalerwids,pValColi], pdiff)
    }
  }else {
    cat("Married Couples: more married males than married females\n")
    pdiff = mincpls - ttlMarriedMales
    if(pdiff > 0){
      percent =  pdiff/ttlMarriedMales*100
      person[marMalerwids,pValColi] = fillAccording2Dist(person[marMalerwids,pValColi], pdiff)
      cat("Marred Couples: there are not there are enough married males to form min required couples, increase married males by :", pdiff,"(",percent,"%)\n")
    }else if(pdiff ==0){
      cat("Married Couples: married males are equal to couple families\n")
    }
    ttlMarriedMales =sum(person[marMalerwids,pValColi])
    pdiff = ttlMarriedMales -ttlMarriedFemales
    if(pdiff > 0){
      percent =  pdiff/ttlMarriedFemales*100
      cat("Married Couples: increase Married females to match married males by:",pdiff,"(",percent,"%)\n")  
      person[marFemalerwids,pValColi] =  fillAccording2Dist(person[marFemalerwids,pValColi], pdiff)
    }
  }
  
  #Check Lone Parents
  f1loneparentrwids = getMatchingRowIds(hhold, hFamilyTypeColi,"One family household: One parent family")
  f2loneparentrwids = getMatchingRowIds(hhold, hFamilyTypeColi,"Two family household: One parent family")
  f3loneparentrwids = getMatchingRowIds(hhold, hFamilyTypeColi,"Three or more family household: One parent family")
  ttloneparentfamilies = sum(hhold[f1loneparentrwids,hValColi],hhold[f2loneparentrwids,hValColi],hhold[f3loneparentrwids,hValColi])
  cat("Lone Parents: minimum required number of lone parents in households file",ttloneparentfamilies,"\n")
  oneparentrwids = getMatchingRowIds(person, pRelColi, "Lone parent")
  ttloneparents = sum(person[oneparentrwids,pValColi])
  cat("Lone Parents: lone parents in persons file",ttloneparents,"\n")
  pdiff = ttloneparentfamilies - ttloneparents
  if(pdiff > 0){
    percent = pdiff/ttloneparents*100
    person[oneparentrwids,pValColi] = fillAccording2Dist(person[oneparentrwids,pValColi], pdiff)
    cat("Lone Parents: less lone parents than required by families, adding new agents",pdiff,"(",percent,"%)\n")
  }else{
    cat("Lone Parents: more lone parents in persons file than Lone parent primary families in households file, no problem\n")
  }
  
  #Check children
  u15rwids = getMatchingRowIds(person, pRelColi, "U15Child")
  sturwids = getMatchingRowIds(person, pRelColi, "Student")
  o15rwids = getMatchingRowIds(person, pRelColi, "O15Child")
  ttlchlds = sum(person[u15rwids,pValColi], person[sturwids,pValColi],person[o15rwids,pValColi])
  cat("Children: total children (U15 + Student + O15) in persons file:",ttlchlds,"\n")
  
  ttlFamiliesWithChildren = ttloneparentfamilies+sumcplyschld
  cat("Children: total familes with children (One parent + Couple with children) in households file:",ttlFamiliesWithChildren,"\n")
  
  pdiff = ttlFamiliesWithChildren - ttlchlds
  if(pdiff > 0){
    percent = pdiff/ttlchlds*100
    person[c(u15rwids,sturwids,o15rwids),pValColi] = fillAccording2Dist(person[c(u15rwids,sturwids,o15rwids),pValColi], pdiff)
    cat("Children: less children than families, adding new agents:", pdiff,"(",percent,"%)\n")
  }else{
    cat("Children: there are enough children to construct all known basic family structures with children, no problem\n")
  }
  
  #Relatives and Other family
  relrwids = getMatchingRowIds(person, pRelColi, "Relatives")
  ttlrelatives = sum(person[relrwids,pValColi])
  cat("Relatives for Other families: total relatives in persons file:",ttlrelatives,"\n")
  f1otherfamilyrwids = getMatchingRowIds(hhold,hFamilyTypeColi,"One family household: Other family")
  f2otherfamilyrwids = getMatchingRowIds(hhold,hFamilyTypeColi,"Two family household: Other family")
  f3otherfamilyrwids = getMatchingRowIds(hhold,hFamilyTypeColi,"Three or more family household: Other family")
  reltivsFor1FOtherfamily = sum(hhold[f1otherfamilyrwids,hValColi]*seq(1:8))
  cat("Relatives for Other families: total one family households(family type - other family):",sum(hhold[f1otherfamilyrwids,hValColi]),"\n")
  cat("Relatives for Other families: total relatives required to form all one family households(family type - other family):",reltivsFor1FOtherfamily,"\n")
  Otherfamily2fn3f = sum(hhold[f2otherfamilyrwids,hValColi], hhold[f3otherfamilyrwids,hValColi])
  cat("Relatives for Other families: total two and three family households (family type - Other family) in households file:",Otherfamily2fn3f,"\n")
  minRelativesFor2fn3f = Otherfamily2fn3f*2
  cat("Relatives for Other families: minimum relatives required for two and three family households (family type - Other family) in households file:",minRelativesFor2fn3f,"\n")
  hhrequiredreltives = minRelativesFor2fn3f + reltivsFor1FOtherfamily
  cat("Relatives for Other families: minimum required relatives to form all primary Other familes:",hhrequiredreltives,"\n")
  
  pdiff = hhrequiredreltives - ttlrelatives
  if(pdiff > 0){
    percent = pdiff/ttlrelatives*100
    person[relrwids,pValColi] = fillAccording2Dist(person[relrwids,pValColi], pdiff)
    cat("Relatives for Other families: less persons than households, adding new agents:", pdiff,"(",percent,"%)\n")
  }else{
    cat("Relatives for Other families: there are enough relatives to construch all basic other family structures\n")
  }
  
  cat("\n     Final Summary   \n")
  totalpersonsNeededByHhs =sum(hhold[,hValColi]*rep(seq(1,8), each = 14))
  totalExistingpersons = sum(person[,pValColi])
  difference = totalpersonsNeededByHhs - totalExistingpersons
  percentage = difference/ totalpersonsNeededByHhs*100
  cat("In households file:", totalpersonsNeededByHhs,"\n")
  cat("In persons file:",totalExistingpersons,"\n")
  cat("Difference (unrecongnised missing persons):",difference," (",percentage,"%)\n\n")


  return(list(person, hhold))
}
