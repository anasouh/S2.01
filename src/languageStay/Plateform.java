package languageStay;
import java.util.ArrayList;
import java.util.List;

/**
 * <strong>Permet de créer un objet Platform </strong>
 * @author Desmee Nathan
 * @author Ouhdda Anas
 * @author Belguebli Rayane
 */

public class Plateform {

    private ArrayList<Teenager> promo = new ArrayList<>();

    /**
     * Supprime de la platforme les teenagers en trop par rapport au nombre entré en paramètre.
     * @param i Nombre maximum de teenager 
     */
    public void supprimer(int i){
        int c = 0;
        for(Teenager t : promo){
            t.purgeInvalidRequierement();
        }
        while(c < i && promo.size() > 0){
            int minCri = minimumCritere(promo);
            List<Teenager> aSupp = new ArrayList<>();
            for(Teenager etudiant : promo){
                if(etudiant.getNbCriterion() == minCri){
                    c++;
                    aSupp.add(etudiant);
                }
            }
            promo.removeAll(aSupp);
        }
    }

    /**
     * Supprime des teenagers d'un pays de la plateforme.
     * @param nbSupp nombre de teenagers du pays à supprimer
     * @param pays pays des teenagers à supprimer
     */
    public void supprimer(int nbSupp, Country pays){
        int c = 0;
        for(Teenager t : promo){
            t.purgeInvalidRequierement();
        }
        while(c < nbSupp && promo.size() > 0){
            int minCri = minimumCritere(promo);
            List<Teenager> aSupp = new ArrayList<>();
            for(Teenager etudiant : promo){
                if(etudiant.getNbCriterion() == minCri && etudiant.getCountry() == pays){
                    c++;
                    aSupp.add(etudiant);
                }
            }
            promo.removeAll(aSupp);
        }
    }

    /**
     * Renvoie le nombre de critère minimum 
     * @param etudiants liste des étudiants 
     * @return le nombre minimal de critère
     */
    public int minimumCritere(ArrayList<Teenager> etudiants){
        int min = etudiants.get(0).getNbCriterion();
        for(Teenager etudiant : etudiants){
            if(min > etudiant.getNbCriterion()){
                min = etudiant.getNbCriterion();
            }
        }
        return min;
    }

    /**
     * Ajoute à la plateforme un teenager, à condition que celui-ci n'y est pas déjà présent.
     * @param teenager Teenager à ajouter 
     */
    public boolean ajouter(Teenager teenager){
        if (!promo.contains(teenager)) return promo.add(teenager);
        return false;
    }

    /**
     * Ajoute à la plateforme des teenagers, à condition que ceux-ci n'y soient pas déjà.
     * @param teenagers Liste de Teenager à ajouter 
     */
    public void ajouter(ArrayList<Teenager> teenagers){
        for (Teenager t: teenagers){
            if (!promo.contains(t)) promo.add(t);
        }
    }

    /**
     * Ajoute à la plateforme des teenagers, à condition que ceux-ci n'y soient pas déjà.
     * @param teenagers Tableau de Teenager à ajouter 
     */
    public void ajouter(Teenager[] teenagers){
        for (Teenager t: teenagers){
            if (!promo.contains(t)) promo.add(t);
        }
    }

    /**
     * Retire tous les teenagers présents dans la plateforme.
     */
    public void clear(){
        promo.clear();
    }

    /**
     * Vérifie si la plateforme contient un teenager. 
     */
    public boolean contains(Teenager teenager){
        return promo.contains(teenager);
    }

    /**
     * Retourne le nombre de teenagers dans la plateforme.
     */
    public int size(){
        return promo.size();
    }
}
