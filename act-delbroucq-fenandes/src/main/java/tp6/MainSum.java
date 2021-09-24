package tp6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainSum {
    public static void main(String[] arg) throws Exception {
        PblSum sum;
        //saisie du probleme
        if (arg.length < 2)
            System.out.println("java testJSP mode att");
        else {
            System.out.println(arg[1]);
            Scanner donnee = new Scanner (System.in); //l’ins€tance
            sum = readFile(arg[1]);

            // les differents modes
            if (arg[0].equals("-verif")) {
                //lire un certificat proposé, sortir le résultat de la vérification
            }
            else if (arg[0].equals("-nondet")) {
                //générer aléatoirement un certificat, sortir le résultat de la vérification et evt le certificat
            }
            else if (arg[0].equals("-exhaust")) {
                //générer tous les certificats jusqu’au dernier ou jusqu’à un trouver un de valide, sortir le résultat et evt le certificat valide
                if (sum.aUneSolution()) {
                    System.out.println("correct ! :)");
                    sum.getCertifCorrect().affiche();
                }
                else {
                    System.out.println("pas correct ! :(");
                }
            }
            else System.out.println("erreur de mode");
        }
    }
    private static PblSum readFile(String filename) throws IOException {
        File fichier = new File(filename);
        FileReader reader = new FileReader(fichier);
        BufferedReader br = new BufferedReader(reader);

        String ligne = "";
        /**
         * Nombre d'entier
         */
        int nb = Integer.parseInt(br.readLine().replaceAll("\\s+",""));
        System.out.println(nb);
        /**
         * Nombre cible
         */
        int s = Integer.parseInt(br.readLine().replaceAll("\\s+",""));
        System.out.println(s);

        /**
         * tableau des n entiers
         */
        int[] entiers = new int[nb];
        /**
         * Ajout de chaque entier pour chaque entré
         */
        while (ligne.equals("")) {
            ligne = br.readLine();
        }
        for (int i = 0; i<nb; i++) {
            System.out.println(ligne);
            entiers[i] = Integer.parseInt(ligne);
            ligne = br.readLine();
        }

        br.close();
        return new PblSum(nb, entiers, s);
    }
}
