library(Metrics)

source("util.R")
source("DataReadUltraShort.R")
source("util.R")
source("dwellingproperties.R")

lpersonIndRw=c(c(113:119),c(121:128))
lpersonHhRw=c(13,27,41,55,69,83,97,111)

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

indsacol = 1
relcol = 2
sexcol = 3
agecol = 4
indatacol = 5

hhsacol = 1
nopcol = 2
familytypecol =3
hhdatacol=4

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


cleanup <- function(indiv, hhold){
  
  indiv[,indatacol] <- as.numeric(indiv[,indatacol])
  hhold[,hhdatacol] <- as.numeric(hhold[,hhdatacol])
  
  cat("      Summary      \n")
  totalIndivsNeededByHhs =sum(hhold[,hhdatacol]*rep(seq(1,8), each = 14))
  totalExistingIndivs = sum(indiv[,indatacol])
  difference = totalIndivsNeededByHhs - totalExistingIndivs
  percentage = difference/ totalIndivsNeededByHhs*100
  cat("In households file:", totalIndivsNeededByHhs,"\n")
  cat("In individuals file:",totalExistingIndivs,"\n")
  cat("Difference:",difference," - ",percentage,"%\n\n")
  
  cat("Removed impossible values:\n")
  indPossibles = indiv[,indatacol]*IndPossibles
  indDetectedImpossibles = indiv[,indatacol] - indPossibles
  indImpossibleRwids = which(indDetectedImpossibles > 0)
  cat("Individuals:")
  if(length(indImpossibleRwids) > 0){
    print(indiv[indImpossibleRwids,-1])
  }else{
    cat("none\n")
  }
  
  hhPossibles = hhold[,hhdatacol]*HhPossibles
  hhDetectedImpossibles = hhold[,hhdatacol] - hhPossibles
  hhImpossibleRwids = which(hhDetectedImpossibles > 0)
  cat("Households:")
  if(length(hhImpossibleRwids)>0){
    cat("\n")
    print(hhold[hhImpossibleRwids,-1])
    cat("\n")
  }else{
    cat("none\n\n")
  }
  
  
  indiv[,indatacol] <- indiv[,indatacol]*IndPossibles
  hhold[,hhdatacol] <- hhold[,hhdatacol]*HhPossibles
  
  
  extra = 0
  
  cat("       Summary      \n")
  totalIndivsNeededByHhs =sum(hhold[,hhdatacol]*rep(seq(1,8), each = 14))
  totalExistingIndivs = sum(indiv[,indatacol])
  difference = totalIndivsNeededByHhs - totalExistingIndivs
  percentage = difference/ totalIndivsNeededByHhs*100
  cat("In households file:", totalIndivsNeededByHhs,"\n")
  cat("In individuals file:",totalExistingIndivs,"\n")
  cat("Difference:",difference," - ",percentage,"%\n\n")
  
  
  #Check grouphouseholds
  grpIndrwids = getMatchingRowIds(indiv, relcol, "GroupHhold {B}")
  ttlgrpin = sum(indiv[grpIndrwids,indatacol])
  cat("Group households: total persons in individuals file",ttlgrpin,"\n")
  grpHhsrwids = getMatchingRowIds(hhold,familytypecol,"Group household");
  ttlgrphh = sum(hhold[grpHhsrwids,hhdatacol]*seq(1,8))
  cat("Group households: total persons in households file",ttlgrphh,"\n")
  
  pdiff = ttlgrphh - ttlgrpin
  if(pdiff > 0){
    percent = pdiff/ttlgrpin*100
    indiv[grpIndrwids,indatacol] = fillAccording2Dist(indiv[grpIndrwids,indatacol], pdiff)
    cat("Group households: less persons than households, adding new agents:", pdiff,"(",percent,"%)\n")
  }else if(pdiff == 0){
    cat("Group households: No difference\n")
  }else{
    percent = pdiff/ttlgrpin*100
    cat("Group households: more persons than households, removing extra agents", pdiff,"(",percent,"%)\n")
    indiv[grpIndrwids,indatacol] = fillAccording2Dist(indiv[grpIndrwids,indatacol], pdiff)
    extra = extra + pdiff
  }
  
  #Check lone person households
  lnpersonrwids = getMatchingRowIds(indiv, relcol, "LonePerson {B}")
  ttllnpersons = sum(indiv[lnpersonrwids,indatacol])
  cat("Lone persons: total persons in individuals file",ttllnpersons,"\n")
  lnpersonhhsrwids = getMatchingRowIds(hhold, familytypecol,"Lone person household")
  ttllnpersonhhs = sum(hhold[lnpersonhhsrwids,hhdatacol])
  cat("Lone persons: total persons in household files",ttllnpersonhhs,"\n")
  pdiff = ttllnpersonhhs - ttllnpersons
  if(pdiff >0){
    percent = pdiff/ttllnpersons*100
    indiv[lnpersonrwids,indatacol]= fillAccording2Dist(indiv[lnpersonrwids,indatacol],pdiff)
    cat("Lone person: less persons than households, adding new agents:", pdiff,"(",percent,"%)\n")
  }else if(pdiff == 0){
    print("Lone person: No difference")
  }else{
    percent = pdiff/ttllnpersonhhs*100
    cat("Lone persons: more persons than households, removing extra agents:", pdiff,"(",percent,"%)\n")
    indiv[lnpersonrwids,indatacol]= fillAccording2Dist(indiv[lnpersonrwids,indatacol],pdiff)
    extra = extra + pdiff
  }
  
  #Match married males to married females
  marriedrwids = getMatchingRowIds(indiv, relcol, "Married {B}")
  marMalerwids = getMatchingRowIds(indiv[marriedrwids,], sexcol, "Male")
  marFemalerwids = getMatchingRowIds(indiv[marriedrwids,], sexcol,"Female")
  ttlMarriedMales = sum(indiv[marMalerwids,indatacol])
  cat("Married couples: total married males in individuals file:",ttlMarriedMales,"\n")
  ttlMarriedFemales = sum(indiv[marFemalerwids,indatacol])
  cat("Married couples: total married females in individuals file:",ttlMarriedFemales,"\n")
  
  f1cplonlyrwids = getMatchingRowIds(hhold, familytypecol, "One family household: Couple family with no children")
  f2cplonlyrwids =getMatchingRowIds(hhold, familytypecol, "Two family household: Couple family with no children")
  f3cplonlyrwids =getMatchingRowIds(hhold, familytypecol, "Three or more family household: Couple family with no children")
  
  f1cplYschildrwids = getMatchingRowIds(hhold, familytypecol, "One family household: Couple family with children")
  f2cplYschildrwids =getMatchingRowIds(hhold, familytypecol, "Two family household: Couple family with children")
  f3cplYschildrwids =getMatchingRowIds(hhold, familytypecol, "Three or more family household: Couple family with children")
  
  sumcplonly = sum(hhold[f1cplonlyrwids,hhdatacol],hhold[f2cplonlyrwids,hhdatacol],hhold[f3cplonlyrwids,hhdatacol])
  cat("Married couples: total couple families with no children in household file:",sumcplonly,"\n")
  sumcplyschld = sum(hhold[f1cplYschildrwids,hhdatacol],hhold[f2cplYschildrwids,hhdatacol],hhold[f3cplYschildrwids,hhdatacol])
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
      indiv[marFemalerwids,indatacol] = fillAccording2Dist(indiv[marFemalerwids,indatacol], pdiff)
      cat("Married Couples: not enough married females to form min required couples, increase married females by :", pdiff,"(",percent,"%)\n")
    }else if(pdiff ==0){
      cat("Married Couples: married females are equal to couple families\n")
    }else{
      cat("Married Couples: there are more married females than couple families, no problem")
    }
    ttlMarriedFemales =sum(indiv[marFemalerwids,indatacol])
    pdiff = ttlMarriedFemales - ttlMarriedMales
    if(pdiff > 0){
      percent =  pdiff/ttlMarriedMales*100
      cat("Married Couples: increase married males to match married females:",pdiff,"(",percent,"%)\n")  
      indiv[marMalerwids,indatacol] =  fillAccording2Dist(indiv[marMalerwids,indatacol], pdiff)
    }
  }else {
    cat("Married Couples: more married males than married females\n")
    pdiff = mincpls - ttlMarriedMales
    if(pdiff > 0){
      percent =  pdiff/ttlMarriedMales*100
      indiv[marMalerwids,indatacol] = fillAccording2Dist(indiv[marMalerwids,indatacol], pdiff)
      cat("Marred Couples: not enough married males to form min required couples, increase married males by :", pdiff,"(",percent,"%)\n")
    }else if(pdiff ==0){
      cat("Married Couples: married males are equal to couple families\n")
    }
    ttlMarriedMales =sum(indiv[marMalerwids,indatacol])
    pdiff = ttlMarriedMales -ttlMarriedFemales
    if(pdiff > 0){
      percent =  pdiff/ttlMarriedFemales*100
      cat("Married Couples: increase Married females to match married males by:",pdiff,"(",percent,"%)\n")  
      indiv[marFemalerwids,indatacol] =  fillAccording2Dist(indiv[marFemalerwids,indatacol], pdiff)
    }
  }
  
  #Check Lone Parents
  f1loneparentrwids = getMatchingRowIds(hhold, familytypecol,"One family household: One parent family")
  f2loneparentrwids = getMatchingRowIds(hhold, familytypecol,"Two family household: One parent family")
  f3loneparentrwids = getMatchingRowIds(hhold, familytypecol,"Three or more family household: One parent family")
  ttloneparentfamilies = sum(hhold[f1loneparentrwids,hhdatacol],hhold[f2loneparentrwids,hhdatacol],hhold[f3loneparentrwids,hhdatacol])
  cat("Lone Parents: minimum required number of lone parents in households file",ttloneparentfamilies,"\n")
  oneparentrwids = getMatchingRowIds(indiv, relcol, "Lone parent {B}")
  ttloneparents = sum(indiv[oneparentrwids,indatacol])
  cat("Lone Parents: lone parents in individuals file",ttloneparents,"\n")
  pdiff = ttloneparentfamilies - ttloneparents
  if(pdiff > 0){
    percent = pdiff/ttloneparents*100
    indiv[oneparentrwids,indatacol] = fillAccording2Dist(indiv[oneparentrwids,indatacol], pdiff)
    cat("Lone Parents: less lone parents than required by families, adding new agents",pdiff,"(",percent,"%)\n")
  }else{
    cat("Lone Parents: more lone parents in individuals file than Lone parent primary families in households file, no problem\n")
  }
  
  #Check children
  u15rwids = getMatchingRowIds(indiv, relcol, "U15Child {B}")
  sturwids = getMatchingRowIds(indiv, relcol, "Student {B}")
  o15rwids = getMatchingRowIds(indiv, relcol, "O15Child {B}")
  ttlchlds = sum(indiv[u15rwids,indatacol], indiv[sturwids,indatacol],indiv[o15rwids,indatacol])
  cat("Children: total children (U15 + Student + O15) in individuals file:",ttlchlds,"\n")
  
  ttlFamiliesWithChildren = ttloneparentfamilies+sumcplyschld
  cat("Children: total familes with children (One parent + Couple with children) in households file:",ttlFamiliesWithChildren,"\n")
  
  pdiff = ttlFamiliesWithChildren - ttlchlds
  if(pdiff > 0){
    percent = pdiff/ttlchlds*100
    indiv[c(u15rwids,sturwids,o15rwids),indatacol] = fillAccording2Dist(indiv[c(u15rwids,sturwids,o15rwids),indatacol], pdiff)
    cat("Children: less children than families, adding new agents:", pdiff,"(",percent,"%)\n")
  }else{
    cat("Children: enough children to construct all basic family structures with children, no problem\n")
  }
  
  #Relatives and Other family
  relrwids = getMatchingRowIds(indiv, relcol, "Relative {B}")
  ttlrelatives = sum(indiv[relrwids,indatacol])
  cat("Relatives for Other families: total relatives in individuals file:",ttlrelatives,"\n")
  f1otherfamilyrwids = getMatchingRowIds(hhold,familytypecol,"One family household: Other family")
  f2otherfamilyrwids = getMatchingRowIds(hhold,familytypecol,"Two family household: Other family")
  f3otherfamilyrwids = getMatchingRowIds(hhold,familytypecol,"Three or more family household: Other family")
  reltivsFor1FOtherfamily = sum(hhold[f1otherfamilyrwids,hhdatacol]*seq(1:8))
  cat("Relatives for Other families: total one family households(family type - other family):",sum(hhold[f1otherfamilyrwids,hhdatacol]),"\n")
  cat("Relatives for Other families: total relatives required to form all one family households(family type - other family):",reltivsFor1FOtherfamily,"\n")
  Otherfamily2fn3f = sum(hhold[f2otherfamilyrwids,hhdatacol], hhold[f3otherfamilyrwids,hhdatacol])
  cat("Relatives for Other families: total two and three family households (family type - Other family) in households file:",Otherfamily2fn3f,"\n")
  minRelativesFor2fn3f = Otherfamily2fn3f*2
  cat("Relatives for Other families: minimum relatives required for two and three family households (family type - Other family) in households file:",minRelativesFor2fn3f,"\n")
  hhrequiredreltives = minRelativesFor2fn3f + reltivsFor1FOtherfamily
  cat("Relatives for Other families: minimum required relatives to form all primary Other familes:",hhrequiredreltives,"\n")
  
  pdiff = hhrequiredreltives - ttlrelatives
  if(pdiff > 0){
    percent = pdiff/ttlrelatives*100
    indiv[relrwids,indatacol] = fillAccording2Dist(indiv[relrwids,indatacol], pdiff)
    cat("Relatives for Other families: less persons than households, adding new agents:", pdiff,"(",percent,"%)\n")
  }else{
    cat("Relatives for Other families: enough relatives to construch all basic other family structures\n")
  }
  
  cat("\n     Final Summary   \n")
  totalIndivsNeededByHhs =sum(hhold[,hhdatacol]*rep(seq(1,8), each = 14))
  totalExistingIndivs = sum(indiv[,indatacol])
  difference = totalIndivsNeededByHhs - totalExistingIndivs
  percentage = difference/ totalIndivsNeededByHhs*100
  cat("In households file:", totalIndivsNeededByHhs,"\n")
  cat("In individuals file:",totalExistingIndivs,"\n")
  cat("Difference (unrecongnised missing persons):",difference," - ",percentage,"%\n\n")


  #Extras
  extrasPersons = matrix(unlist(hhold),nrow = nrow(hhold), ncol(hhold))
  compulsory = c(2,3,2,2,2,3,2,2,2,3,2,2,1,2,
                 2,3,2,2,2,3,2,2,2,3,2,2,1,2,
                 2,3,2,2,2,3,2,2,2,3,2,2,1,2,
                 2,3,2,2,2,3,2,2,2,3,2,2,1,2,
                 2,3,2,2,2,3,2,2,2,3,2,2,1,2,
                 2,3,2,2,2,3,2,2,2,3,2,2,1,2,
                 2,3,2,2,2,3,2,2,2,3,2,2,1,2,
                 2,3,2,2,2,3,2,2,2,3,2,2,1,2)
  extras <- (rep(1:8, each=14 ) - (compulsory*HhPossibles))*hhold[,hhdatacol]
  return(list(indiv, hhold))
}

if(FALSE){
  dataHome = Sys.getenv("ANONDATA_HOME")
  hhinput = paste(dataHome,"/latch/raw/SA2, NPRD and HCFMD.csv",sep="")
  indinput = paste(dataHome,"/latch/raw/SA2, RLHP Relationship in Household, SEXP and AGE5P.csv",sep="")
  
  hhArr = readHouseholds(hhinput)
  indArr = readIndividuals(indinput)
  
  sa ="Ivanhoe"
  indv = readBySA(indArr,sa)
  hhs = readBySA(hhArr,sa)
  
  cleanup(indv,hhs)
}