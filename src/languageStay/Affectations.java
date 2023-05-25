package languageStay;
/**
 * Classe sérialisable qui permet de gérer les affectations de Teenager
 * @author Desmee Nathan
 * @author Ouhdda Anas
 * @author Belguebli Rayane
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Affectations implements Serializable {
    private Map<Teenager, Teenager> affectations;
    public static final String PATH = "data/Affectations/";

    public Affectations() {
        this.affectations = new HashMap<Teenager, Teenager>();
    }

    public Affectations(List<fr.ulille.but.sae2_02.graphes.Arete<Teenager>> arretes) {
        this();
        for (fr.ulille.but.sae2_02.graphes.Arete<Teenager> arete : arretes) {
            this.affecter(arete.getExtremite1(), arete.getExtremite2());
        }
    }

    public void affecter(Teenager teenager1, Teenager teenager2) {
        this.affectations.put(teenager1, teenager2);
    }

    public void desaffecter(Teenager teenager) {
        this.affectations.remove(teenager);
    }

    public Teenager get(Teenager teenager) {
        return this.affectations.get(teenager);
    }

    public boolean estAffecte(Teenager teenager) {
        return this.affectations.containsKey(teenager);
    }

    public boolean exporter(String filename) {
        try (FileOutputStream fos = new FileOutputStream(Affectations.PATH + filename)) {
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static Affectations importer(String filename) {
        Affectations result = null;
        try (FileInputStream fis = new FileInputStream(Affectations.PATH + filename)) {
            ObjectInputStream in = new ObjectInputStream(fis);
            result = (Affectations) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<Teenager, Teenager> entry : this.affectations.entrySet()) {
            result += entry.getKey().toString() + " loge chez " + entry.getValue().toString() + "\n";
        }
        return result;
    }
}
