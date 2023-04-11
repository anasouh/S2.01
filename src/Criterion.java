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
        return value;
    }

    public CriterionName getLabel() {
        return label;
    }

    public boolean allIn(String necessary){
        ArrayList<String> necessaries = Criterion.separation(necessary);
        ArrayList<String> values = Criterion.separation(this.value);
        for(String s : necessaries){
            if(values.indexOf(s) < 0){
                return false;
            }
        }
        return true;
    }
    
    public boolean isIn(String necessary){
        ArrayList<String> necessaries = Criterion.separation(necessary);
        ArrayList<String> values = Criterion.separation(this.value);
        for(String s : necessaries){
            if(values.indexOf(s) >= 0){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> separation(String txt){
        ArrayList<String> s = new ArrayList<>();
        String mot ="";
        for(int i=0 ; i< txt.length(); i++){
            if(txt.charAt(i) != ','){
                mot += txt.charAt(i);
            }
            else{
                s.add(mot);
                mot = "";
            }
        }
        if(!mot.equals("")){
            s.add(mot);
        }
        return s;
    }
}
