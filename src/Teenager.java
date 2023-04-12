import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Teenager{

    private int id;
    private static int compteur = 1;
    private String name;
    private String firstname;
    private LocalDate birthday;
    private Country country;
    
    Map<String, Criterion> requierments = new HashMap<String, Criterion>();

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
        else if(this.country == Country.FRANCE){
            if(!requierments.get("HOBBIES").isIn(teenager.requierments.get("HOBBIES").getValue())){
                return false;
            }
        }
        return true;
    }

    public void purgeInvalidRequierement(){
       for(String c : requierments.keySet()){
            if(!requierments.get(c).isValid()){
                requierments.remove(c);
            }
       }
    } 

    public void addCriterion(CriterionName label, String value){
        Criterion criterion =  new Criterion(label, value);
        requierments.put(label.name(), criterion);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teenager other = (Teenager) obj;
        if (id != other.id)
            return false;
        return true;
    }


}