
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Plateform {
    
    private Map<Teenager, Teenager> plateform = new HashMap<Teenager, Teenager>();
    private ArrayList<Teenager> promo = new ArrayList<>();

    public void clear(int i){
        int c = 0;
        for(Teenager t : promo){
            t.purgeInvalidRequierement();
        }
    }
}
