package languageStay;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.ModuleElement.RequiresDirective;

public class Teenager{

    private int id;
    private static int compteur = 1;
    private String name;
    private String firstname;
    private LocalDate birthday;
    private Country country;
    
    Map<String, Criterion> requierments = new HashMap<String, Criterion>();

    Map<Sejour, Teenager> history = new HashMap<Sejour, Teenager>();

    public Teenager(String name, String firstname, LocalDate birthday, Country country){
        this.name = name;
        this.firstname = firstname;
        this.birthday = birthday;
        this.country = country;
        this.id = compteur;
        Teenager.compteur ++;
    }

    public boolean compatibleWithGuest(Teenager teenager){
        if(requierments.get("HOST_HAS_ANIMAL").getValue().equals("yes") 
        && teenager.requierments.get("GUEST_ANIMAL_ALLERGY").getValue().equals("yes")){
            return false;
        }
        else if(!requierments.get("HOST_FOOD").allIn(teenager.requierments.get("GUEST_FOOD").getValue()) ){
            return false;
        }
        else if(this.country == Country.FRANCE || teenager.getCountry() == Country.FRANCE){
            if(!requierments.get("HOBBIES").isIn(teenager.requierments.get("HOBBIES").getValue())){
                return false;
            }
        }else if(history.values().contains(teenager) && requierments.get("HISTORY").getValue().equals("other")){
            return false;
        }else if(teenager.history.values().contains(this) && teenager.requierments.get("HISTORY").getValue().equals("other")){
            return false;
        }
        return true;
    }

    public void purgeInvalidRequierement(){
        if(requierments.get("HOST_HAS_ANIMAL").getValue().equals("yes") && requierments.get("GUEST_ANIMAL_ALLERGY").getValue().equals("yes")){
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
}