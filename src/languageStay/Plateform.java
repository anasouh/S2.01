package languageStay;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import languageStay.exceptions.WrongLineFormatException;

/**
 * <strong>Permet de créer un objet Platform </strong>
 * @author Desmee Nathan
 * @author Ouhdda Anas
 * @author Belguebli Rayane
 */

public class Plateform {

    private ArrayList<Teenager> promo = new ArrayList<>();

    /**
     * Supprime de la platforme un nombre de teenagers.
     * @param nbSupp Nombre de teenager à supprimer
     */
    public void supprimer(int nbSupp){
        int c = 0;
        for(Teenager t : promo){
            t.purgeInvalidRequierement();
            t.purgeIncoherentRequirement();
        }
        while(c < nbSupp && promo.size() > 0){
            int minCri = minimumCritere(promo);
            List<Teenager> aSupp = new ArrayList<>();
            for(Teenager etudiant : promo){
                if(etudiant.getNbCriterion() == minCri && c < nbSupp){
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
            t.purgeIncoherentRequirement();
        }
        while(c < nbSupp && promo.size() > 0){
            int minCri = minimumCritere(promo);
            List<Teenager> aSupp = new ArrayList<>();
            for(Teenager etudiant : promo){
                if(etudiant.getNbCriterion() == minCri && etudiant.getCountry() == pays && c < nbSupp){
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
     * @param teenager Teenager à ajouter $
     * @return true or false si le teenager est ajouté ou non
     */
    public boolean ajouter(Teenager teenager){
        if (!promo.contains(teenager)) return promo.add(teenager);
        return false;
    }

    /**
     * Ajoute à la plateforme des teenagers, à condition que ceux-ci n'y soient pas déjà.
     * @param teenagers Liste de Teenager à ajouter 
     */
    public void ajouter(List<Teenager> teenagers){
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
     * @return true or false si le teenager est présent
     */
    public boolean contains(Teenager teenager){
        return promo.contains(teenager);
    }

    /**
     * Supprimer un teenager de la plateforme si celu-ci y est présent. 
     * @oaram teenager le teenager ) supprimer.
     * @return true si le teenager a correctement été supprimé, false sinon.
     */
    public boolean supprimer(Teenager teenager) {
        return promo.remove(teenager);
    }

    /**
     * Retourne le nombre de teenagers dans la plateforme.
     *  @return un int qui défini la taille
     */
    public int size(){
        return promo.size();
    }

    /**
     * Importer des teenagers à partir d'un fichier CSV.
     * @param filename Nom du fichier CSV
     */
    public void importer(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int line = 0;
            while (br.ready()) {
                System.out.println(line);
                String s = br.readLine();
                if (line > 0) {
                    try {
                        System.out.println(s);
                        promo.add(Teenager.parse(s));
                    } catch (WrongLineFormatException e) {
                        System.out.println("Erreur à la ligne " + line + " : " + e.getMessage());
                        System.out.println("Poursuite de l'importation...");
                    }
                }
                line ++;
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier " + filename + " : " + e.getMessage());
        }
    }

    

    @Override
    public String toString() {
        return "Plateform [promo=" + promo + "]";
    }
}
