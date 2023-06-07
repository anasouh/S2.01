package languageStay;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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


public class Teenager implements Serializable{

    private final int ID;
    private static int compteur = 1;
    private String name;
    private String firstname;
    private LocalDate birthday;
    private Country country;
    
    Map<String, Criterion> requierments;

    private Map<Teenager, Integer> fixerEviter = new HashMap<>();

    /**
     * Crée un Teenager complètement spécifiée
     * @param name nom du Teenager
     * @param firstname prénom du Teenager
     * @param birthday date d'anniversaire du Teenager
     * @param country pays du Teenager
     */
    public Teenager(String name, String firstname, LocalDate birthday, Country country){
        this(name, firstname, birthday, country, new HashMap<String, Criterion>());
    }

    /**
     * Crée un Teenager complètement spécifiée
     * @param name nom du Teenager
     * @param firstname prénom du Teenager
     * @param birthday date d'anniversaire du Teenager
     * @param country pays du Teenager
     * @param crit critère du Teenager
     */
    public Teenager(String name, String firstname, LocalDate birthday, Country country, Map<String, Criterion> crit){
        this.requierments = crit;
        this.name = name;
        this.firstname = firstname;
        this.birthday = birthday;
        this.country = country;
        this.ID = compteur;
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
     * @return un Teenager
     */
    public static Teenager parse(String line, String header) throws WrongLineFormatException {
        Teenager result = null;
        line += ";aide";
        String[] data = line.split(";");
        String[] head = header.split(";");
        String firstname = null;
        String name = null;
        Country country = null;
        LocalDate birthDate = null;
        Map<String, Criterion> crit = new HashMap<String, Criterion>();
        if(data.length == 13) {
            for(int i = 0; i < 12; i++){
                if(head[i].equals("FORENAME")){
                    firstname = data[i];
                }else if(head[i].equals("NAME")){
                    name = data[i];
                }else if(head[i].equals("COUNTRY")){
                    country = Country.valueOf(data[i].toUpperCase());
                }else if(head[i].equals("BIRTH_DATE")){
                    String[] dateStr = data[i].split("-");
                    if(dateStr.length == 3){
                        birthDate = LocalDate.of(Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[2]));
                    }
                }else{
                    crit.put(head[i], new Criterion(CriterionName.valueOf(head[i]), data[i]));
                }
            }
            result = new Teenager(name, firstname, birthDate, country, crit);
            return result;
        } else {
            throw new WrongLineFormatException();
        }
    }

    /**
     * Affiche les informations d'un Teenager.
     * @return une chaîne avec les informations.
     */
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

    /**
     * Remet le compteur à zéro
     */
    public static void ResetCompteur(){
        Teenager.compteur = 0;
    }

    @Override
    public String toString() {
        return ID + "-" + name + "-" + firstname + "-\"" + birthday + "\"-" + country;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        return result;
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
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (firstname == null) {
            if (other.firstname != null)
                return false;
        } else if (!firstname.equals(other.firstname))
            return false;
        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;
        if (country != other.country)
            return false;
        return true;
    }

    public int getID() {
        return ID;
    }

    public Map<Teenager, Integer> getFixerEviter() {
        return fixerEviter;
    }

    public void putFixerEviter(Teenager t, int i){
        this.fixerEviter.put(t, i);
    }

    public int getFixerEviter(Teenager t){
        return this.fixerEviter.get(t);
    }

    public boolean pairGender(Teenager t){
        if((this.getCriterion(CriterionName.PAIR_GENDER) != null && t.getCriterion(CriterionName.GENDER) != null) ||
        (t.getCriterion(CriterionName.PAIR_GENDER) != null && this.getCriterion(CriterionName.GENDER) != null)){
            
            if(this.getCriterion(CriterionName.PAIR_GENDER).equals(t.getCriterion(CriterionName.GENDER)) || 
            t.getCriterion(CriterionName.PAIR_GENDER).equals(this.getCriterion(CriterionName.GENDER))){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public String date(){
        return "" + this.birthday.getYear() + "-" + this.birthday.getMonthValue() + "-" + this.birthday.getDayOfMonth();
    }

    public String chaineCSV(){
        String[] header = Plateform.CSVImportHeader.split(";");
        String res = "";
        for(String s : header){
            if(s.equals("FORENAME")){
                res += this.firstname + ";";
            }else if(s.equals("NAME")){
                res += this.name + ";";
            }else if(s.equals("BIRTH_DATE")){
                res += this.date() + ";";
            }else if(s.equals("COUNTRY")){
                res += this.country + ";";
            }else if(this.requierments.containsKey(s)){
                res += this.requierments.get(s).getValue() + ";";
            }else{
                res += ";";
            }
        }
        return res.substring(0, res.length()-1);
    }

    public String getFirstname() {
        return firstname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setBirthday(String birthday) {
        String[] dates = birthday.split("-");
        if(dates.length == 3){
            this.birthday = LocalDate.of(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]), Integer.valueOf(dates[2]));
        }else{
            this.birthday = null;
        }
    }

    public void setCountry(String country) {
        this.country = Country.valueOf(country);
    }

    public void setCriterion(String critName, String value){
        if(!this.requierments.containsKey(critName)){
            this.addCriterion(CriterionName.valueOf(critName), value);
        }else{
            this.requierments.get(critName).setValue(value);
        }
    }
}
