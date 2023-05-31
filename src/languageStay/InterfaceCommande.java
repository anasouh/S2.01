package languageStay;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import languageStay.graph.AffectationUtil;

public class InterfaceCommande {
    
    private static Scanner scan = new Scanner(System.in);
    public static String teenList = System.getProperty("user.dir") + File.separator + "res" + File.separator + "teenagersData.csv";
    public static String affList = System.getProperty("user.dir") + File.separator + "res" + File.separator + "affectationData.csv";

    private static char ecouterChar(){
        System.out.print("Veuillez entrer 1 caractère : ");
        String c = scan.next();
        while(c.length() != 1){
            System.out.print("Veuillez entrer 1 caractère : ");
            c = scan.next();
        }
        return c.charAt(0);
    }

    private static void ajouterEtu(){
        List<String> liste = new ArrayList<>();
        System.out.println("\nAjouter étudiant :");
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
            System.out.println("\nTableau de bord :");
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
        Plateform plat = new Plateform();
        plat.importer(teenList);
        plat.exporter(affList, host, guest);
        Affectations affectations = new Affectations(AffectationUtil.affectation(plat.getPromo(), guest, host));
        System.out.println("\nAffectations :");
        System.out.println(affectations);
        boolean continuer = true;
        while(continuer){
            System.out.println("\nCommandes :");
            System.out.println("- la commande q permet de quitter l'interface");
            char c = ecouterChar();
            if(c == 'q'){
                continuer = false;
            }
        }
    }

    public static void gesEtu(){
        Plateform plat = new Plateform();
        plat.importer(teenList);
        System.out.println("\nListe des étudiants :");
        for(Teenager t : plat){
            System.out.println(t);
        }
        boolean continuer = true;
        while(continuer){
            System.out.println("\nCommandes :");
            System.out.println("- la commande q permet de quitter l'interface");
            char c = ecouterChar();
            if(c == 'q'){
                continuer = false;
            }
        }
    }

    public static void defPond(){
        System.out.println("\nChangement pondérations :");
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
            System.out.println("\nMenu principal :");
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
}
