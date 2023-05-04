package languageStay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Plateform {

    private ArrayList<Teenager> promo = new ArrayList<>();

    public void supprimer(int i){
        int c = 0;
        for(Teenager t : promo){
            t.purgeInvalidRequierement();
        }
        while(c < i && promo.size() > 0){
            int minCri = minimumCritere(promo);
            for(Teenager etudiant : promo){
                if(etudiant.getNbCriterion() == minCri){
                    c++;
                    promo.remove(etudiant);
                }
            }
        }
    }

    public void supprimer(int i, Country pays){
        int c = 0;
        for(Teenager t : promo){
            t.purgeInvalidRequierement();
        }
        while(c < i && promo.size() > 0){
            int minCri = minimumCritere(promo);
            for(Teenager etudiant : promo){
                if(etudiant.getNbCriterion() == minCri && etudiant.getCountry() == pays){
                    c++;
                    promo.remove(etudiant);
                }
            }
        }
    }

    public int minimumCritere(ArrayList<Teenager> etudiants){
        int min = etudiants.get(0).getNbCriterion();
        for(Teenager etudiant : etudiants){
            if(min > etudiant.getNbCriterion()){
                min = etudiant.getNbCriterion();
            }
        }
        return min;
    }
}
