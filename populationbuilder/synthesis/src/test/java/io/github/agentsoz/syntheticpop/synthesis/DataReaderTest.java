package io.github.agentsoz.syntheticpop.synthesis;

import io.github.agentsoz.syntheticpop.synthesis.models.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author wniroshan 26 Mar 2018
 */
public class DataReaderTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

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

    @Test
    public void testReadAgeDistribution() throws IOException {
        Map<String, String> paramsMap = new HashMap<>(4, 1);
        ClassLoader classLoader = getClass().getClassLoader();

        paramsMap.put("FileName", "Persons_percentage_by_age_2016_Greater_Melbourne_SA2s.txt");
        paramsMap.put("AgeColumn", "0");
        paramsMap.put("SA2NamesRow", "10");

        Throwable exception = assertThrows(Error.class, () -> DataReader.readAgeDistribution(paramsMap));

        Path inputFile = new File(classLoader.getResource("Persons_percentage_by_age_2016_Greater_Melbourne_SA2s.zip").getFile()).toPath();
        paramsMap.put("FileName", inputFile.toString());

        List<Double> ageDist = DataReader.readAgeDistribution(paramsMap).get("Armadale");

        Double[] expectedValues = {0.9916435, 1.1699164, 0.9470752, 0.902507, 1.0473538, 0.8022284, 0.913649, 0.9916435, 1.0027855,
                                   0.8690808, 0.9582173, 0.8245125, 0.7465181, 0.902507, 0.6239554, 0.9359331, 1.0250696, 0.6016713,
                                   0.9247911, 0.9805014, 0.9916435, 1.2924791, 1.5933148, 1.6713092, 1.6490251, 2.005571, 1.9387187,
                                   1.9164345, 2.2061281, 2.0724234, 2.0947075, 2.1504178, 2.4401114, 1.9052925, 2.005571, 1.7381616,
                                   1.6824513, 1.6713092, 1.4150418, 1.448468, 1.3927577, 1.4038997, 1.281337, 1.3704735, 1.2479109,
                                   1.2256267, 1.2256267, 1.281337, 1.281337, 1.2367688, 1.1142061, 1.1253482, 1.3370474, 1.1476323,
                                   0.9916435, 0.9582173, 1.1699164, 0.9470752, 0.8802228, 0.9247911, 1.0696379, 0.9582173, 0.9359331,
                                   1.0139276, 0.9805014, 1.2033426, 0.902507, 0.8690808, 0.902507, 1.0473538, 0.9805014, 1.1142061,
                                   0.8467967, 0.7465181, 0.6908078, 0.6462396, 0.5348189, 0.6462396, 0.7910864, 0.6573816, 0.4122563,
                                   0.4122563, 0.4122563, 0.4122563, 0.4456825, 0.2339833, 0.2674095, 0.3788301, 0.2785515, 0.2896936,
                                   0.2674095, 0.2005571, 0.2674095, 0.1448468, 0.1002786, 0.1002786, 0d, 0d, 0d, 0d, 0d, 0.0334262, 0d,
                                   0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d};

        Assertions.assertEquals(Arrays.stream(expectedValues).mapToDouble(v -> v).sum(), ageDist.stream().mapToDouble(d -> d).sum());
        Assertions.assertEquals(expectedValues.length, ageDist.size());
        Assertions.assertArrayEquals(expectedValues, ageDist.toArray());

    }
}