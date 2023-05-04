package languageStay;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlatformTest {
    Teenager t1, t2, t3;
    String n1, fn1, n2, fn2, n3, fn3;
    LocalDate d1, d2, d3;
    Country c1, c2, c3;
    Plateform pf;
    ArrayList<Teenager> l1;
    Teenager[] l2;

    @BeforeEach
    public void testInitialization() {
        pf = new Plateform();
        d1 = LocalDate.of(2005, 5, 12);
        d2 = LocalDate.of(2004, 12, 3);
        d3 = LocalDate.of(2004, 1, 16);
        n1 = "Paul"; fn1 = "Jean";
        n2 = "Kroos"; fn2 = "Toni";
        n3 = "Iniesta"; fn3 = "Andres";
        c1 = Country.FRANCE; c2 = Country.GERMANY; c3 = Country.SPAIN;
        t1 = new Teenager(n1, fn1, d1, c1);
        t2 = new Teenager(n2, fn2, d2, c2);
        t3 = new Teenager(n3, fn3, d3, c3);

        l1 = new ArrayList<Teenager>();
        l1.add(t1);
        l1.add(t2);
        l1.add(t3);

        l2 = new Teenager[]{t1, t2, t3};

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
    }

    @Test
    public void testClear() {
        pf.ajouter(t1);
        pf.ajouter(t2);
        pf.ajouter(t3);

        assertEquals(pf.size(), 3);
        pf.clear();
        assertEquals(pf.size(), 0);
    }

    @Test
    public void testAdd() {
        pf.ajouter(t1);
        pf.ajouter(t2);
        pf.ajouter(t3);
        assertTrue(pf.contains(t1));
        assertTrue(pf.contains(t2));
        assertTrue(pf.contains(t3));

        pf.clear();
        
        pf.ajouter(l1);
        assertTrue(pf.contains(t1));
        assertTrue(pf.contains(t2));
        assertTrue(pf.contains(t3));
        
        pf.ajouter(l2);
        assertTrue(pf.contains(t1));
        assertTrue(pf.contains(t2));
        assertTrue(pf.contains(t3));
    }

    @Test
    public void testSupprimer() {
        pf.ajouter(t1);
        pf.ajouter(t2);
        pf.ajouter(t3);
        pf.supprimer(2);
        assertEquals(pf.size(), 2);
        pf.clear();
        
        pf.ajouter(t1);
        pf.ajouter(t2);
        pf.supprimer(3);
        assertEquals(pf.size(), 2);
        pf.clear();

        pf.ajouter(t1);
        pf.ajouter(t2);
        pf.ajouter(t3);
        pf.supprimer(1, c1);
        assertFalse(pf.contains(t1));
    }

}
