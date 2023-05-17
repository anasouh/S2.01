package languageStay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeenagerTest {
    Teenager t1, t2, t3, t4;
    String n1, fn1, n2, fn2, n3, fn3, n4, fn4;
    LocalDate d1, d2, d3, d4;
    Country c1, c2, c3, c4;

    @BeforeEach
    public void testInitialization() {
        d1 = LocalDate.of(2005, 5, 12);
        d2 = LocalDate.of(2004, 12, 3);
        d3 = LocalDate.of(2004, 1, 16);
        d4 = LocalDate.of(2006, 3, 18);
        n1 = "Paul"; fn1 = "Jean";
        n2 = "Kroos"; fn2 = "Toni";
        n3 = "Iniesta"; fn3 = "Andres";
        n4 = "Mario"; fn4 = "Super";
        c1 = Country.FRANCE; c2 = Country.GERMANY; c3 = Country.SPAIN; c4 = Country.ITALY;
        t1 = new Teenager(n1, fn1, d1, c1);
        t2 = new Teenager(n2, fn2, d2, c2);
        t3 = new Teenager(n3, fn3, d3, c3);
        t4 = new Teenager(n4, fn4, d4, c4);
    }
    
    @Test
    public void testPurgeInvalidRequierement() {
        t1.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t1.addCriterion(CriterionName.GENDER, "M");
        t3.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "jsp");
        t3.addCriterion(CriterionName.GENDER, "M");
        t1.purgeInvalidRequierement();
        t3.purgeInvalidRequierement();
        t1.purgeIncoherentRequirement();
        t3.purgeIncoherentRequirement();
        assertEquals(1 , t1.getNbCriterion());
        assertEquals(2 , t3.getNbCriterion());
    }

    @Test
    public void testCompatibility() {
        t1.addCriterion(CriterionName.GENDER, "M");
        t1.addCriterion(CriterionName.HOBBIES, "Sport");
        t1.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t1.addCriterion(CriterionName.HOST_FOOD, "nonuts,vegetarian");
        t1.addCriterion(CriterionName.GUEST_FOOD, "vegetarian");

        t2.addCriterion(CriterionName.GENDER, "M");
        t2.addCriterion(CriterionName.HOBBIES, "Sport");
        t2.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t2.addCriterion(CriterionName.HOST_FOOD, "");
        t2.addCriterion(CriterionName.GUEST_FOOD, "");

        t3.addCriterion(CriterionName.GENDER, "M");
        t3.addCriterion(CriterionName.HOBBIES, "Video_games");
        t3.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t3.addCriterion(CriterionName.HOST_FOOD, "");
        t3.addCriterion(CriterionName.GUEST_FOOD, "");

        assertTrue(t1.compatibleWithGuest(t2));
        assertFalse(t1.compatibleWithGuest(t3));
        assertFalse(t2.compatibleWithGuest(t1));
        assertTrue(t2.compatibleWithGuest(t3));
        assertFalse(t3.compatibleWithGuest(t1));
        assertTrue(t3.compatibleWithGuest(t2));
    } 

    @Test
    public void testCriterionEquals() {
        t1.addCriterion(CriterionName.GENDER, "M");
        t1.addCriterion(CriterionName.HOBBIES, "Sport");

        t2.addCriterion(CriterionName.GENDER, "M");
        t2.addCriterion(CriterionName.HOBBIES, "Art");

        assertTrue(t1.criterionEquals(CriterionName.GENDER.name(), t2.getCriterion(CriterionName.GENDER)));
        assertFalse(t1.criterionEquals(CriterionName.HOBBIES.name(), t2.getCriterion(CriterionName.HOBBIES)));
    }

    @Test
    public void testNbLoisirCommun(){
        t1.addCriterion(CriterionName.HOBBIES, "Sport");
        t2.addCriterion(CriterionName.HOBBIES, "Sport, Cuisine");
        t3.addCriterion(CriterionName.HOBBIES, "Video_games");
        t4.addCriterion(CriterionName.HOBBIES, "Sport, Cuisine");
        assertEquals(0, t2.nbLoisirCommun(t3));
        assertEquals(1, t2.nbLoisirCommun(t1));
        assertEquals(2, t2.nbLoisirCommun(t4));
    }
    
}
