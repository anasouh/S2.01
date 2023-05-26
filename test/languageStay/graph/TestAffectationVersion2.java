package languageStay.graph;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import languageStay.*;

public class TestAffectationVersion2 {
    Teenager t1, t2, t3, t4, t5, t6, t7, t8, t11, t12, t13, t14, t15, t16;
    Affectations history1 = new Affectations();
    Affectations history2 = new Affectations();
        

        @BeforeEach
        public void testInitialization(){
        //exemple1
        t1 = new Teenager("Jack", "Grealish", LocalDate.now(), Country.GERMANY);
        t2 = new Teenager("Phil", "Foden", LocalDate.now(), Country.GERMANY);
        t3 = new Teenager("Harry", "Kane", LocalDate.now(), Country.GERMANY);
        t4 = new Teenager("Kyle", "Walker", LocalDate.now(), Country.GERMANY);
        t5 = new Teenager("Pedri", "Gonzalez", LocalDate.now(), Country.SPAIN);
        t6 = new Teenager("Nico", "Williams", LocalDate.now(), Country.SPAIN);
        t7 = new Teenager("David", "DeGea", LocalDate.now(), Country.SPAIN);
        t8 = new Teenager("Xavi", "Hernandez", LocalDate.now(), Country.SPAIN);

        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t4.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t5.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t6.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t7.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t8.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");

        t1.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t2.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t3.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t4.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t5.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t6.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t7.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t8.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");

        t1.addCriterion(CriterionName.HOBBIES, "");
        t2.addCriterion(CriterionName.HOBBIES, "");
        t3.addCriterion(CriterionName.HOBBIES, "");
        t4.addCriterion(CriterionName.HOBBIES, "");
        t5.addCriterion(CriterionName.HOBBIES, "");
        t6.addCriterion(CriterionName.HOBBIES, "");
        t7.addCriterion(CriterionName.HOBBIES, "");
        t8.addCriterion(CriterionName.HOBBIES, "");

        t1.addCriterion(CriterionName.GUEST_FOOD, "");
        t2.addCriterion(CriterionName.GUEST_FOOD, "");
        t3.addCriterion(CriterionName.GUEST_FOOD, "");
        t4.addCriterion(CriterionName.GUEST_FOOD, "");
        t5.addCriterion(CriterionName.GUEST_FOOD, "");
        t6.addCriterion(CriterionName.GUEST_FOOD, "");
        t7.addCriterion(CriterionName.GUEST_FOOD, "");
        t8.addCriterion(CriterionName.GUEST_FOOD, "");

        t1.addCriterion(CriterionName.HOST_FOOD, "");
        t2.addCriterion(CriterionName.HOST_FOOD, "");
        t3.addCriterion(CriterionName.HOST_FOOD, "");
        t4.addCriterion(CriterionName.HOST_FOOD, "");
        t5.addCriterion(CriterionName.HOST_FOOD, "");
        t6.addCriterion(CriterionName.HOST_FOOD, "");
        t7.addCriterion(CriterionName.HOST_FOOD, "");
        t8.addCriterion(CriterionName.HOST_FOOD, "");

        t1.addCriterion(CriterionName.HISTORY, "same");
        t2.addCriterion(CriterionName.HISTORY, "same");
        t3.addCriterion(CriterionName.HISTORY, "");
        t4.addCriterion(CriterionName.HISTORY, "other");
        t5.addCriterion(CriterionName.HISTORY, "other");
        t6.addCriterion(CriterionName.HISTORY, "same");
        t7.addCriterion(CriterionName.HISTORY, "");
        t8.addCriterion(CriterionName.HISTORY, "other");


        history1.affecter(t1, t8);
        history1.affecter(t3, t7);
        history1.affecter(t4, t5);
        history1.affecter(t2, t6);
        Affectations.exporter(history1, "GERMANY_SPAIN.bin");
        //System.out.println(AffectationUtil.sejourExiste(Country.GERMANY, Country.SPAIN));

        //exemple2
        t11 = new Teenager("A", "Adonia", LocalDate.now(), Country.FRANCE);
        t12 = new Teenager("B", "Bellatrix", LocalDate.now(), Country.FRANCE);
        t13 = new Teenager("C", "Callista", LocalDate.now(), Country.FRANCE);
        t14 = new Teenager("X", "Xolag", LocalDate.now(), Country.SPAIN);
        t15 = new Teenager("Y", "Yak", LocalDate.now(), Country.SPAIN);
        t16 = new Teenager("Z", "Zander", LocalDate.now(), Country.SPAIN);

        t11.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t12.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t13.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t14.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t15.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t16.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");

        t11.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t12.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t13.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t14.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t15.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t16.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");

        t11.addCriterion(CriterionName.HOBBIES, "sports,technology");
        t12.addCriterion(CriterionName.HOBBIES, "culture,science");
        t13.addCriterion(CriterionName.HOBBIES, "science,reading");
        t14.addCriterion(CriterionName.HOBBIES, "culture,technology");
        t15.addCriterion(CriterionName.HOBBIES, "science,reading");
        t16.addCriterion(CriterionName.HOBBIES, "technology");

        t11.addCriterion(CriterionName.GUEST_FOOD, "");
        t12.addCriterion(CriterionName.GUEST_FOOD, "vegetarian");
        t13.addCriterion(CriterionName.GUEST_FOOD, "nonuts");
        t14.addCriterion(CriterionName.GUEST_FOOD, "");
        t15.addCriterion(CriterionName.GUEST_FOOD, "");
        t16.addCriterion(CriterionName.GUEST_FOOD, "vegatarian");

        t11.addCriterion(CriterionName.HOST_FOOD, "");
        t12.addCriterion(CriterionName.HOST_FOOD, "");
        t13.addCriterion(CriterionName.HOST_FOOD, "meal");
        t14.addCriterion(CriterionName.HOST_FOOD, "");
        t15.addCriterion(CriterionName.HOST_FOOD, "nonuts");
        t16.addCriterion(CriterionName.HOST_FOOD, "meal");

        t11.addCriterion(CriterionName.HISTORY, "same");
        t12.addCriterion(CriterionName.HISTORY, "");
        t13.addCriterion(CriterionName.HISTORY, "same");
        t14.addCriterion(CriterionName.HISTORY, "");
        t15.addCriterion(CriterionName.HISTORY, "");
        t16.addCriterion(CriterionName.HISTORY, "same");

        history2.affecter(t11, t14);
        history2.affecter(t12, t15);
        history2.affecter(t13, t16);
        Affectations.exporter(history2, "FRANCE_SPAIN.bin");
        //System.out.println(AffectationUtil.sejourExiste(Country.FRANCE, Country.SPAIN));
    }
    
    @Test
    public void testExemple1(){
        assertEquals(10, AffectationUtil.weight(t1, t5));
        assertEquals(10, AffectationUtil.weight(t1, t6));
        assertEquals(10, AffectationUtil.weight(t1, t7));
        assertEquals(1010, AffectationUtil.weight(t1, t8));
        assertEquals(10, AffectationUtil.weight(t2, t5));
        assertEquals(0, AffectationUtil.weight(t2, t6));
        assertEquals(10, AffectationUtil.weight(t2, t7));
        assertEquals(10, AffectationUtil.weight(t2, t8));
        assertEquals(10, AffectationUtil.weight(t3, t5));
        assertEquals(10, AffectationUtil.weight(t3, t6));
        assertEquals(10, AffectationUtil.weight(t3, t7));
        assertEquals(10, AffectationUtil.weight(t3, t8));
        assertEquals(1010, AffectationUtil.weight(t4, t5));
        assertEquals(10, AffectationUtil.weight(t4, t6));
        assertEquals(10, AffectationUtil.weight(t4, t7));
        assertEquals(10, AffectationUtil.weight(t4, t8));
    }

    @Test
    public void testExemple2(){
        assertEquals(-2, AffectationUtil.weight(t11, t14));
        assertEquals(1010, AffectationUtil.weight(t11, t15));
        assertEquals(1008, AffectationUtil.weight(t11, t16));
        assertEquals(8, AffectationUtil.weight(t12, t14));
        assertEquals(8, AffectationUtil.weight(t12, t15));
        assertEquals(1010, AffectationUtil.weight(t12, t16));
        assertEquals(1010, AffectationUtil.weight(t13, t14));
        assertEquals(6, AffectationUtil.weight(t13, t15));
        assertEquals(1000, AffectationUtil.weight(t13, t16));
    }
}
