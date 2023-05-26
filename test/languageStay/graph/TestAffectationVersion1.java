package languageStay.graph;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import languageStay.*;

public class TestAffectationVersion1 {
    Teenager t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
    Affectations history = new Affectations();

    @BeforeEach
    public void testInitialization(){
        t1 = new Teenager("A", "Adonia", LocalDate.now(), Country.FRANCE);
        t2 = new Teenager("B", "Bellatrix", LocalDate.now(), Country.FRANCE);
        t3 = new Teenager("C", "Callista", LocalDate.now(), Country.FRANCE);
        t4 = new Teenager("X", "Xolag", LocalDate.now(), Country.ITALY);
        t5 = new Teenager("Y", "Yak", LocalDate.now(), Country.ITALY);
        t6 = new Teenager("Z", "Zander", LocalDate.now(), Country.ITALY);
        t7 = new Teenager("A", "A", LocalDate.now(), Country.ITALY);
        t8 = new Teenager("B", "B", LocalDate.now(), Country.ITALY);
        t9 = new Teenager("C", "C", LocalDate.now(), Country.GERMANY);
        t10 = new Teenager("D", "D", LocalDate.now(), Country.GERMANY);
    
        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t7.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t8.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t9.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t10.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");

        t4.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t5.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t6.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t7.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t8.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t9.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t10.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");

        t1.addCriterion(CriterionName.HOBBIES, "sports,technology");
        t2.addCriterion(CriterionName.HOBBIES, "culture,science");
        t3.addCriterion(CriterionName.HOBBIES, "science,reading");
        t4.addCriterion(CriterionName.HOBBIES, "culture,technology");
        t5.addCriterion(CriterionName.HOBBIES, "science,reading");
        t6.addCriterion(CriterionName.HOBBIES, "technology");
        t7.addCriterion(CriterionName.HOBBIES, "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx;yes;yes");
        t8.addCriterion(CriterionName.HOBBIES, "");
        t9.addCriterion(CriterionName.HOBBIES, "");
        t10.addCriterion(CriterionName.HOBBIES, "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx;yes;yes");


        t1.addCriterion(CriterionName.GUEST_FOOD, "");
        t2.addCriterion(CriterionName.GUEST_FOOD, "");
        t3.addCriterion(CriterionName.GUEST_FOOD, "");
        t4.addCriterion(CriterionName.HOST_FOOD, "");
        t5.addCriterion(CriterionName.HOST_FOOD, "");
        t6.addCriterion(CriterionName.HOST_FOOD, "");
        t7.addCriterion(CriterionName.GUEST_FOOD, "");
        t8.addCriterion(CriterionName.GUEST_FOOD, "");
        t9.addCriterion(CriterionName.GUEST_FOOD, "");
        t10.addCriterion(CriterionName.GUEST_FOOD, "");
        t7.addCriterion(CriterionName.HOST_FOOD, "");
        t8.addCriterion(CriterionName.HOST_FOOD, "");
        t9.addCriterion(CriterionName.HOST_FOOD, "");
        t10.addCriterion(CriterionName.HOST_FOOD, "");
    }

    @Test
    public void testWeight(){
        assertEquals(8, AffectationUtil.weight(t4, t1, history));
        assertEquals(1010, AffectationUtil.weight(t5, t1, history));
        assertEquals(8, AffectationUtil.weight(t6, t1, history));
        assertEquals(8, AffectationUtil.weight(t4, t2, history));
        assertEquals(1008, AffectationUtil.weight(t5, t2, history));
        assertEquals(1010, AffectationUtil.weight(t6, t2, history));
        assertEquals(1010, AffectationUtil.weight(t4, t3, history));
        assertEquals(6, AffectationUtil.weight(t5, t3, history));
        assertEquals(1010, AffectationUtil.weight(t6, t3, history));
    }

    @Test
    public void testCompatibilityVsHobbies(){
        assertEquals(10, AffectationUtil.weight(t9, t7, history));
        assertEquals(10, AffectationUtil.weight(t10, t8, history));
        assertEquals(-90, AffectationUtil.weight(t7, t10, history));
        assertEquals(10, AffectationUtil.weight(t8, t9, history));
        assertEquals(910, AffectationUtil.weight(t10, t7, history));
        assertEquals(10, AffectationUtil.weight(t9, t7, history));
    }
}
