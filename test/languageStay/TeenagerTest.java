package languageStay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeenagerTest {
    Teenager t1, t2, t3;
    String n1, fn1, n2, fn2;
    LocalDate d1, d2;
    Country c1, c2;

    @BeforeEach
    public void testInitialization() {
        d1 = LocalDate.of(2005, 5, 12);
        d2 = LocalDate.of(2004, 12, 3);
        n1 = "Paul"; fn1 = "Jean";
        n2 = "Kroos"; fn2 = "Toni";
        c1 = Country.FRANCE; c2 = Country.GERMANY;
        t1 = new Teenager(n1, fn1, d1, c1);
        t2 = new Teenager(n2, fn2, d2, c2);
    }

    @Test
    public void testAddCriterion() {
        t1.addCriterion(CriterionName.GENDER, "M");
        assertEquals("M", t1.getCriterion(CriterionName.GENDER));
        t1.addCriterion(CriterionName.GENDER, "F");
        assertEquals("F", t1.getCriterion(CriterionName.GENDER));
        assertNull(t1.getCriterion(CriterionName.HISTORY));
    }

    @Test
    public void testCompatibility() {
        t1.addCriterion(CriterionName.GENDER, "M");
        t1.addCriterion(CriterionName.HOBBIES, "Sport");
        t1.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t1.addCriterion(CriterionName.HOST_FOOD, "");
        t1.addCriterion(CriterionName.GUEST_FOOD, "");

        t2.addCriterion(CriterionName.GENDER, "M");
        t2.addCriterion(CriterionName.HOBBIES, "Sport");
        t2.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t2.addCriterion(CriterionName.HOST_FOOD, "");
        t2.addCriterion(CriterionName.GUEST_FOOD, "");

        assertTrue(t1.compatibleWithGuest(t2));
        assertTrue(t2.compatibleWithGuest(t1));

        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t2.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");

        assertTrue(t1.compatibleWithGuest(t2));
        assertFalse(t2.compatibleWithGuest(t1));
    }

    @Test
    public void testEquals(){
        assertTrue(t1.equals(t1));
        assertFalse(t1.equals(t2));
    }
    
}
