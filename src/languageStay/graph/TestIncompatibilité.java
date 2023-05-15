package languageStay.graph;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import languageStay.*;


public class TestIncompatibilité {

    public static void main(String[] args) {
        Teenager t7, t8, t9, t10;

        t7 = new Teenager("A", "A", LocalDate.now(), Country.ITALY);
        t8 = new Teenager("B", "B", LocalDate.now(), Country.ITALY);
        t9 = new Teenager("C", "C", LocalDate.now(), Country.GERMANY);
        t10 = new Teenager("D", "D", LocalDate.now(), Country.GERMANY);

        t7.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t8.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t9.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t10.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");

        t7.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t8.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t9.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t10.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");

        t7.addCriterion(CriterionName.HOBBIES, "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx;yes;yes");
        t8.addCriterion(CriterionName.HOBBIES, "");
        t9.addCriterion(CriterionName.HOBBIES, "");
        t10.addCriterion(CriterionName.HOBBIES, "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx;yes;yes");

        t7.addCriterion(CriterionName.GUEST_FOOD, "");
        t8.addCriterion(CriterionName.HOST_FOOD, "");
        t9.addCriterion(CriterionName.HOST_FOOD, "");
        t10.addCriterion(CriterionName.HOST_FOOD, "");


        GrapheNonOrienteValue<Teenager> grapheSpaghettiToManchaft = new GrapheNonOrienteValue<Teenager>();
        GrapheNonOrienteValue<Teenager> grapheManchaftToSpaghetti = new GrapheNonOrienteValue<Teenager>();
        List<Teenager> spaghetti2 = new ArrayList<Teenager>();
        List<Teenager> manchaft = new ArrayList<Teenager>();
        spaghetti2.add(t7);
        spaghetti2.add(t8);
        manchaft.add(t9);
        manchaft.add(t10);

        for(Teenager sommet : spaghetti2){
            grapheSpaghettiToManchaft.ajouterSommet(sommet);
            grapheManchaftToSpaghetti.ajouterSommet(sommet);
        }
        for(Teenager sommet : manchaft){
            grapheSpaghettiToManchaft.ajouterSommet(sommet);
            grapheManchaftToSpaghetti.ajouterSommet(sommet);
        }
        for(Teenager sommet1 : manchaft){
            for(Teenager sommet2 : spaghetti2){
                grapheSpaghettiToManchaft.ajouterArete(sommet2, sommet1, AffectationUtil.weight(sommet1, sommet2));
            }
        }
        for(Teenager sommet1 : spaghetti2){
            for(Teenager sommet2 : manchaft){
                grapheManchaftToSpaghetti.ajouterArete(sommet2, sommet1, AffectationUtil.weight(sommet1, sommet2));
            }
        }

        CalculAffectation<Teenager> calcul = new CalculAffectation<Teenager>(grapheSpaghettiToManchaft, manchaft, spaghetti2);
        List<fr.ulille.but.sae2_02.graphes.Arete<Teenager>> liste = calcul.calculerAffectation();
        System.out.println("SpaghettiToManchaft");
        System.out.println(liste);
        System.out.println(calcul.getCout());
        
        System.out.println();
        System.out.println();

        CalculAffectation<Teenager> calcul2 = new CalculAffectation<Teenager>(grapheManchaftToSpaghetti, spaghetti2, manchaft);
        List<fr.ulille.but.sae2_02.graphes.Arete<Teenager>> liste2 = calcul2.calculerAffectation();
        System.out.println("ManchaftToSpaghetti");
        System.out.println(liste2);
        System.out.println(calcul2.getCout());
    }
    
}
