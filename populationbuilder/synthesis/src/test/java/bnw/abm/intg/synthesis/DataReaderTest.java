package bnw.abm.intg.synthesis;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author wniroshan 26 Mar 2018
 */
public class DataReaderTest {

    @Test
    public void testReadPersonRecords() throws IOException, ClassNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("Armadale/preprocessed/person_types.csv.gz").getFile());

        Map<String, List<IndRecord>> expectedIndRecs, indRecords = DataReader.readPersonRecords(file.toPath());

        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File(classLoader.getResource(
                "expected/Armadale_person_types.obj").getFile())));
        expectedIndRecs = (Map<String, List<IndRecord>>) oi.readObject();

        Assert.assertEquals("IndRecords SA2 name", indRecords.keySet().toArray()[0], "Armadale");
        Assert.assertEquals("IndRecords structure", indRecords, expectedIndRecs);
    }

    @Test
    public void testReadHouseholdRecords() throws IOException, ClassNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("Armadale/preprocessed/household_types.csv.gz").getFile());

        Map<String, List<HhRecord>> expectedHhRecs, hhRecords = DataReader.readHouseholdRecords(file.toPath());

        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File(classLoader.getResource(
                "expected/Armadale_household_types.obj").getFile())));
        expectedHhRecs = (Map<String, List<HhRecord>>) oi.readObject();

        Assert.assertEquals("HhRecords SA2 name", hhRecords.keySet().toArray()[0], "Armadale");
        Assert.assertEquals("HhRecords structure", hhRecords, expectedHhRecs);
    }
}
//
//