import java.time.LocalDate;
import java.util.ArrayList;

public class Teenager extends Criterion{

    private int id;
    private static int compteur = 0;
    private String name;
    private String firstname;
    private LocalDate birthday;
    private Gender gender;
    private Country country;
    
    private ArrayList<Criterion> requierments = new ArrayList<>();

    public Teenager(String name, String firstname, Gender gender, LocalDate birthday, Country country){
        this.name = name;
        this.firstname = firstname;
        this.gender = gender;
        this.birthday = birthday;
        this.country = country;
        this.id = compteur +1;
    }

    public boolean compatibleWithGuest(Teenager teenager){
        return;
    }

    public void purgeInvalidRequierement(){

    } 

    public void addCriterion(CriterionName label, String value){
        Criterion criterion =  super(label, value);
        requierments.add(criterion);
    }


}