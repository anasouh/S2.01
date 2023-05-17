package languageStay.graph;
import java.util.ArrayList;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class TPAffectation {
    public static void main(String[] args) {
        GrapheNonOrienteValue<String> graphe = new GrapheNonOrienteValue<String>();
        List<String> partie1 = new ArrayList<String>();
        List<String> partie2 = new ArrayList<String>();
        partie1.add("A");
        partie1.add("B");
        partie1.add("C");
        partie1.add("D");
        partie2.add("W");
        partie2.add("X");
        partie2.add("Y");
        partie2.add("Z");
        int[][] matrice = new int[][]{{13, 4, 7, 6},
                                      {1, 11, 5, 4},
                                      {6, 7, 2, 8},
                                      {1, 3, 5, 9}};
        for(String sommet : partie1){
            graphe.ajouterSommet(sommet);
        }
        for(String sommet : partie2){
            graphe.ajouterSommet(sommet);
        }
        for(String sommet1 : partie1){
            for(String sommet2 : partie2){
                graphe.ajouterArete(sommet1, sommet2, matrice[partie1.indexOf(sommet1)][partie2.indexOf(sommet2)]);
            }
        }
        CalculAffectation<String> calcul = new CalculAffectation<String>(graphe, partie1, partie2);
        List<Arete<String>> liste = calcul.calculerAffectation();
        System.out.println(liste);
        System.out.println(calcul.getCout());
    }
}