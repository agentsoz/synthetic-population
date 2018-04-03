package io.github.agentsoz.syntheticpop.synthesis;

import io.github.agentsoz.syntheticpop.synthesis.models.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wniroshan 26 Mar 2018
 */
public class DataReaderTest {

    @Test
    public void testReadPersonRecords() throws IOException {
        //Expected
        //Armadale	Married	Male	0-14 years	0
        //Armadale	Married	Male	70-84 years	238
        //Armadale	Student	Female	0-14 years	0
        //Armadale	GroupHhold	Female	55-69 years	9
        //Armadale	Lone person	Female	25-39 years	214
        //Armadale	Relatives	Female	100 years and over	0

        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("Armadale/preprocessed/person_types.csv.gz").getFile());

        Map<String, List<IndRecord>> indRecords = DataReader.readPersonRecords(file.toPath());

        Assert.assertEquals("IndRecords SA2 name", indRecords.keySet().toArray()[0], "Armadale");
        for (IndRecord r : indRecords.get("Armadale")) {
            if (r.RELATIONSHIP_STATUS == RelationshipStatus.MARRIED && r.SEX == Sex.Male && r.AGE_RANGE == AgeRange.A0_14) {
                Assert.assertEquals("Married Male 0-14", r.IND_COUNT, 0);
            } else if (r.RELATIONSHIP_STATUS == RelationshipStatus.MARRIED && r.SEX == Sex.Male && r.AGE_RANGE == AgeRange.A70_84) {
                Assert.assertEquals("Married Male 70-84", r.IND_COUNT, 238);
            } else if (r.RELATIONSHIP_STATUS == RelationshipStatus.STUDENT && r.SEX == Sex.Female && r.AGE_RANGE == AgeRange.A0_14) {
                Assert.assertEquals("Student Female 0-14", r.IND_COUNT, 0);
            } else if (r.RELATIONSHIP_STATUS == RelationshipStatus.GROUP_HOUSEHOLD && r.SEX == Sex.Female && r.AGE_RANGE == AgeRange
                    .A55_69) {
                Assert.assertEquals("Group household Female 55-69", r.IND_COUNT, 9);
            } else if (r.RELATIONSHIP_STATUS == RelationshipStatus.LONE_PERSON && r.SEX == Sex.Female && r.AGE_RANGE == AgeRange.A25_39) {
                Assert.assertEquals("Lone person Female 25-39", r.IND_COUNT, 214);
            } else if (r.RELATIONSHIP_STATUS == RelationshipStatus.RELATIVE && r.SEX == Sex.Female && r.AGE_RANGE == AgeRange.A100_110) {
                Assert.assertEquals("Relative Female 100+", r.IND_COUNT, 0);
            }
        }
    }

    @Test
    public void testReadHouseholdRecords() throws IOException, ClassNotFoundException {

        //Expected
        //Armadale	One person	One family household: Couple family with no children	0
        //Armadale	One person	Lone person household	1347
        //Armadale	Four persons	One family household: Couple family with no children	11
        //Armadale	Five persons	Two family household: One parent family	5
        //Armadale	Eight or more persons	One family household: Couple family with children	4

        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("Armadale/preprocessed/household_types.csv.gz")).getFile());

        Map<String, List<HhRecord>> hhRecords = DataReader.readHouseholdRecords(file.toPath());

        Assert.assertEquals("IndRecords SA2 name", hhRecords.keySet().toArray()[0], "Armadale");
        for (HhRecord r : hhRecords.get("Armadale")) {
            if (r.NUM_OF_PERSONS_PER_HH == 1 && r.FAMILY_HOUSEHOLD_TYPE == FamilyHouseholdType.F1_COUPLE_ONLY) {
                Assert.assertEquals("1 person: 1 family couple with no children", r.HH_COUNT, 0);
            } else if (r.NUM_OF_PERSONS_PER_HH == 1 && r.FAMILY_HOUSEHOLD_TYPE == FamilyHouseholdType.LONE_PERSON) {
                Assert.assertEquals("1 person: lone person", r.HH_COUNT, 1347);
            } else if (r.NUM_OF_PERSONS_PER_HH == 4 && r.FAMILY_HOUSEHOLD_TYPE == FamilyHouseholdType.F1_COUPLE_ONLY) {
                Assert.assertEquals("4 persons: 1 family couple with no children", r.HH_COUNT, 11);
            } else if (r.NUM_OF_PERSONS_PER_HH == 5 && r.FAMILY_HOUSEHOLD_TYPE == FamilyHouseholdType.F2_ONE_PARENT) {
                Assert.assertEquals("5 persons: 2 family one parent", r.HH_COUNT, 5);
            } else if (r.NUM_OF_PERSONS_PER_HH == 8 && r.FAMILY_HOUSEHOLD_TYPE == FamilyHouseholdType.F1_COUPLE_WITH_CHILDREN) {
                Assert.assertEquals("8 persons: 1 family couple with children", r.HH_COUNT, 4);
            }
        }
    }
}