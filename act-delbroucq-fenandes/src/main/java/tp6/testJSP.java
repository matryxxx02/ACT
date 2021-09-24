package tp6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class testJSP{
	public static void main(String[] arg) throws Exception {
		CertificatJSP certificatJSP;
		PblJSP pblJSP;
	//saisie du probleme
		if (arg.length < 2)
			System.out.println("java testJSP mode att");
		else {
			System.out.println(arg[1]);
			

			Scanner donnee = new Scanner (System.in); //l’instance
			//parser l’instance du probleme, creer l’objet
			//System.out.println("Entrez le chemin vers le fichier");
			//pblJSP = readFile(donnee.nextLine());
			pblJSP = readFile(arg[1]);
			certificatJSP = new CertificatJSP(pblJSP);
		
			// les differents modes
			if (arg[0].equals("-verif")) {
			//lire un certificat proposé, sortir le résultat de la vérification
				certificatJSP.saisie();
				if (certificatJSP.estCorrect()) {
					System.out.println("correct ! :)");
				} else {
					System.out.println("pas correct ! :(");
				}
				certificatJSP.affiche();
			 }
			else if (arg[0].equals("-nondet")) {
			//générer aléatoirement un certificat, sortir le résultat de la vérification et evt le certificat
				certificatJSP.alea();
				if (certificatJSP.estCorrect()) {
					System.out.println("correct ! :)");
				} else {
					System.out.println("pas correct ! :(");
				}
				certificatJSP.affiche();
				
			}
			else if (arg[0].equals("-exhaust")) {
			//générer tous les certificats jusqu’au dernier ou jusqu’à un trouver un de valide, sortir le résultat et evt le certificat valide
				if (pblJSP.aUneSolution()) {
					System.out.println("correct ! :)");
					pblJSP.getCertificatCorrect().affiche();
				}
				else {
					System.out.println("pas correct ! :(");
				}
			}
			else System.out.println("erreur de mode");
		}
	}
	//a compléter pour tester réduction
	
	private static PblJSP readFile(String filename) throws IOException {
		File fichier = new File(filename);
		FileReader reader = new FileReader(fichier);
		BufferedReader br = new BufferedReader(reader);

		String ligne = "";
		/**
		 * Nombre de machine
		 */
		int M = Integer.parseInt(br.readLine().replaceAll("\\s+",""));
		System.out.println(M);
		/**
		 * Nombre de tâches
		 */
		int n = Integer.parseInt(br.readLine().replaceAll("\\s+",""));
		System.out.println(n);
		/**
		 * tableau des données d'origines du problèmes
		 */
		JobOrigine[] jobs = new JobOrigine[n];
		String[] tmp = null;
		/**
		 * Pour chaque entré création de la tache
		 */
		while (ligne.equals("")) {
			ligne = br.readLine();
		}
		for (int i =0 ; i< n; i++) {
			System.out.println(ligne);
			if (ligne.charAt(0) == ' ') tmp= ligne.substring(1).split(" ");
			else tmp= ligne.split(" ");
			jobs[i] = new JobOrigine(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
			ligne = br.readLine();
		}
		/**
		 * récupération de l'attente max. 
		 */
		while (!ligne.contains("une solution pour attente=")) {
			ligne = br.readLine();
		}
		ligne = ligne.replace("une solution pour attente=", "");

		// On supprime les espaces en trop
		ligne = ligne.replaceAll("\\s+", "");
		int D = Integer.parseInt(ligne);
		System.out.println(D);
		br.close();
		return new PblJSP(M, n, D, jobs);
	}
}

