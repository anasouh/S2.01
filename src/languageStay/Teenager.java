package languageStay;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import languageStay.exceptions.WrongCriterionTypeException;
import languageStay.exceptions.WrongLineFormatException;



/**
 * <strong>Permet de créer un objet Teenager </strong>
 * @author Desmee Nathan
 * @author Ouhdda Anas
 * @author Belguebli Rayane
 */


public class Teenager{

    private int id;
    private static int compteur = 1;
    private String name;
    private String firstname;
    private LocalDate birthday;
    private Country country;
    public static final String CSVHeader = "HOST;GUEST;REDIBITOIRE";
    
    Map<String, Criterion> requierments = new HashMap<String, Criterion>();

    static Map<Teenager, Teenager> history = new HashMap<>();


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
     * Récupérer le nom du Teenager
     * @return String le nom
     */
    public String getName(){ 
        return this.name;
    }

    /**
     * Vérifie la compatibilité entre 2 Teenagers
     * @param teenager le teenager pour la vérifier la compatibilité
     * @return boolean true or false
     */
    public boolean compatibleWithGuest(Teenager teenager){
        if(this.problemeAllergie(teenager)){
            return false;
        }
        else if(!this.peutNourrir(teenager)){
            return false;
        }
        else if(this.country == Country.FRANCE || teenager.getCountry() == Country.FRANCE){
            if(this.nbLoisirCommun(teenager) == 0){
                return false;
            }
        }
        return true;
    }


    /**
     * Vérifie si les valeurs des critères est valide et si oui les supprime
     */
    public void purgeInvalidRequierement(){
        ArrayList<String> supp = new ArrayList<String>();
        for(String c : requierments.keySet()){
            try{
                requierments.get(c).isValid();
            }catch(WrongCriterionTypeException e){
                supp.add(c);
            }
        }
        for(String c : supp){
            requierments.remove(c);
        }
    }

    /**
     * Vérifie si les valeurs des critères sont incohérents et si oui les supprime
     */
    public void purgeIncoherentRequirement(){
        if(this.criterionEquals("HOST_HAS_ANIMAL", "yes") && this.criterionEquals("GUEST_ANIMAL_ALLERGY", "yes")){
            requierments.remove("HOST_HAS_ANIMAL");
            requierments.remove("GUEST_ANIMAL_ALLERGY");
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
     * Ajoute un critère donné en paramètre
     * @param criterion le critère
     */
    public void addCriterion(Criterion criterion){
        requierments.put(criterion.toString(), criterion);
    }

    /**
     * Renvoie la valeur d'un critère
     * @param label nom du critère 
     * @return String la valeur du critère
     */
    public String getCriterion(CriterionName label){
        Criterion res = requierments.get(label.name());
        if (res != null) return res.getValue();
        return null;
    }

    /**
     * Renvoie le pays du Teenager
     * @return Country le pays du Teenager
     */
    public Country getCountry() {
        return country;
    }
    
    /**
     * Renvoie le nombre de critère d'un Teenager
     * @return int le nombre de critère
     */
    public int getNbCriterion(){
        return this.requierments.size();
    }


    /**
     * Renvoie true si le critère est égal à celui donné en paramètre 
     * @param nameCriterion nom du critère
     * @param value valeur du critère
     * @return boolean true or false
     */


    public boolean criterionEquals(String nameCriterion, String value){
        if(!this.requierments.containsKey(nameCriterion)){
            return false;
        }
        return this.requierments.get(nameCriterion).equals(value);
    }

    /**
     * Renvoie true si le teenager l 'habitude l'alimentaire compatible à celui donné en paramètre 
     * @param teen un Teenager
     * @return boolean true or false
     */
    public boolean peutNourrir(Teenager teen){
        if(teen.requierments.containsKey("GUEST_FOOD") && teen.criterionEquals("GUEST_FOOD", "")){
            return true;
        }
        if(!teen.requierments.containsKey("GUEST_FOOD") || !this.requierments.containsKey("HOST_FOOD")){
            return false;
        }
        ArrayList<String> ask = new ArrayList<>();
        ArrayList<String> give = new ArrayList<>();
        for (String s : teen.requierments.get("GUEST_FOOD").getValue().split(",")) {
            ask.add(s);
        }
        if(!this.criterionEquals("HOST_FOOD", "")){
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

    /**
     * Renvoie le nombre de loisirs communs entre le host et le guest
     * @param teen un Teenager
     * @return int nombre de loisirs communs
     */
    public int nbLoisirCommun(Teenager teen){
        if(!teen.requierments.containsKey("HOBBIES") || !this.requierments.containsKey("HOBBIES")){
            return 0;
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
        int nombre = 0;
        for (String s : ask) {
            if (give.indexOf(s) >= 0) {
                nombre++;
            }
        }
        return nombre;

    }

    /**
     * Vérifie si il y a un problème d'allergie entre un hôte et un guest
     * @param teenager un teenager
     * @return false si problème d'allergie, false sinon
     */
    public boolean problemeAllergie(Teenager teenager){
        if(this.criterionEquals("HOST_HAS_ANIMAL", "no") || teenager.criterionEquals("GUEST_ANIMAL_ALLERGY", "no")){
            return false;
        }
        return true;
    }

    /**
     * Créer une instance Teenager à partir d'une ligne de CSV. 
     * @param line une ligne de CSV
     */
    public static Teenager parse(String line) throws WrongLineFormatException {
        Teenager result = null;
        line += "aide";
        String[] data = line.split(";");
        if(data.length == 13) {
            String[] dateStr = data[3].split("-");
            LocalDate date = null;
            if(dateStr.length == 3){
                date = LocalDate.of(Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[2]));
            }
            result = new Teenager(data[1], data[0], date, Country.valueOf(data[2]));
            result.addCriterion(new Criterion(CriterionName.GUEST_ANIMAL_ALLERGY, data[4]));
            result.addCriterion(new Criterion(CriterionName.HOST_HAS_ANIMAL, data[5]));
            result.addCriterion(new Criterion(CriterionName.GUEST_FOOD, data[6]));
            result.addCriterion(new Criterion(CriterionName.HOST_FOOD, data[7]));
            result.addCriterion(new Criterion(CriterionName.HOBBIES, data[8]));
            result.addCriterion(new Criterion(CriterionName.GENDER, data[9]));
            result.addCriterion(new Criterion(CriterionName.PAIR_GENDER, data[10]));
            result.addCriterion(new Criterion(CriterionName.HISTORY, data[11]));
            return result;
        } else {
            throw new WrongLineFormatException();
        }
    }

    public String serialize(){
        String result = "" + this.firstname + ";" + this.name + ";" + this.country + ";";
        if (this.birthday != null) result += this.birthday.toString();
        result += ";";
        result += this.getCriterion(CriterionName.GUEST_ANIMAL_ALLERGY) + ";";
        result += this.getCriterion(CriterionName.HOST_HAS_ANIMAL) + ";";
        result += this.getCriterion(CriterionName.GUEST_FOOD) + ";";
        result += this.getCriterion(CriterionName.HOST_FOOD) + ";";
        result += this.getCriterion(CriterionName.HOBBIES) + ";";
        result += this.getCriterion(CriterionName.GENDER) + ";";
        result += this.getCriterion(CriterionName.PAIR_GENDER);
        result += this.getCriterion(CriterionName.HISTORY);
        return result;
    }

    public static void ResetCompteur(){
        Teenager.compteur = 0;
    }

    public static void addHistory(Teenager t1, Teenager t2){
        System.out.println(t1.getId() + "   " + t2.getId() );
        history.put(t1, t2);
    }

    public static int history(Teenager t1, Teenager t2){
        System.out.println(history.toString());
        System.out.println(t2.toString());
        System.out.println(history.get(t2));
        if(t1 != null && t2 != null && ((history.containsKey(t1) == true && history.containsValue(t2) == true) || (history.containsKey(t2) == true && history.containsValue(t1) == true))){
            if(history.get(t2).equals(t1) || history.get(t1).equals(t2)){
                if(t2.getCriterion(CriterionName.HISTORY).equals("same") && t1.getCriterion(CriterionName.HISTORY).equals("same")){
                    return -10;
                }
                else if(t2.getCriterion(CriterionName.HISTORY).equals("other") || t1.getCriterion(CriterionName.HISTORY).equals("other")){
                    return 4;
                }
                else{
                    return -5;
                }
            }
        }
        return 0;
    }

    /** 
     * Renvoie en chaîne de caratère l'id, le nom et prénom du teenager
     * @return une chaine de caratère 
    */

    @Override
    public String toString() {
        return id + "-" + name + "-" + firstname;
    }

    public int getId() {
        return id;
    }

    public static int getCompteur() {
        return compteur;
    }

    
}
