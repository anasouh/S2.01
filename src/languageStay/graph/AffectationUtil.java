package languageStay.graph;
/**
 * <strong>Affecte des teenagers visiteur à des teenagers d'autres pays hôte </strong>
 * @author Desmee Nathan
 * @author Ouhdda Anas
 * @author Belguebli Rayane
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import languageStay.*;

public class AffectationUtil {
    /** Calcule le poids de l’arête entre host et visitor dans le graphe modèle.
    * @param host L'adolescent hôte
    * @param visitor L'adolescent visiteur
    * @return le poids plus il est bas plus ils sont compatibles
    */
    public static double weight (Teenager host, Teenager visitor) {
        double result = 10.0;
        if (!host.compatibleWithGuest(visitor)) result = result + 100;
        result = result - (2 * host.nbLoisirCommun(visitor));
        return result;
    }

    public static void main(String[] args) {
        Teenager t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;
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
        t8.addCriterion(CriterionName.HOST_FOOD, "");
        t9.addCriterion(CriterionName.HOST_FOOD, "");
        t10.addCriterion(CriterionName.HOST_FOOD, "");

        GrapheNonOrienteValue<Teenager> graphe = new GrapheNonOrienteValue<Teenager>();
        List<Teenager> baguette = new ArrayList<Teenager>();
        List<Teenager> spaghetti = new ArrayList<Teenager>();
        baguette.add(t1);
        baguette.add(t2);
        baguette.add(t3);
        spaghetti.add(t4);
        spaghetti.add(t5);
        spaghetti.add(t6);
        for(Teenager sommet : baguette){
            graphe.ajouterSommet(sommet);
        }
        for(Teenager sommet : spaghetti){
            graphe.ajouterSommet(sommet);
        }
        for(Teenager sommet1 : baguette){
            for(Teenager sommet2 : spaghetti){
                graphe.ajouterArete(sommet2, sommet1, AffectationUtil.weight(sommet2, sommet1));
            }
        }

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

        CalculAffectation<Teenager> calculEx1 = new CalculAffectation<Teenager>(graphe, spaghetti, baguette);
        List<fr.ulille.but.sae2_02.graphes.Arete<Teenager>> listeEx1 = calculEx1.calculerAffectation();
        System.out.println("BaguetteToSpaghetti");
        System.out.println(listeEx1);
        System.out.println(calculEx1.getCout());
        

        System.out.println();
        System.out.println();

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