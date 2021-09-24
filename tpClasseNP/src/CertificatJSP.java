package tp6;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//la notion de certificat pour le problème Job Shop Problem
public class CertificatJSP implements Certificat {
	/**
	 * Liste des tâches à exécuter. 
	 */
	private Job[] jobs; 
	
	/**
	 * données u du problème d'origine
	 */
	private PblJSP probleme; 
	
	/**
	 * 
	 * @param jobs structure de données du certificat 
	 * @param probleme structure données d'origine du problème
	 */
	public CertificatJSP(PblJSP probleme) {
		super();
		this.probleme = probleme;
		this.jobs = new Job[probleme.getN()];
		
	}
	
	/**
	 * Si on considère deux tâche de la forme t(int décalageDate, numeroMachine) t1(1,2) et t2(0,0) avec D=2 et M=2
	 * On considère alors que le précédent certificat était  t1'(1,1) t2'(2,1).
	 * Le certificat suivant sera alors t1''(2,0)t2''(0,0)
	 * 
	 * On considère avec la configuration au dessus tout les résultats possibles :
	 * 
	 * 
	 	t1 (0,0) t2(0,0)
	 	t1 (0,0) t2(0,1)
	 	t1 (0,0) t2(1,0)
	 	t1 (0,0) t2(1,1)
	 	t1 (0,0) t2(2,0)
	 	t1 (0,0) t2(2,1)
	 	
	 	t1 (0,1) t2(0,0)
	 	t1 (0,1) t2(0,1)
	 	t1 (0,1) t2(1,0)
	 	t1 (0,1) t2(1,1)
	 	t1 (0,1) t2(2,0)
	 	t1 (0,1) t2(2,1)
	 	
	 	t1 (1,0) t2(0,0)
	 	t1 (1,0) t2(0,1)
	 	t1 (1,0) t2(1,0)
	 	t1 (1,0) t2(1,1)
	 	t1 (1,0) t2(2,0)
	 	t1 (1,0) t2(2,1)
	 	
	 	t1 (1,1) t2(0,0)
	 	t1 (1,1) t2(0,1)
	 	t1 (1,1) t2(1,0)
	 	t1 (1,1) t2(1,1)
	 	t1 (1,1) t2(2,0)
	 	t1 (1,1) t2(2,1)
	 	
	 	t1 (2,0) t2(0,0)
	 	t1 (2,0) t2(0,1)
	 	t1 (2,0) t2(1,0)
	 	t1 (2,0) t2(1,1)
	 	t1 (2,0) t2(2,0)
	 	t1 (2,0) t2(2,1)
	 	
	 	t1 (2,1) t2(0,0)
	 	t1 (2,1) t2(0,1)
	 	t1 (1,1) t2(1,0)
	 	t1 (2,1) t2(1,1)
	 	t1 (2,1) t2(2,0)
	 	t1 (2,1) t2(2,1)
	 	
	 */
	public void suivant() {
		this.jobs = this.generateCertif();
	}
	
	private Job[] generateCertif() {
		Job[] certificat = Arrays.copyOf(jobs, jobs.length);
		for (int i = jobs.length-1; i>=0; i--) {
			if (certificat[i].getNumeroMachine() < this.probleme.getM()-1) {
				certificat[i].setNumeroMachine(certificat[i].getNumeroMachine()+1);
				return certificat;
			}
			if(certificat[i].getNumeroMachine() >= this.probleme.getM()-1){
				certificat[i].setNumeroMachine(0);
			}
			if (certificat[i].getDateDebut() < (this.probleme.getJob(i).getDebut() + this.probleme.getD())) {
				certificat[i].setDateDebut(certificat[i].getDateDebut()+1);
				return certificat;
			}
			if(certificat[i].getDateDebut() >= (this.probleme.getJob(i).getDebut() + this.probleme.getD())){
				certificat[i].setDateDebut(probleme.getJob(i).getDebut());
			}
			
		}
		return certificat;
	}
	
	/**
	 * Le dernier élément du certificat est quand la durée d'attente de chaque tâche est au maximum est quelles s'éxécutent toutes sur la même machine. 
	 */
	public boolean estDernier() {
		for(int i =0 ; i< this.jobs.length; i++) {
			if (getDateOffset(i) < this.probleme.getD() && jobs[i].getNumeroMachine() < this.probleme.getM()-1 ) {
				return false;
			}
		}
		return true;
	}

	public void reset() {
		for (int i =0; i< jobs.length; i++)  jobs[i] = new Job(probleme.getJob(i).getDebut(), 0);		
	}

	public boolean estCorrect() {
		for (int i =0; i< jobs.length; i++) {
			for (int j =1; j< jobs.length; j++) {
				if (i !=j) {
					if(jobs[i].getNumeroMachine() == jobs[j].getNumeroMachine()) {
						if (appartientInterval(jobs[i].getDateDebut(), jobs[i].getDateDebut()+probleme.getJob(i).getDuree(), jobs[j].getDateDebut())
								|| appartientInterval(jobs[j].getDateDebut(), jobs[j].getDateDebut()+probleme.getJob(j).getDuree(), jobs[i].getDateDebut() )) {
							
							return false;
						}
					}
				}
			}
		}
		return true;
	}
		
	public boolean appartientInterval(int debut, int fin, int valeur) {
		return valeur >= debut && valeur < fin;
	}
	/**
	 * Géneration d'un certificat aléatoire en fonction des données d'origine. 
	 */
	public void alea() {
		Random r = new Random();
		//pour chaque tâche on lui attribut une machine aléatoire et une date début avec un temps d'attente aléatoire. 
		for (int i =0 ; i < probleme.getN(); i++) {
			this.jobs[i] = new Job(
					probleme.getJobOrigines()[i].getDebut() + r.nextInt(probleme.getD()+1)
					, r.nextInt(probleme.getM()));
		}
		
	}

	public void saisie() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		for (int i =0 ; i< jobs.length; i++) {
			System.out.println("Entrez la date de Début (un entier) de la tâche numéro : " + i);
			
			int dateDebut = scanner.nextInt();
			while(!verifDate(i, dateDebut)) {
				System.out.println("Date incorrect le délais ne doit pas dépassé la date : " + (probleme.getJob(i).getDebut()+ probleme.getD())+
						"\n et doit être supérieur ou égale à "+ probleme.getJob(i).getDebut()
						);
				dateDebut = scanner.nextInt();
			}
			System.out.println("Entrez le numéro de machine (de 0 à "+(probleme.getM()-1)+") que laquelle s'exécutera la tâche numéro : " + i);
			int machine = scanner.nextInt();
			while(!verifMachine(i, machine)) {
				System.out.println("Numéro de machine incorrect la valeur doit être comprise entre 0 et "+ probleme.getJob(i).getDebut()+ probleme.getD()+
									(probleme.getM()-1)
									);
				machine = scanner.nextInt();
			}
			this.jobs[i] = new Job(dateDebut, machine);
		}
		scanner.close();
		
	}
	
	public boolean verifDate(int idxJob, int date) {
		return date >= probleme.getJob(idxJob).getDebut() && date <= (probleme.getJob(idxJob).getDebut() +  probleme.getD()); 
	}
	public boolean verifMachine(int idxJob, int machine) {
		return machine >= 0 && machine <= probleme.getM(); 
	}

	public void affiche() {
		System.out.println(Arrays.deepToString(jobs));
		
	}

	public Job[] getJobs() {
		return jobs;
	}

	public void setJobs(Job[] jobs) {
		this.jobs = jobs;
	}

	public PblJSP getProbleme() {
		return probleme;
	}

	public void setProbleme(PblJSP probleme) {
		this.probleme = probleme;
	}
	//renvoie la durée de laquelle la date a été décalé. 
	public int getDateOffset(int idx) {
		return this.getProbleme().getJob(idx).getDebut() - this.jobs[idx].getDateDebut();
	}
	

}
