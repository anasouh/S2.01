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
        String[] necessariesTab = necessary.split(",");
        String[]valuesTab = this.value.split(",");
        System.out.println(necessariesTab.toString());
        System.out.println(valuesTab.toString());
        ArrayList<String> necessaries = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        for(int i = 0; i<necessariesTab.length; i++){
            necessaries.add(necessariesTab[i]);
        }
        for(int j = 0; j<valuesTab.length; j++){
            necessaries.add(valuesTab[j]);
        }
        for(String s : necessaries){
            if(values.indexOf(s) < 0){
                return false;
            }
        }
        System.out.println(necessaries.toString());
        System.out.println(values.toString());
        return true;
    }
    
    public boolean isIn(String necessary){
        String[] necessariesTab = necessary.split(",");
        String[]valuesTab = this.value.split(",");
        System.out.println(necessariesTab.toString());
        System.out.println(valuesTab.toString());
        ArrayList<String> necessaries = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        for(int i = 0; i<necessariesTab.length; i++){
            necessaries.add(necessariesTab[i]);
        }
        for(int j = 0; j<valuesTab.length; j++){
            necessaries.add(valuesTab[j]);
        }
        for(String s : necessaries){
            if(values.indexOf(s) >= 0){
                return true;
            }
        }
        return false;
    }

    

    /*public static ArrayList<String> separation(String txt){
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
    }*/

    public static void main(String args[]){

        Criterion c1, c2;
        c1 = new Criterion(CriterionName.HOST_FOOD, "vegetarian, fff");
        c2 = new Criterion(CriterionName.GUEST_FOOD, "vegetarian, nonuts");
        c1.allIn(c2.getValue());

    }
}
