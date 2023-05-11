package languageStay.graph;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import languageStay.*;

public class TestAffectationVersion1 {
    Teenager t1, t2, t3, t4, t5, t6;

    @BeforeEach
    public void testInitialization(){
        System.out.println("je m'execute");
        t1 = new Teenager("A", "Adonia", LocalDate.now(), Country.FRANCE);
        t2 = new Teenager("B", "Bellatrix", LocalDate.now(), Country.FRANCE);
        t3 = new Teenager("C", "Callista", LocalDate.now(), Country.FRANCE);
        t4 = new Teenager("X", "Xolag", LocalDate.now(), Country.ITALY);
        t5 = new Teenager("Y", "Yak", LocalDate.now(), Country.ITALY);
        t6 = new Teenager("Z", "Zander", LocalDate.now(), Country.ITALY);
    
        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");

        t4.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t5.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t6.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");

        t1.addCriterion(CriterionName.HOBBIES, "sports,technology");
        t2.addCriterion(CriterionName.HOBBIES, "culture,science");
        t3.addCriterion(CriterionName.HOBBIES, "science,reading");
        t4.addCriterion(CriterionName.HOBBIES, "culture,technology");
        t5.addCriterion(CriterionName.HOBBIES, "science,reading");
        t6.addCriterion(CriterionName.HOBBIES, "technology");

        t1.addCriterion(CriterionName.GUEST_FOOD, "");
        t2.addCriterion(CriterionName.GUEST_FOOD, "");
        t3.addCriterion(CriterionName.GUEST_FOOD, "");
        t4.addCriterion(CriterionName.HOST_FOOD, "");
        t5.addCriterion(CriterionName.HOST_FOOD, "");
        t6.addCriterion(CriterionName.HOST_FOOD, "");
    }

    @Test
    public void testWeight(){
        System.out.println(t4);
        assertEquals(8, AffectationUtil.weight(t4, t1));
        assertEquals(110, AffectationUtil.weight(t5, t1));
        assertEquals(8, AffectationUtil.weight(t6, t1));
        assertEquals(8, AffectationUtil.weight(t4, t2));
        assertEquals(108, AffectationUtil.weight(t5, t2));
        assertEquals(110, AffectationUtil.weight(t6, t2));
        assertEquals(110, AffectationUtil.weight(t4, t3));
        assertEquals(6, AffectationUtil.weight(t5, t3));
        assertEquals(110, AffectationUtil.weight(t6, t3));
    }
}
