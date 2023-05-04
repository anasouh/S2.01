package languageStay;

/**
 * <strong>permet de créer un objet Teenager </strong>
 * @author Desmee Nathan, Ouhdda Anas, Belguebli Rayane
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Teenager{

    private int id;
    private static int compteur = 1;
    private String name;
    private String firstname;
    private LocalDate birthday;
    private Country country;
    
    Map<String, Criterion> requierments = new HashMap<String, Criterion>();

    TreeMap<Sejour, Teenager> history = new TreeMap<Sejour, Teenager>();


    /**
     * Crée un Teenager complètement spécifiée
     * @param name nom du Teenager
     * @param firstname prénom du Teenager
     * @param birthday date d'anniversaire du Teenager
     * @param country pays du Teenager
     */
    public Teenager(String name, String firstname, LocalDate birthday, Country country){
        this.name = name;
        this.firstname = firstname;
        this.birthday = birthday;
        this.country = country;
        this.id = compteur;
        Teenager.compteur ++;
    }

    /**
     * Vérifie la compatibilité entre 2 Teenagers
     * @param teenager le teenager pour la vérifier la compatibilité
     */
    public boolean compatibleWithGuest(Teenager teenager){
        if(this.criterionEquals("HOST_HAS_ANIMAL", "yes") 
        && teenager.criterionEquals("GUEST_ANIMAL_ALLERGY", "yes")){
            return false;
        }
        else if(!requierments.get("HOST_FOOD").allIn(teenager.requierments.get("GUEST_FOOD").getValue()) ){
            return false;
        }    /**
        * Renvoie true si le teenager n'a pas d'habitude l'alimentaire contraire à celui donné en paramètre 
        * @param necessary un Teenager
        */FRANCE || teenager.getCountry() == Country.FRANCE){
            if(!requierments.get("HOBBIES").isIn(teenager.requierments.get("HOBBIES").getValue())){
                return false;
            }
        }else if(!this.history.isEmpty() && this.history.get(this.history.lastKey()) == teenager && this.requierments.get("HISTORY").equals("other")){
            return false;
        }else if(!teenager.history.isEmpty() && teenager.history.get(teenager.history.lastKey()) == this && teenager.requierments.get("HISTORY").equals("other")){
            return false;
        }
        return true;
    }


    /**
     * Vérifie si les valeurs des critères est valide et si oui les supprime
     */
    public void purgeInvalidRequierement(){
        if(requierments.get("HOST_HAS_ANIMAL").equals("yes") && requierments.get("GUEST_ANIMAL_ALLERGY").equals("yes")){
            requierments.remove("HOST_HAS_ANIMAL");
            requierments.remove("GUEST_ANIMAL_ALLERGY");
        }
        ArrayList<String> supp = new ArrayList<String>();
        for(String c : requierments.keySet()){
            if(!requierments.get(c).isValid()){
                supp.add(c);
            }
        }
        for(String c : supp){
            requierments.remove(c);
        }
    }

    /**
     * Ajoute un critère donné en paramètre
     * @param label nom du critère
     * @param value valeur du critère
     */
    public void addCriterion(CriterionName label, String value){
        Criterion criterion =  new Criterion(label, value);
        requierments.put(label.name(), criterion);
    }


    /**
     * Renvoie la valeur d'un critère
     * @param label nom du critère 
     */
    public String getCriterion(CriterionName label){
        Criterion res = requierments.get(label.name());
        if (res != null) return res.getValue();
        return null;
    }

    /**
     * Renvoie le pays du Teenager
     */
    public Country getCountry() {
        return country;
    }
    
    /**
     * Renvoie le nombre de critère d'un Teenager
     */
    public int getNbCriterion(){
        return this.requierments.size();
    }

    /**
     * Ajoute les séjours précédents
     * @param annee l'année du séjour
     * @param pays le pays dans lequel le séjour a été fait
     * @param etudiant le Teenager ayant fait le séjour
     */
    public void addHistory(int annee, Country pays, Teenager etudiant){
        Sejour s = new Sejour(annee, pays);
        this.history.put(s, etudiant);
    }

    /**
     * Renvoie true si le critère est égal à celui donné en paramètre
     * @param nameCriterion nom du critère
     * @param value valeur du critère
     */
    public boolean criterionEquals(String nameCriterion, String value){
        if(!this.requierments.containsKey(nameCriterion)){
            return false;
        }
        return this.requierments.get(nameCriterion).equals(value);
    }

    /**
     * Renvoie true si le teenager n'a pas d'habitude l'alimentaire contraire à celui donné en paramètre 
     * @param teen un Teenager
     */
    public boolean peutNourrir(Teenager teen){
        if(!teen.requierments.containsKey("GUEST_FOOD") && !this.requierments.containsKey("HOST_FOOD")){
            return false;
        }
        ArrayList<String> ask = new ArrayList<>();
        ArrayList<String> give = new ArrayList<>();
        if(!teen.requierments.get("GUEST_FOOD").equals("")){
            for (String s : teen.requierments.get("GUEST_FOOD").getValue().split(",")) {
                ask.add(s);    /**
                * Renvoie true si le teenager n'a pas d'habitude l'alimentaire contraire à celui donné en paramètre 
                * @param necessary un Teenager
                */
            }
        }
        if(!this.requierments.get("HOST_FOOD").equals("")){
            for (String s : this.requierments.get("HOST_FOOD").getValue().split(",")) {
                give.add(s);
            }
        }
        for (String s : ask) {
            if (give.indexOf(s) < 0) {
                return false;
            }
        }
        return true;

    }


    public boolean isIn(String necessary){
        ArrayList<String> necessaries = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (String s : necessary.replaceAll("\\s+","").split(",")) {
            necessaries.add(s);
        }
        for (String s : this.values.replaceAll("\\s+","").split(",")) {
            values.add(s);
        }

        for (String s : necessaries) {
            if (values.indexOf(s) >= 0) {
                return true;
            }
        }
        return false;

    }
}