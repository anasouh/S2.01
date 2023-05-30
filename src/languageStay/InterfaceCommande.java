package languageStay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterfaceCommande {
    
    private static Scanner scan = new Scanner(System.in);

    private static char ecouterChar(){
        System.out.print("Veuillez entrer 1 caractère : ");
        String c = scan.next();
        while(c.length() != 1){
            System.out.print("Veuillez entrer 1 caractère : ");
            c = scan.next();
        }
        return c.charAt(0);
    }

    private static void clear(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }

    private static void ajouterEtu(){
        List<String> liste = new ArrayList<>();
        System.out.println("Ajouter étudiant :");
        String[] header = Plateform.CSVImportHeader.split(";");
        for(int i = 0; i < header.length; i++){
            System.out.print("Veuillez la valeur : ");
        }
    }

    public static void main(String[] args) {
        boolean continuer = true;
        while(continuer){
            System.out.println("Menu principal :");
            System.out.println("- la commande q permet de quitter l'interface");
            System.out.println("- la commande a permet d'ajouter un étudiant");
            System.out.println("- la commande t permet d'afficher la tableau de bord'");
            char c = ecouterChar();
            if(c == 'q'){
                continuer = false;
            }else if(c == 'a'){
                ajouterEtu();
            }
        }
        System.out.println("Salut, bisous");
        scan.close();
    }
}
