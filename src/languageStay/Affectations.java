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
import fr.ulille.but.sae2_02.graphes.Arete;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Affectations implements Serializable {
    private Map<Teenager, Teenager> affectations;
    public static final String PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator;

    /**
     * Créer une HashMap de Teenagers.
     */
    public Affectations() {
        this.affectations = new HashMap<Teenager, Teenager>();
    }

    /**
     * Créer une HashMap de Teenagers avec des arretes.
     *  @param arretes arretes entre 2 Teenagers.
     */
    public Affectations(List<Arete<Teenager>> arretes) {
        this();
        for (Arete<Teenager> arete : arretes) {
            this.affecter(arete.getExtremite1(), arete.getExtremite2());
        }
    }

    /**
     * Affecte 2 Teenagers ayant déjà été correspondant.
     * @param teenager1
     * @param teenager2
     */
    public void affecter(Teenager teenager1, Teenager teenager2) {
        this.affectations.put(teenager1, teenager2);
    }

    /**
     * Dé-affecte un Teenager du Teenager courant.
     * @param teenager
     */
    public void desaffecter(Teenager teenager) {
        this.affectations.remove(teenager);
    }

    /**
     * Permet d'avoir le Teenager ayant été correspondant avec le Teenager courant.
     * @param teenager
     * @return le correspondant du Teenager courant
     */
    public Teenager get(Teenager teenager) {
        return this.affectations.get(teenager);
    }

    /**
     * Permet de savoir si un Teenager à déjà eu un correspondant.
     * @param teenager
     * @return true ou false
     */
    public boolean estAffecte(Teenager teenager) {
        return this.affectations.containsKey(teenager);
    }

    /**
     * Exporte une liste de Teenager ayant été correspondant.
     * @param filename
     * @return true ou false
     */
    public static boolean exporter(Affectations history, String filename) {
        try{//(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(Affectations.PATH + File.separator + filename)))) {
            File f = new File(Affectations.PATH + filename);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(history);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Permet d'importer une liste de Teenager ayant été correspondant
     * @param filename
     * @return une HashMap de Teenager et Teenager
     */
    public static Affectations importer(String filename) {
        Affectations result = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(Affectations.PATH + filename)))) {
            result = (Affectations) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch( ClassNotFoundException e){
            return null;
        }
        return result;
    }


    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<Teenager, Teenager> entry : this.affectations.entrySet()) {
            result += entry.getKey().toString() + " héberge " + entry.getValue().toString() + "\n";
        }
        return result;
    }

    /**
     * Permet de savoir si des Teenagers pouvant être associé on des préférences ou des contraintes lié à l'historique.
     * -10 si ils ont déjà été correspondant et aucun d'entres eux n'a mis "other" et au moins 1 à mis same
     * 1000 si ils ont déjà été correspondant et au moins a mis "other"
     * 0 si ils n'ont jamais été correspondant 
     * @param host le Teenager hôte
     * @param visitor le Teenager visiteur
     * @return le poids lié à l'historique entre un hôte est un visiteur
     */
    public int history(Teenager host, Teenager visitor){
        System.out.println(host);
        System.out.println(this.affectations.keySet());
        System.out.println(this.estAffecte(host));
        if(this.estAffecte(host)){
            if(this.get(host).equals(visitor)){
                if(host.criterionEquals("HISTORY","other") || visitor.criterionEquals("HISTORY","other") ){
                    return 1000;
                }
                if(host.criterionEquals("HISTORY","same")  || visitor.criterionEquals("HISTORY","same") ){
                    return -10;
                }
            }
        }
        /* else if(this.estAffecte(visitor)){
            if(this.get(visitor).equals(host)){
                if(host.getCriterion(CriterionName.HISTORY).equals("same") || visitor.getCriterion(CriterionName.HISTORY).equals("same")){
                    return -10;
                }
                if(host.getCriterion(CriterionName.HISTORY).equals("other") || visitor.getCriterion(CriterionName.HISTORY).equals("other")){
                    return 1000;
                }
            }
        } */
        return 0;        
    }
}
