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
        }else if(this.label.getTYPE() == 'N'){
            return Criterion.isNumeric(this.value);
        }
        return true;
    }

    public String getValue() {
        return this.value;
    }

    public CriterionName getLabel() {
        return this.label;
    }

    public boolean equals(String text){
        return this.value.equals(text);
    }

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
}
