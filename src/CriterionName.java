public enum CriterionName {
    GUEST_ANIMAL_ALLERGIE('B'),
    HOST_HAS_ANIMAL('B'),
    GUEST_FOOD('T'),
    HOST_FOOD('T'),
    HOBBIES('T'),
    GENDER('T'),
    PAIR_GENDER('T'),
    HISTORY('T');

    private final char TYPE;

    private CriterionName(char type){
        this.TYPE = type;
    }

    public char getTYPE() {
        return TYPE;
    }

}
