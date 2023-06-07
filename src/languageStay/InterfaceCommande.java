package languageStay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import languageStay.graph.AffectationUtil;

public class InterfaceCommande {
    
    private static Scanner scan = new Scanner(System.in);
    public static String teenList = System.getProperty("user.dir") + File.separator + "res" + File.separator + "teenagersData.csv";
    public static String affList = System.getProperty("user.dir") + File.separator + "res" + File.separator + "affectationData.csv";

    private static char ecouterChar(){
        System.out.print("Veuillez entrer 1 caractère : ");
        String c = scan.next();
        while(c.length() != 1){
            System.out.println("invalide");
            System.out.print("Veuillez entrer 1 caractère : ");
            c = scan.next();
        }
        return c.charAt(0);
    }

    private static void ajouterEtu(){
        clear();
        List<String> liste = new ArrayList<>();
        System.out.println("Ajouter étudiant :");
        String[] header = Plateform.CSVImportHeader.split(";");
        for(int i = 0; i < header.length; i++){
            System.out.print("Veuillez la valeur qui coresspond au critère " + header[i] + ": ");
            String s = scan.next();
            if(s.equals("null")){
                s = "";
            }
            liste.add(s);
        }
        String newEtu = "";
        for(String s : liste){
            newEtu += s + ";";
        }
        newEtu = newEtu.substring(0, newEtu.length()-1);
        File file = new File(teenList);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write(newEtu);
            bw.newLine();
        }catch(Exception e){
            System.out.println("Erreur dans l'écriture du fichier");
        }
    }

    public static void tableauDeBord(){
        boolean continuer = true;
        while(continuer){
            clear();
            System.out.println("Tableau de bord :");
            System.out.println("- la commande q permet de quitter l'interface");
            System.out.println("- la commande d permet de définir les pondérations");
            System.out.println("- la commande t permet de gérer les étudiants");
            System.out.println("- la commande a permet d'observer les appariements");
            char c = ecouterChar();
            if(c == 'q'){
                continuer = false;
            }else if(c == 'd'){
                defPond();
            }else if(c == 't'){
                gesEtu();
            }else if(c == 'a'){
                Country[] pays = choixPays();
                gesApp(pays[0], pays[1]);
            }
        }
    }

    public static Country[] choixPays(){
        System.out.println("\n");
        Country[] pays = new Country[2];
        String host = "";
        while(!paysExist(host)){
            System.out.print("Veuillez rentrer le nom du pays hote en MAJUSCULE : ");
            host = scan.next();
        }
        String guest = "";
        while(!paysExist(guest)){
            System.out.print("Veuillez rentrer le nom du pays guest en MAJUSCULE : ");
            guest = scan.next();
        }
        pays[0] = Country.valueOf(host);
        pays[1] = Country.valueOf(guest);
        return pays;
    }

    public static boolean paysExist(String pays){
        for(Country c : Country.values()){
            if(c.name().equals(pays)){
                return true;
            }
        }
        return false;
    }

    public static void gesApp(Country host, Country guest){
        clear();
        Plateform plat = new Plateform();
        plat.importer(teenList);
        if(plat.getNbCountry(host) != plat.getNbCountry(guest)){
            System.out.println("Le nombre d'étudiants doit être identique pour les deux pays");
            System.out.println("Il y a actuellement " + plat.getNbCountry(host) + " pour le pays " + host);
            System.out.println("Il y a actuellement " + plat.getNbCountry(guest) + " pour le pays " + guest);
            System.out.println("Il est possible de modifier ça dans l'interface de gestion des étudiants");
            try{
                Thread.sleep(5000);
            }catch(Exception e){

            }
            return;
        }
        plat.exporter(affList, host, guest);
        Affectations affectations = new Affectations(AffectationUtil.affectation(plat.getPromo(), guest, host), host, guest);
        boolean continuer = true;
        while(continuer){
            clear();
            System.out.println("Affectations :");
            System.out.println(affectations);
            System.out.println("\nCommandes :");
            System.out.println("- la commande q permet de quitter l'interface");
            System.out.println("- la commande f permet de fixer un appariement");
            System.out.println("- la commande e permet d'éviter un appariement");
            char c = ecouterChar();
            if(c == 'q'){
                continuer = false;
            }else if(c == 'f'){
                appManuel(affectations, plat, 1);
            }else if(c == 'e'){
                appManuel(affectations, plat, 0);
            }
            plat.exporter(affList, host, guest);
        }
    }

    public static void appManuel(Affectations affectations, Plateform plateform, int n){
        clear();
        String mot = "";
        if(n == 0){
            mot = "eviter";
        }else if(n == 1){
            mot = "fixer";
        }
        Set<Teenager> hotes = affectations.getMap().keySet();
        Collection<Teenager> visiteurs = affectations.getMap().values();
        System.out.println(mot + " un appariement :");
        System.out.println("Voici la liste des hotes :");
        for(Teenager t : hotes){
            System.out.println(t);
        }
        System.out.println("Voici la liste des invités :");
        for(Teenager t : visiteurs){
            System.out.println(t);
        }
        int idHost = -1;
        int idGuest = -1;
        while(!containsId(hotes, idHost)){
            System.out.print("Entrez le numéro id de l'hote à " + mot + " : ");
            try{
                idHost = scan.nextInt();
            }catch(Exception e){

            }
        }
        while(!containsId(visiteurs, idGuest)){
            System.out.print("Entrez le numéro id du visiteur à " + mot + " : ");
            try{
                idGuest = scan.nextInt();
            }catch(Exception e){

            }
        }
        affectations.clear();
        plateform.getById(idHost).putFixerEviter(plateform.getById(idGuest), n);
        affectations.add(AffectationUtil.affectation(plateform.getPromo(), affectations.getGuest(), affectations.getHost()));
    }

    /* public static void fixerApp(Affectations affectations, Plateform plateform){
        Set<Teenager> hotes = affectations.getMap().keySet();
        Collection<Teenager> visiteurs = affectations.getMap().values();
        System.out.println("\nFixer un appariement :");
        System.out.println("Voici la liste des hotes :");
        for(Teenager t : hotes){
            System.out.println(t);
        }
        System.out.println("Voici la liste des invités :");
        for(Teenager t : visiteurs){
            System.out.println(t);
        }
        int idHost = -1;
        int idGuest = -1;
        while(!containsId(hotes, idHost)){
            System.out.print("Entrez le numéro id de l'hote à affecter : ");
            try{
                idHost = scan.nextInt();
            }catch(Exception e){

            }
        }
        while(!containsId(visiteurs, idGuest)){
            System.out.print("Entrez le numéro id du visiteur à affecter : ");
            try{
                idGuest = scan.nextInt();
            }catch(Exception e){
                
            }
        }
        affectations.clear();
        affectations.put(plateform.getById(idHost), plateform.getById(idGuest));
        plateform.removeById(idHost);
        plateform.removeById(idGuest);
        affectations.add(AffectationUtil.affectation(plateform.getPromo(), affectations.getGuest(), affectations.getHost()));
    } */

    public static boolean containsId(Collection<Teenager> liste, int id){
        for(Teenager t : liste){
            if(t.getID() == id){
                return true;
            }
        }
        return false;
    }

    public static void gesEtu(){
        Plateform plat = new Plateform();
        plat.importer(teenList);
        boolean continuer = true;
        while(continuer){
            clear();
            System.out.println("Liste des étudiants :");
            for(Teenager t : plat){
                System.out.println(t);
            }
            System.out.println("\nCommandes :");
            System.out.println("- la commande q permet de quitter l'interface");
            System.out.println("- la commande s permet de supprimer un étudiant précis");
            System.out.println("- la commande p permet de supprimer un certain nombre d'étudiants");
            System.out.println("- la commande m permet de modifier un étudiant");
            char c = ecouterChar();
            if(c == 'q'){
                continuer = false;
            }else if(c == 's'){
                suppEtu(plat);
            }else if(c == 'p'){
                etuSupp(plat);
            }else if(c == 'm'){
                modifEtu(plat);
            }
        }
    }

    public static void modifEtu(Plateform plat){
        clear();
        System.out.println("Liste des étudiants :");
        for(Teenager t : plat){
            System.out.println(t);
        }
        int idEtu = -1;
        while(!containsId(plat.getPromo(), idEtu)){
            System.out.print("Entrez le numéro id de l'étudiant à mofifier : ");
            try{
                idEtu = scan.nextInt();
            }catch(Exception e){

            }
        }
        Teenager teen = plat.getById(idEtu);
        suppEtuCSV(teen);
        etuModif(teen);
        File file = new File(teenList);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
            bw.write(teen.chaineCSV());
            bw.newLine();
        }catch(Exception e){
            System.out.println("Erreur dans l'écriture du fichier");
        }
    }

    public static void etuModif(Teenager teen){
        String[] header = Plateform.CSVImportHeader.split(";");
        for(String s : header){
            clear();
            System.out.println("Entrez une nouvelle valeur pour le critère " + s + " (ou rien si il faut laisser comme actuellement) :");
            if(s.equals("FORENAME")){
                System.out.println("Valeur actuelle : " + teen.getFirstname());
                System.out.print("Nouvelle valeur : ");
                String chaine = scan.nextLine();
                if(!chaine.equals("")){
                    teen.setFirstname(chaine);
                }
            }else if(s.equals("NAME")){
                System.out.println("Valeur actuelle : " + teen.getName());
                System.out.print("Nouvelle valeur : ");
                String chaine = scan.nextLine();
                if(!chaine.equals("")){
                    teen.setName(chaine);
                }
            }else if(s.equals("BIRTH_DATE")){
                System.out.println("Valeur actuelle : " + teen.date());
                System.out.print("Nouvelle valeur : ");
                String chaine = scan.nextLine();
                if(!chaine.equals("")){
                    teen.setBirthday(chaine);
                }
            }else if(s.equals("COUNTRY")){
                System.out.println("Valeur actuelle : " + teen.getCountry());
                System.out.print("Nouvelle valeur : ");
                String chaine = scan.nextLine();
                if(!chaine.equals("")){
                    teen.setCountry(chaine);
                }
            }else{
                System.out.println("Valeur actuelle : " + teen.getCriterion(CriterionName.valueOf(s)));
                System.out.print("Nouvelle valeur : ");
                String chaine = scan.nextLine();
                if(!chaine.equals("")){
                    teen.setCriterion(s, chaine);
                }
            }
        }
    }

    public static void etuSupp(Plateform plat){
        int nb = -1;
        while(nb < 0){
            System.out.print("Entrez le nombre d'étudiants à supprimer : ");
            try{
                nb = scan.nextInt();
            }catch (Exception e){

            }
        }
        String country = "";
        System.out.print("Entrez l'éventuel pays d'où ils doivent être supprimés (null sinon) : ");
        country = scan.next();
        List<Teenager> liste = new ArrayList<>();
        if(paysExist(country)){
            liste = plat.supprimer(nb, Country.valueOf(country));
        }else{
            liste = plat.supprimer(nb);
        }
        for(Teenager t : liste){
            suppEtuCSV(t);
        }
    }

    public static void suppEtu(Plateform plat){
        clear();
        System.out.println("Liste des étudiants :");
        for(Teenager t : plat){
            System.out.println(t);
        }
        int idEtu = -1;
        while(!containsId(plat.getPromo(), idEtu)){
            System.out.print("Entrez le numéro id de l'étudiant à supprimer : ");
            try{
                idEtu = scan.nextInt();
            }catch(Exception e){

            }
        }
        Teenager teen = plat.getById(idEtu);
        suppEtuCSV(teen);
        plat.removeById(idEtu);
    }

    public static void suppEtuCSV(Teenager teen){
        List<String> fichier = new ArrayList<>();
        fichier.add(Plateform.CSVImportHeader);
        try(BufferedReader br = new BufferedReader(new FileReader(new File(teenList)))){
            br.readLine();
            while(br.ready()){
                String line = br.readLine();
                Teenager t = Teenager.parse(line, fichier.get(0));
                if(!t.equals(teen)){
                    fichier.add(line);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(teenList)))){
            for(String s : fichier){
                bw.write(s);
                bw.newLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void defPond(){
        clear();
        System.out.println("Changement pondérations :");
        System.out.print("Indiquez la pondération d'un critère rédhibitoire : ");
        try{
            AffectationUtil.redhibitoire = scan.nextInt();
        }catch(Exception e){

        }
        System.out.print("Indiquez la pondération d'un critère préférentiel : ");
        try{
            AffectationUtil.pref = scan.nextInt();
        }catch(Exception e){

        }
    }

    public static void main(String[] args) {
        boolean continuer = true;
        while(continuer){
            clear();
            System.out.println("Menu principal :");
            System.out.println("- la commande q permet de quitter l'interface");
            System.out.println("- la commande a permet d'ajouter un étudiant");
            System.out.println("- la commande t permet d'afficher la tableau de bord");
            char c = ecouterChar();
            if(c == 'q'){
                continuer = false;
            }else if(c == 'a'){
                ajouterEtu();
            }else if(c == 't'){
                tableauDeBord();
            }
        }
        System.out.println("Salut, bisous");
        scan.close();
    }

    public static void clear()
	{
        final String ESC = "\033[";
        System.out.print (ESC + "2J");
        System.out.print (ESC + "0;0H");
        System.out.flush();
	}

}
