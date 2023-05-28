package languageStay;
import java.io.Serializable;
import java.time.Month;

import languageStay.exceptions.WrongCriterionTypeException;

/**
 * <strong>Permet de créer un objet Criterion </strong>
 * @author Desmee Nathan
 * @author Ouhdda Anas
 * @author Belguebli Rayane
 */

public class Criterion implements Serializable {
    private String value;
    private CriterionName label;

    /**
     * Crée un Criterion complètement spécifiée
     * @param label nom du critère
     * @param value valeur du critère
     */

    public Criterion(CriterionName label, String value){
        this.label = label;
        this.value = value;
    }

    
    /**
     * Vérifie si le critère est valide si oui retourne true
     * @return boolean true or false
     */

    public boolean isValid() throws WrongCriterionTypeException{
        if(this.label.getTYPE() == 'B' && !this.equals("yes") && !this.equals("no")){
            throw new WrongCriterionTypeException("Ce critère boolean présente une valeur incorrecte");
        }else if(this.label.getTYPE() == 'N' && !Criterion.isNumeric(this.value)){
            throw new WrongCriterionTypeException("Ce critère numerique présente une valeur incorrecte");
        }else if(this.label.getTYPE() == 'D'){
            String[] date = this.value.split("-");
            if(!Criterion.isNumeric(date[0]) || !Criterion.isMonth(date[1]) || !Criterion.isDays(date[2], date[1])){
                throw new WrongCriterionTypeException("Ce critère de type date présente une valeur incorrecte");
            }
        }
        return true;
    }

    /**
     * Retourne la valeur du criètre
     * @return la valeur
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Retourne le nom du critère
     * @return le critère
     */
    public CriterionName getLabel() {
        return this.label;
    }

    /**
     * Retourne true si la valeur du critère est égal à celui donné en paramètre
     * @param text le texte à verifier
     * @return boolean true or false
     */
    public boolean equals(String text){
        return this.value.equals(text);
    }

    /**
     * Vérifie si la chaine de caractère donné en paramètre est un chiffre si oui retourne true
     * @param s la chaine à vérifier
     * @return boolean true or false
     */
    public static boolean isNumeric(String s)
    {
        if (s == null || s.equals("")) {
            return false;
        }
 
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si la chaine de caractère donné en paramètre est un mois
     * @param s la chaine à vérifier
     * @return boolean true or false
     */
    public static boolean isMonth(String s){
        try {
            return Criterion.isNumeric(s) && Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Vérifie si la chaine de caractère donné en paramètre est un jour
     * @param s la chaine à vérifier pour le jour
     * @param month la chaine à vérifier pour le mois
     * @return boolean true or false
     */
    public static boolean isDays(String s, String month) {
        try {
            return Criterion.isNumeric(s) && Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= Month.of(Integer.parseInt(month)).length(false);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return getLabel().name();
    }
}
