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
import java.io.File;
import java.io.Serializable;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import languageStay.*;

public class AffectationUtil implements Serializable {

    /** Calcule le poids de l’arête entre host et visitor dans le graphe modèle.
    * @param host L'adolescent hôte
    * @param visitor L'adolescent visiteur
    * @return le poids plus il est bas plus ils sont compatibles
    */
    public static double weight (Teenager host, Teenager visitor){
        Affectations history = AffectationUtil.chargerSejour(host.getCountry(), visitor.getCountry());
        double result = 10.0;
        int i = 0;
        if (!host.compatibleWithGuest(visitor)) result = result + 1000;
        i = i + (2 * host.nbLoisirCommun(visitor));
        if(i>10){
            i = 10;
        }
        result = result - i;
        result = result + history.history(host, visitor);
        return result;
    }

    /**
     * Charge un séjour via un fichier binaire.
     * @param host Pays hôte
     * @param guest Pays visiteur
     * @return une HashMap avec une combinaison de Teenager
     */
    public static Affectations chargerSejour(Country host, Country guest) {
        if (AffectationUtil.sejourExiste(host, guest)) return Affectations.importer(host + "_" + guest + ".bin");
        return new Affectations();
    }

    /**
     * Vérifie si le séjour entre 2 pays à déjà été fait cette année.
     * @param host Pays hôte
     * @param guest Pays visiteur
     * @return true ou false
     */
    public static boolean sejourExiste(Country host, Country guest) {
        File f = new File(Affectations.PATH + host + "_" + guest + ".bin");
        return f.exists();
    }

    public static List<Arete<Teenager>> affectation(List<Teenager> promo, Country guest, Country host){
        List<Teenager> teenHost = AffectationUtil.selectionPays(promo, host);
        List<Teenager> teenGuest = AffectationUtil.selectionPays(promo, guest);
        GrapheNonOrienteValue<Teenager> graphe = new GrapheNonOrienteValue<Teenager>();
        for(Teenager sommet : teenHost){
            graphe.ajouterSommet(sommet);
        }
        for(Teenager sommet : teenGuest){
            graphe.ajouterSommet(sommet);
        }
        for(Teenager sommet1 : teenHost){
            for(Teenager sommet2 : teenGuest){
                graphe.ajouterArete(sommet1, sommet2, AffectationUtil.weight(sommet1, sommet2));
            }
        }
        CalculAffectation<Teenager> calcul = new CalculAffectation<>(graphe, teenHost, teenGuest);
        return calcul.calculerAffectation();

    }

    private static List<Teenager> selectionPays(List<Teenager> promo, Country pays){
        List<Teenager> liste = new ArrayList<>();
        for(Teenager t : promo){
            if(t.getCountry() == pays){
                liste.add(t);
            }
        }
        return liste;
    }

    public static void main(String[] args) {
        Affectations history = new Affectations();
        Teenager.ResetCompteur();
        Teenager t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18;

        /*Graphes Version 1 */
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

        /*Graphes Version 2 */
        t11 = new Teenager("Jack", "Grealish", LocalDate.now(), Country.GERMANY);
        t12 = new Teenager("Phil", "Foden", LocalDate.now(), Country.GERMANY);
        t13 = new Teenager("Harry", "Kane", LocalDate.now(), Country.GERMANY);
        t14 = new Teenager("Kyle", "Walker", LocalDate.now(), Country.GERMANY);
        t15 = new Teenager("Pedri", "Gonzalez", LocalDate.now(), Country.SPAIN);
        t16 = new Teenager("Nico", "Williams", LocalDate.now(), Country.SPAIN);
        t17 = new Teenager("David", "DeGea", LocalDate.now(), Country.SPAIN);
        t18 = new Teenager("Xavi", "Hernandez", LocalDate.now(), Country.SPAIN);

        t11.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t12.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t13.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t14.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t15.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t16.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t17.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t18.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");

        t11.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t12.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t13.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t14.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t15.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t16.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t17.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t18.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");

        t11.addCriterion(CriterionName.HOBBIES, "");
        t12.addCriterion(CriterionName.HOBBIES, "");
        t13.addCriterion(CriterionName.HOBBIES, "");
        t14.addCriterion(CriterionName.HOBBIES, "");
        t15.addCriterion(CriterionName.HOBBIES, "");
        t16.addCriterion(CriterionName.HOBBIES, "");
        t17.addCriterion(CriterionName.HOBBIES, "");
        t18.addCriterion(CriterionName.HOBBIES, "");

        t11.addCriterion(CriterionName.GUEST_FOOD, "");
        t12.addCriterion(CriterionName.GUEST_FOOD, "");
        t13.addCriterion(CriterionName.GUEST_FOOD, "");
        t14.addCriterion(CriterionName.GUEST_FOOD, "");
        t15.addCriterion(CriterionName.GUEST_FOOD, "");
        t16.addCriterion(CriterionName.GUEST_FOOD, "");
        t17.addCriterion(CriterionName.GUEST_FOOD, "");
        t18.addCriterion(CriterionName.GUEST_FOOD, "");

        t11.addCriterion(CriterionName.HOST_FOOD, "");
        t12.addCriterion(CriterionName.HOST_FOOD, "");
        t13.addCriterion(CriterionName.HOST_FOOD, "");
        t14.addCriterion(CriterionName.HOST_FOOD, "");
        t15.addCriterion(CriterionName.HOST_FOOD, "");
        t16.addCriterion(CriterionName.HOST_FOOD, "");
        t17.addCriterion(CriterionName.HOST_FOOD, "");
        t18.addCriterion(CriterionName.HOST_FOOD, "");

        t11.addCriterion(CriterionName.HISTORY, "same");
        t12.addCriterion(CriterionName.HISTORY, "same");
        t13.addCriterion(CriterionName.HISTORY, "same");
        t14.addCriterion(CriterionName.HISTORY, "other");
        t15.addCriterion(CriterionName.HISTORY, "other");
        t16.addCriterion(CriterionName.HISTORY, "same");
        t17.addCriterion(CriterionName.HISTORY, "");
        t18.addCriterion(CriterionName.HISTORY, "");

        history.affecter(t18, t11);
        history.affecter(t13, t17);
        history.affecter(t14, t15);
        history.affecter(t16, t17);


        /*Graphes Version 1 */
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

        /*Graphes Version 2 */

        GrapheNonOrienteValue<Teenager> grapheHistory = new GrapheNonOrienteValue<Teenager>();
        List<Teenager> opel = new ArrayList<Teenager>();
        List<Teenager> salsa = new ArrayList<Teenager>();
        opel.add(t11);
        opel.add(t12);
        opel.add(t13);
        opel.add(t14);
        salsa.add(t15);
        salsa.add(t16);
        salsa.add(t17);
        salsa.add(t18);
        for(Teenager sommet : opel){
            grapheHistory.ajouterSommet(sommet);
        }
        for(Teenager sommet : salsa){
            grapheHistory.ajouterSommet(sommet);
        }
        for(Teenager sommet1 : opel){
            for(Teenager sommet2 : salsa){
                grapheHistory.ajouterArete(sommet2, sommet1, AffectationUtil.weight(sommet2, sommet1));
            }
        }

        CalculAffectation<Teenager> calculHistory = new CalculAffectation<Teenager>(grapheHistory, salsa, opel);
        List<fr.ulille.but.sae2_02.graphes.Arete<Teenager>> listeHistory = calculHistory.calculerAffectation();
        System.out.println("OpelToSalsa");
        System.out.println(listeHistory);
        System.out.println(calculHistory.getCout());


    }
}