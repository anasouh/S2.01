package languageStay;

/**
 * <strong>Permet de créer un objet Teenager </strong>
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

    public boolean compatibleWithGuest(Teenager teenager){
        if(this.criterionEquals("HOST_HAS_ANIMAL", "yes") 
        && teenager.criterionEquals("GUEST_ANIMAL_ALLERGY", "yes")){
            return false;
        }
        else if(!this.peutNourrir(teenager)){
            return false;
        }
        else if(this.country == Country.FRANCE || teenager.getCountry() == Country.FRANCE){
            if(!this.loisirCommun(teenager)){
                return false;
            }
        }else if(!this.history.isEmpty() && this.history.get(this.history.lastKey()) == teenager && this.requierments.get("HISTORY").equals("other")){
            return false;
        }else if(!teenager.history.isEmpty() && teenager.history.get(teenager.history.lastKey()) == this && teenager.requierments.get("HISTORY").equals("other")){
            return false;
        }
        return true;
    }

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

    public void addCriterion(CriterionName label, String value){
        Criterion criterion =  new Criterion(label, value);
        requierments.put(label.name(), criterion);
    }

    public String getCriterion(CriterionName label){
        Criterion res = requierments.get(label.name());
        if (res != null) return res.getValue();
        return null;
    }

    public Country getCountry() {
        return country;
    }
    
    public int getNbCriterion(){
        return this.requierments.size();
    }

    public void addHistory(int annee, Country pays, Teenager etudiant){
        Sejour s = new Sejour(annee, pays);
        this.history.put(s, etudiant);
    }

    public boolean criterionEquals(String nameCriterion, String value){
        if(!this.requierments.containsKey(nameCriterion)){
            return false;
        }
        return this.requierments.get(nameCriterion).equals(value);
    }

    public boolean peutNourrir(Teenager teen){
        if(!teen.requierments.containsKey("GUEST_FOOD") || !this.requierments.containsKey("HOST_FOOD")){
            return false;
        }
        ArrayList<String> ask = new ArrayList<>();
        ArrayList<String> give = new ArrayList<>();
        if(!teen.requierments.get("GUEST_FOOD").equals("")){
            for (String s : teen.requierments.get("GUEST_FOOD").getValue().split(",")) {
                ask.add(s);
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

    public boolean loisirCommun(Teenager teen){
        if(!teen.requierments.containsKey("HOBBIES") || !this.requierments.containsKey("HOBBIES")){
            return false;
        }
        ArrayList<String> ask = new ArrayList<>();
        ArrayList<String> give = new ArrayList<>();
        if(!teen.requierments.get("HOBBIES").equals("")){
            for (String s : teen.requierments.get("HOBBIES").getValue().split(",")) {
                ask.add(s);
            }
        }
        if(!this.requierments.get("HOBBIES").equals("")){
            for (String s : this.requierments.get("HOBBIES").getValue().split(",")) {
                give.add(s);
            }
        }
        for (String s : ask) {
            if (give.indexOf(s) >= 0) {
                return true;
            }
        }
        return false;

    }
}
