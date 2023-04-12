package languageStay;
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
            if(!requierments.get("HOBBIES").allIn(teenager.requierments.get("HOBBIES").getValue())){
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

    public String getCriterion(CriterionName label){
        Criterion res = requierments.get(label.name());
        if (res != null) return res.getValue();
        return null;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Country getCountry() {
        return country;
    }

    public Map<String, Criterion> getRequierments() {
        return requierments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
}