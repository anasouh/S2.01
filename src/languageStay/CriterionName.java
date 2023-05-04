package languageStay;

/**
 * <strong>Liste des critères disponible et leur type </strong>
 * @author Desmee Nathan
 * @author Ouhdda Anas
 * @author Belguebli Rayane
 */

public enum CriterionName {
    GUEST_ANIMAL_ALLERGY('B'),
    HOST_HAS_ANIMAL('B'),
    GUEST_FOOD('T'),
    HOST_FOOD('T'),
    HOBBIES('T'),
    GENDER('T'),
    PAIR_GENDER('T'),
    HISTORY('T');

    private final char TYPE;

    /**
     * Crée un type à un critère
     * @param type type du critère
     */
    private CriterionName(char type){
        this.TYPE = type;
    }

    /**
     * Renvoie le type du critère
     * @return boolean true or false
     */
    public char getTYPE() {
        return TYPE;
    }

}
