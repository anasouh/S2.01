package languageStay;
import java.util.ArrayList;

public class Criterion {
    private String value;
    private CriterionName label;

    public Criterion(CriterionName label, String value){
        this.label = label;
        this.value = value;
    }

    public boolean isValid(){
        if(this.label.getTYPE() == 'B'){
            return this.value.equals("yes") || this.value.equals("no");
        }
        else{
            return true;
        }
    }

    public String getValue() {
        return this.value;
    }

    public CriterionName getLabel() {
        return this.label;
    }

    public boolean allIn(String necessary){
        boolean allIn = true;
        ArrayList<String> necessaries = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (String s : necessary.replaceAll("\\s+","").split(",")) {
            necessaries.add(s);
        }
        for (String s : this.value.replaceAll("\\s+","").split(",")) {
            values.add(s);
        }

        for (String s : necessaries) {
            System.out.println(s);
            if (values.indexOf(s) < 0) {
                allIn = false;
            }
        }
        return allIn;

    }
}
