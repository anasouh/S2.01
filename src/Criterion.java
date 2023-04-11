public class Criterion {
    private String value;
    private CriterionName label;

    public Criterion(CriterionName label, String value){
        this.label = label;
        this.value = value;
    }

    public boolean isValid(){
        return;
    }
}
