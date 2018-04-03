package io.github.agentsoz.syntheticpop.synthesis;

import io.github.agentsoz.syntheticpop.synthesis.models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author wniroshan 26 Mar 2018
 */
public class ExtrasHandlerTest {
    private ExtrasHandler extrasHandler;
    private Random random = new Random(1);


    @Before
    public void createExtrasHandlerInstance() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        File file1 = new File(Objects.requireNonNull(classLoader.getResource("Armadale/preprocessed/person_types.csv.gz")).getFile());
        Map<String, List<IndRecord>> indRecords = DataReader.readPersonRecords(file1.toPath());

        File file2 = new File(Objects.requireNonNull(classLoader.getResource("Armadale/preprocessed/household_types.csv.gz")).getFile());
        Map<String, List<HhRecord>> hhRecords = DataReader.readHouseholdRecords(file2.toPath());

        extrasHandler = new ExtrasHandler(indRecords.get("Armadale"), random);
        extrasHandler.formExtras(hhRecords.get("Armadale"));
    }

    @Test
    public void testFormingExtras() {
        Assert.assertNotNull(extrasHandler);
        Assert.assertEquals("Number of extras", extrasHandler.remainingExtras(), 145);
    }

    @Test
    public void testAddToExtras() throws NoSuchFieldException, IllegalAccessException {

        int startingSize = extrasHandler.remainingExtras();

        Person p = new Person();
        p.setRelationshipStatus(RelationshipStatus.RELATIVE);
        p.setAgeRange(AgeRange.A25_39);
        p.setSex(Sex.Male);
        extrasHandler.addToExtras(new ArrayList<>(Collections.singleton(p)));

        Assert.assertTrue("New person added to extras - extras increments by one", startingSize + 1 == extrasHandler.remainingExtras());

        Field field = ExtrasHandler.class.getDeclaredField("extras");
        field.setAccessible(true);
        List<Person> extras = (List<Person>) field.get(extrasHandler);

        Person lastAddedToExtras = extras.get(extras.size() - 1);
        Assert.assertEquals("New person added to extra - age", lastAddedToExtras.getAgeRange(), AgeRange.A25_39);
        Assert.assertEquals("New person added to extra - sex", lastAddedToExtras.getSex(), Sex.Male);
        Assert.assertEquals("New person added to extra - relationship status", lastAddedToExtras.getRelationshipStatus(), null);
    }

    @Test
    public void testGetPersonsFromExtras() throws NoSuchFieldException, IllegalAccessException {
        int startExtrasCount = extrasHandler.remainingExtras();
        List<Person> pl = extrasHandler.getPersonsFromExtras(RelationshipStatus.LONE_PARENT, Sex.Female, AgeRange.A40_54, 1);
        Assert.assertTrue("Person taken from extras - count", startExtrasCount - 1 == extrasHandler.remainingExtras());
        Assert.assertEquals("Person taken from extras - age", pl.get(0).getAgeRange(), AgeRange.A40_54);
        Assert.assertEquals("Person taken from extras - sex", pl.get(0).getSex(), Sex.Female);
        Assert.assertEquals("Person taken from extras - relationship status",
                            pl.get(0).getRelationshipStatus(),
                            RelationshipStatus.LONE_PARENT);


        Person p = new Person();
        p.setRelationshipStatus(RelationshipStatus.RELATIVE);
        p.setAgeRange(AgeRange.A25_39);
        p.setSex(Sex.Male);
        extrasHandler.addToExtras(new ArrayList<>(Collections.singleton(p)));

        pl = extrasHandler.getPersonsFromExtras(RelationshipStatus.RELATIVE, Sex.Male, AgeRange.A25_39, 1);

        Field field = ExtrasHandler.class.getDeclaredField("extras");
        field.setAccessible(true);
        List<Person> extras = (List<Person>) field.get(extrasHandler);

        Assert.assertTrue("All persons in extras have no sex", extras.stream().filter(e -> e.getSex() == null).count() == extrasHandler.remainingExtras());
        Assert.assertTrue("All persons in extras have no age range", extras.stream().filter(e -> e.getAgeRange() == null).count() == extrasHandler.remainingExtras());
        Assert.assertTrue("All persons in extras have no relationship status", extras.stream().filter(e -> e.getRelationshipStatus() == null).count() == extrasHandler.remainingExtras());

    }
}
