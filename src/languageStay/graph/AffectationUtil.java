package languageStay.graph;
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
    */
    public static double weight (Teenager host, Teenager visitor) {
        double result = 10.0;
        if (!host.compatibleWithGuest(visitor)) result = result + 100;
        result = result - (2 * host.nbLoisirCommun(visitor));
        return result;
    }

    public static void main(String[] args) {
        Teenager t1, t2, t3, t4, t5, t6;
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
        CalculAffectation<Teenager> calcul = new CalculAffectation<Teenager>(graphe, baguette, spaghetti);
        List<fr.ulille.but.sae2_02.graphes.Arete<Teenager>> liste = calcul.calculerAffectation();
        System.out.println(liste);
        System.out.println(calcul.getCout());
    }
}