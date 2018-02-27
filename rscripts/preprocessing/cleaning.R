library(Metrics)

IndPossibles = list()
for (rel in rel_status_cats) {
  IndPossibles[[rel]] = list()
  for (sex in sex_cats) {
    IndPossibles[[rel]][[sex]] = list()
    binaries = switch(
      which(rel_status_cats == rel),
      married_age,
      loneparent_age,
      u15child_age,
      student_age,
      o15_age,
      grouphh_age,
      loneperson_age,
      relatives_age
    )
    for (i in 1:length(age_cats)) {
      IndPossibles[[rel]][[sex]][[age_cats[i]]] = binaries[i]
    }
  }
}

hh_possibles = list()
for (size in hh_sizes) {
  hh_possibles[[size]] = list()
  binaries = switch(
    which(hh_sizes == size),
    one_person,
    two_persons,
    three_persons,
    four_persons,
    five_persons,
    six_persons,
    seven_persons,
    eight_persons
  )
  for (i in 1:length(binaries)) {
    hh_possibles[[size]][[family_hh_cats[i]]] = binaries[i]
  }
}

FillAccording2Dist <- function(dataarray, amount) {
  if (sum(dataarray) == 0) {
    #if all data array cells are 0, we cannot approximate a distribution. So we assign the amount to random cells assuming a uniform distribution.
    idx = ceiling(runif(amount, 0, length(dataarray)))
    for (i in idx) {
      dataarray[i] <- dataarray[i] + 1
    }
  } else{
    dist = dataarray / sum(dataarray)
    newAddition = dist * amount
    if ((round(sum(newAddition)) - sum(floor(newAddition))) != 0) {
      newAddition = SmartRound(newAddition)
    }
    dataarray = dataarray + newAddition
  }
  return(dataarray)
}

SmartRound <- function(x) {
  y <- floor(x)
  indices <- tail(order(x - y), round(sum(x)) - sum(y))
  y[indices] <- y[indices] + 1
  return(y)
}

clean <-
  function(person,
           pSAColi,
           pRelColi,
           pSexColi,
           pAgeColi,
           pValColi,
           hhold,
           hPersonCountColi,
           hFamilyTypeColi,
           hValColi) {
    person[, pValColi] <- as.numeric(person[, pValColi])
    hhold[, hValColi] <- as.numeric(hhold[, hValColi])
    
    cat("      Summary      \n")
    totalpersonsNeededByHhs = sum(hhold[, hValColi] * rep(seq(1, 8), each = 14))
    totalExistingpersons = sum(person[, pValColi])
    difference = totalpersonsNeededByHhs - totalExistingpersons
    percentage = difference / totalpersonsNeededByHhs * 100
    cat("In households file:", totalpersonsNeededByHhs, "\n")
    cat("In persons file:", totalExistingpersons, "\n")
    cat("Difference:", difference, " - ", percentage, "%\n\n")
    starting_error = percentage
    
    cat("Removed impossible values:\n")
    cat("persons:\n")
    hasImpossibles = FALSE
    for (i in 1:nrow(person)) {
      rel = person[i, pRelColi]
      sex = person[i, pSexColi]
      age = person[i, pAgeColi]
      val = person[i, pValColi]
      person[i, pValColi] = person[i, pValColi] * IndPossibles[[rel]][[sex]][[age]]
      if (val != person[i, pValColi]) {
        print(person[i, ])
        hasImpossibles = TRUE
      }
    }
    if (!hasImpossibles) {
      cat("none\n\n")
    }
    
    cat("Households:\n")
    for (i in 1:nrow(hhold)) {
      count = hhold[i, hPersonCountColi]
      familyType = hhold[i, hFamilyTypeColi]
      val = hhold[i, hValColi]
      hhold[i, hValColi] = hhold[i, hValColi] * hh_possibles[[count]][[familyType]]
      if (val != hhold[i, hValColi]) {
        print(hhold[i, ])
        hasImpossibles = TRUE
      }
    }
    if (!hasImpossibles) {
      cat("none\n\n")
    }
    
    
    extra = 0
    
    cat("       Summary      \n")
    totalpersonsNeededByHhs = sum(hhold[, hValColi] * rep(seq(1, 8), each = 14))
    totalExistingpersons = sum(person[, pValColi])
    difference = totalpersonsNeededByHhs - totalExistingpersons
    percentage = difference / totalpersonsNeededByHhs * 100
    cat("In households file:", totalpersonsNeededByHhs, "\n")
    cat("In persons file:", totalExistingpersons, "\n")
    cat("Difference:", difference, " - ", percentage, "%\n\n")
    
    
    #Check grouphouseholds
    grpIndrwids = GetMatchingRowIds(person, pRelColi, "GroupHhold")
    ttlgrpin = sum(person[grpIndrwids, pValColi])
    cat("Group households: total persons in person file", ttlgrpin, "\n")
    grpHhsrwids = GetMatchingRowIds(hhold, hFamilyTypeColi, "Group household")
    
    ttlgrphh = sum(hhold[grpHhsrwids, hValColi] * seq(1, 8))
    cat("Group households: total persons in households file",
        ttlgrphh,
        "\n")
    
    diff = ttlgrphh - ttlgrpin
    if (diff > 0) {
      percent = diff / ttlgrpin * 100
      person[grpIndrwids, pValColi] = FillAccording2Dist(person[grpIndrwids, pValColi], diff)
      cat(
        "Group households: less persons than households, adding new agents:",
        diff,
        "(",
        percent,
        "%)\n"
      )
    } else if (diff == 0) {
      cat("Group households: No difference\n")
    } else{
      percent = diff / ttlgrpin * 100
      cat(
        "Group households: more persons than households, removing extra agents",
        diff,
        "(",
        percent,
        "%)\n"
      )
      person[grpIndrwids, pValColi] = FillAccording2Dist(person[grpIndrwids, pValColi], diff)
      extra = extra + diff
    }
    
    #Check lone person households
    lnpersonrwids = GetMatchingRowIds(person, pRelColi, "Lone person")
    ttllnpersons = sum(person[lnpersonrwids, pValColi])
    cat("Lone persons: total persons in persons file",
        ttllnpersons,
        "\n")
    lnpersonhhsrwids = GetMatchingRowIds(hhold, hFamilyTypeColi, "Lone person household")
    ttllnpersonhhs = sum(hhold[lnpersonhhsrwids, hValColi])
    cat("Lone persons: total persons in household files",
        ttllnpersonhhs,
        "\n")
    diff = ttllnpersonhhs - ttllnpersons
    if (diff > 0) {
      percent = diff / ttllnpersons * 100
      person[lnpersonrwids, pValColi] = FillAccording2Dist(person[lnpersonrwids, pValColi], diff)
      cat(
        "Lone person: less persons than households, adding new agents:",
        diff,
        "(",
        percent,
        "%)\n"
      )
    } else if (diff == 0) {
      print("Lone person: No difference")
    } else{
      percent = diff / ttllnpersonhhs * 100
      cat(
        "Lone persons: more persons than households, removing extra agents:",
        diff,
        "(",
        percent,
        "%)\n"
      )
      person[lnpersonrwids, pValColi] = FillAccording2Dist(person[lnpersonrwids, pValColi], diff)
      extra = extra + diff
    }
    
    #Match married males to married females
    marriedRwIds = GetMatchingRowIds(person, pRelColi, "Married")
    marMaleRwIds = GetMatchingRowIds(person[marriedRwIds, ], pSexColi, "Male")
    marFemaleRwIds = GetMatchingRowIds(person[marriedRwIds, ], pSexColi, "Female")
    ttlMarriedMales = sum(person[marMaleRwIds, pValColi])
    cat("Married couples: total married males in persons file:",
        ttlMarriedMales,
        "\n")
    ttlMarriedFemales = sum(person[marFemaleRwIds, pValColi])
    cat("Married couples: total married females in persons file:",
        ttlMarriedFemales,
        "\n")
    
    f1CplOnlyRwIds = GetMatchingRowIds(hhold,
                                       hFamilyTypeColi,
                                       "One family household: Couple family with no children")
    f2CplOnlyRwIds = GetMatchingRowIds(hhold,
                                       hFamilyTypeColi,
                                       "Two family household: Couple family with no children")
    f3CplOnlyRwIds = GetMatchingRowIds(
      hhold,
      hFamilyTypeColi,
      "Three or more family household: Couple family with no children"
    )
    
    f1CplYsChildRwIds = GetMatchingRowIds(hhold,
                                          hFamilyTypeColi,
                                          "One family household: Couple family with children")
    f2CplYsChildRwIds = GetMatchingRowIds(hhold,
                                          hFamilyTypeColi,
                                          "Two family household: Couple family with children")
    f3CplYsChildRwIds = GetMatchingRowIds(hhold,
                                          hFamilyTypeColi,
                                          "Three or more family household: Couple family with children")
    
    sumCplOnly = sum(hhold[f1CplOnlyRwIds, hValColi], hhold[f2CplOnlyRwIds, hValColi], hhold[f3CplOnlyRwIds, hValColi])
    cat(
      "Married couples: total couple families with no children in household file:",
      sumCplOnly,
      "\n"
    )
    sumCplYsChld = sum(hhold[f1CplYsChildRwIds, hValColi], hhold[f2CplYsChildRwIds, hValColi], hhold[f3CplYsChildRwIds, hValColi])
    cat(
      "Married couples: total couple families with children in household file:",
      sumCplYsChld,
      "\n"
    )
    minRequiredCpls = sum(sumCplYsChld, sumCplOnly)
    cat("Married couples: minimum required number of couples:",
        minRequiredCpls,
        "\n")
    
    diff = minRequiredCpls - ttlMarriedFemales
    if (diff > 0) {
      percent = diff / ttlMarriedFemales * 100
      person[marFemaleRwIds, pValColi] = FillAccording2Dist(person[marFemaleRwIds, pValColi], diff)
      cat(
        "Married Couples: there are not enough married females to form min required couples, increase married females by :",
        diff,
        "(",
        percent,
        "%)\n"
      )
    } else if (diff == 0) {
      cat("Married Couples: married females are equal to couple families\n")
    } else{
      cat("Married Couples: there are more married females than couple families, no problem\n")
    }
    
    diff = minRequiredCpls - ttlMarriedMales
    if (diff > 0) {
      percent =  diff / ttlMarriedMales * 100
      person[marMaleRwIds, pValColi] = FillAccording2Dist(person[marMaleRwIds, pValColi], diff)
      cat(
        "Married Couples: there are not enough married males to form min required couples, increase married males by :",
        diff,
        "(",
        percent,
        "%)\n"
      )
    } else if (diff == 0) {
      cat("Married Couples: married males are equal to couple families\n")
    } else{
      cat("Married Couples: there are more married males than couple families, no problem\n")
    }
    
    #Check Lone Parents
    f1OneParentRwIds = GetMatchingRowIds(hhold,
                                         hFamilyTypeColi,
                                         "One family household: One parent family")
    f2OneParentRwIds = GetMatchingRowIds(hhold,
                                         hFamilyTypeColi,
                                         "Two family household: One parent family")
    f3OneParentRwIds = GetMatchingRowIds(hhold,
                                         hFamilyTypeColi,
                                         "Three or more family household: One parent family")
    ttlOneParentFamilies = sum(hhold[f1OneParentRwIds, hValColi], hhold[f2OneParentRwIds, hValColi], hhold[f3OneParentRwIds, hValColi])
    cat(
      "Lone Parents: minimum required number of lone parents in households file",
      ttlOneParentFamilies,
      "\n"
    )
    
    loneParentRwIds = GetMatchingRowIds(person, pRelColi, "Lone parent")
    ttlLoneparents = sum(person[loneParentRwIds, pValColi])
    cat("Lone Parents: lone parents in persons file",
        ttlLoneparents,
        "\n")
    diff = ttlOneParentFamilies - ttlLoneparents
    if (diff > 0) {
      percent = diff / ttlLoneparents * 100
      person[loneParentRwIds, pValColi] = FillAccording2Dist(person[loneParentRwIds, pValColi], diff)
      cat(
        "Lone Parents: less lone parents than required by families, increasing lone parents by ",
        diff,
        " (",
        percent,
        "%)\n"
      )
    } else{
      cat(
        "Lone Parents: more lone parents in persons file than Lone parent primary families in households file, no problem\n"
      )
    }
    
    #Check children
    u15RwIds = GetMatchingRowIds(person, pRelColi, "U15Child")
    stuRwIds = GetMatchingRowIds(person, pRelColi, "Student")
    o15RwIds = GetMatchingRowIds(person, pRelColi, "O15Child")
    ttlChlds = sum(person[u15RwIds, pValColi], person[stuRwIds, pValColi], person[o15RwIds, pValColi])
    cat("Children: total children (U15 + Student + O15) in persons file:",
        ttlChlds,
        "\n")
    
    ttlFamiliesWithChildren = ttlOneParentFamilies + sumCplYsChld
    cat(
      "Children: total familes with children (One parent + Couple with children) in households file:",
      ttlFamiliesWithChildren,
      "\n"
    )
    
    diff = ttlFamiliesWithChildren - ttlChlds
    if (diff > 0) {
      percent = diff / ttlChlds * 100
      person[c(u15RwIds, stuRwIds, o15RwIds), pValColi] = FillAccording2Dist(person[c(u15RwIds, stuRwIds, o15RwIds), pValColi], diff)
      cat(
        "Children: less children than primary families requiring children, adding new agents:",
        diff,
        "(",
        percent,
        "%)\n"
      )
      availableExtraChildren = 0
    } else{
      availableExtraChildren = -diff
      cat(
        "Children: there are enough children to construct all known primary family structures requiring children, no problem\n"
      )
    }
    
    #Relatives and Other family
    relrwids = GetMatchingRowIds(person, pRelColi, "Relatives")
    ttlrelatives = sum(person[relrwids, pValColi])
    cat("Relatives for Other families: total relatives in persons file:",
        ttlrelatives,
        "\n")
    f1otherfamilyrwids = GetMatchingRowIds(hhold, hFamilyTypeColi, "One family household: Other family")
    f2otherfamilyrwids = GetMatchingRowIds(hhold, hFamilyTypeColi, "Two family household: Other family")
    f3otherfamilyrwids = GetMatchingRowIds(hhold,
                                           hFamilyTypeColi,
                                           "Three or more family household: Other family")
    reltivsFor1FOtherfamily = sum(hhold[f1otherfamilyrwids, hValColi] * seq(1:8))
    cat(
      "Relatives for Other families: total one family households(family type - other family):",
      sum(hhold[f1otherfamilyrwids, hValColi]),
      "\n"
    )
    cat(
      "Relatives for Other families: total relatives required to form all one family households(family type - other family):",
      reltivsFor1FOtherfamily,
      "\n"
    )
    Otherfamily2fn3f = sum(hhold[f2otherfamilyrwids, hValColi], hhold[f3otherfamilyrwids, hValColi])
    cat(
      "Relatives for Other families: total two and three family households (family type - Other family) in households file:",
      Otherfamily2fn3f,
      "\n"
    )
    minRelativesFor2fn3f = Otherfamily2fn3f * 2
    cat(
      "Relatives for Other families: minimum relatives required for two and three family households (family type - Other family) in households file:",
      minRelativesFor2fn3f,
      "\n"
    )
    hhrequiredreltives = minRelativesFor2fn3f + reltivsFor1FOtherfamily
    cat(
      "Relatives for Other families: minimum required relatives to form all primary Other familes:",
      hhrequiredreltives,
      "\n"
    )
    
    diff = hhrequiredreltives - ttlrelatives
    if (diff > 0) {
      percent = diff / ttlrelatives * 100
      person[relrwids, pValColi] = FillAccording2Dist(person[relrwids, pValColi], diff)
      cat(
        "Relatives for Other families: less persons than households, adding new agents:",
        diff,
        "(",
        percent,
        "%)\n"
      )
      availableExtraRelatives = 0
    } else{
      availableExtraRelatives = -diff
      cat(
        "Relatives for Other families: there are enough relatives to construch all basic other family structures\n"
      )
    }
    
    #The number of extra relatives or children needed for households based on the size of the primary family
    m3F1UnitExtras = c(0, 0, 0, 1, 2, 3, 4, 5)
    m2F1UnitExtras = c(0, 0, 1, 2, 3, 4, 5, 6)
    m2F2UnitExtras = c(0, 0, 0, 0, 1, 2, 3, 4)
    m2F3UnitExtras = c(0, 0, 0, 0, 0, 0, 1, 2)
    
    #Extra relatives to complete other family households
    relInOtherFamilyF1 = sum(hhold[f1otherfamilyrwids, hValColi] * m2F1UnitExtras)
    relInOtherFamilyF2 = sum(hhold[f2otherfamilyrwids, hValColi] * m2F2UnitExtras)
    relInOtherFamilyF3 = sum(hhold[f3otherfamilyrwids, hValColi] * m2F3UnitExtras)
    #Extra relatives to complete couple only households
    relInCplOnlyF1 = sum(hhold[f1CplOnlyRwIds, hValColi] * m2F1UnitExtras)
    relInCplOnlyF2 = sum(hhold[f2CplOnlyRwIds, hValColi] * m2F2UnitExtras)
    relInCplOnlyF3 = sum(hhold[f3CplOnlyRwIds, hValColi] * m2F3UnitExtras)
    
    requiredExtraRels = relInOtherFamilyF1 + relInOtherFamilyF2 + relInOtherFamilyF3 + relInCplOnlyF1 + relInCplOnlyF2 + relInCplOnlyF3
    cat(
      "Multi-family households with coule only and Other family: relatives required to complete households ",
      requiredExtraRels,
      "\n"
    )
    cat(
      "Multi-family households with coule only and Other family: relatives available after primary families",
      availableExtraRelatives,
      "\n"
    )
    if (requiredExtraRels > availableExtraRelatives) {
      diff = requiredExtraRels - availableExtraRelatives
      existingTtlrelatives = sum(person[relrwids, pValColi])
      percent = diff / existingTtlrelatives * 100
      person[relrwids, pValColi] = FillAccording2Dist(person[relrwids, pValColi], diff)
      cat(
        "Multi-family households with coule only and Other family: there are not enough relatives to complete these households, adding new relatives:",
        diff,
        "(",
        percent,
        "%)\n"
      )
    } else{
      cat(
        "Multi-family households with coule only and Other family: there are enough relatives to complete these households\n"
      )
    }
    
    #Extra relatives or children for couple with children.
    #We can't infer exact number for 2 and 3 family households because 2nd and 3rd families can be of different sizes.
    #So we infer relatives/children only for couple with children 1 family households
    relchldInClpWithChldF1 = sum(hhold[f1otherfamilyrwids, hValColi] * m3F1UnitExtras)
    
    #Extra relatives and children in one parent families
    relchldInOneParentF1 = sum(hhold[f1OneParentRwIds, hValColi] * m2F1UnitExtras)
    relchldInOneParentF2 = sum(hhold[f2OneParentRwIds, hValColi] * m2F2UnitExtras)
    relchldInOneParentF3 = sum(hhold[f3OneParentRwIds, hValColi] * m2F3UnitExtras)
    
    requiredRelsAndChildSum = relchldInClpWithChldF1 + relchldInOneParentF1 + relchldInOneParentF2 + relchldInOneParentF3
    cat(
      "Multi-family households Couple with children (1 family) and One parent family: children required to complete households ",
      requiredRelsAndChildSum,
      "\n"
    )
    cat(
      "Multi-family households Couple with children (1 family) and One parent family: children available after primary families",
      availableExtraChildren,
      "\n"
    )
    if (requiredRelsAndChildSum > availableExtraChildren) {
      diff = requiredRelsAndChildSum - availableExtraChildren
      precentage = diff / availableExtraChildren * 100
      person[c(u15RwIds, stuRwIds, o15RwIds), pValColi] = FillAccording2Dist(person[c(u15RwIds, stuRwIds, o15RwIds), pValColi], diff)
      cat(
        "Multi-family households Couple with children (1 family) and One parent family: there are not enough extra children to complete the households, adding new children:",
        diff,
        "(",
        percent,
        "%)\n"
      )
    } else{
      cat(
        "Multi-family households Couple with children (1 family) and One parent family: there are enough extra children to complete the households\n"
      )
    }
    
    cat("\n    Final Summary   \n")
    totalpersonsNeededByHhs = sum(hhold[, hValColi] * rep(seq(1, 8), each = 14))
    totalExistingpersons = sum(person[, pValColi])
    difference = totalpersonsNeededByHhs - totalExistingpersons
    error_percentage = difference / totalpersonsNeededByHhs * 100
    cat("In households file:", totalpersonsNeededByHhs, "\n")
    cat("In persons file:", totalExistingpersons, "\n")
    cat(
      "Difference (unrecongnised missing persons):",
      difference,
      " (",
      error_percentage,
      "%)\n\n"
    )
    
    return(list(person, hhold, starting_error, error_percentage))
  }
