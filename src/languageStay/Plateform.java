package languageStay;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.Arete;
import languageStay.exceptions.WrongLineFormatException;
import languageStay.graph.AffectationUtil;

/**
 * <strong>Permet de créer un objet Platform </strong>
 * @author Desmee Nathan
 * @author Ouhdda Anas
 * @author Belguebli Rayane
 */

public class Plateform implements Serializable{

    private ArrayList<Teenager> promo = new ArrayList<>();
    public static String CSVExportHeader = "HOST;GUEST;REDIBITOIRE";
    public static String CSVImportHeader;

    static{
        try(BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + File.separator + "res" + File.separator + "teenagersData.csv")))){
            CSVImportHeader = br.readLine();
        }catch(Exception e){
            CSVImportHeader = "ratio";
        }
    }

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
     * retourne l'ensemble des étudiants que contient la plateform
     * @return promo qui est une arrayList de teenagers
     */
    public ArrayList<Teenager> getPromo() {
        return promo;
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
            int line = 1;
            String header = br.readLine();
            while (br.ready()) {
                try {
                    promo.add(Teenager.parse(br.readLine(), header));
                } catch (WrongLineFormatException e) {
                    System.out.println("Erreur à la ligne " + line + " : " + e.getMessage());
                    System.out.println("Poursuite de l'importation...");
                }
                line ++;
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier " + filename + " : " + e.getMessage());
        }
    }

    /**
     * Réalise une affectation entre deux pays et l'exporte. Chaque ligne contient les deux personnes liées et si elle contiennent un critère rédhibitoire.
     * @param filename Nom du fichier CSV
     */
    public void exporter(String filename, Country host, Country guest) {
        List<Arete<Teenager>> liste = AffectationUtil.affectation(promo, guest, host);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename)))) {
            bw.write(Plateform.CSVExportHeader);
            bw.newLine();
            for (Arete<Teenager> a : liste) {
                String chaine = a.getExtremite1() + ";" + a.getExtremite2() + ";" + !a.getExtremite1().compatibleWithGuest(a.getExtremite2());
                bw.write(chaine);
                bw.newLine();
            }
            Plateform.nouveauSejour(liste, host, guest);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier " + filename + " : " + e.getMessage());
        }
    }

    /**
     * Affecte les adolescents pour un séjour. 
     * @param host Pays hôte
     * @param guest Pays visiteur
     * @return true ou false
     */
    public static boolean nouveauSejour(List<Arete<Teenager>> liste, Country host, Country guest) {
        Affectations affectations = new Affectations(liste);
        return Affectations.exporter(affectations, host + "_" + guest + ".bin");
    }

    @Override
    public String toString() {
        return "Plateform [promo=" + promo + "]";
    }

}
