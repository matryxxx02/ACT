package tp6;

public class PblJSP extends PblDecision {
	
	/**
	 * un nombre de machines
	 */
	private int m;
	/**
	 * un nombre de tâches
	 */
	private int n;
	
	/**
	 * l’attente maximale autorisée.
	 */
	private int D; 
	
	/**
	 * Liste des tâches à exécuter. 
	 */
	private JobOrigine[] jobOrigines;
	
	/**
	 * Certificat correct
	 * Null sinon
	 */
	private CertificatJSP certificatCorrect;
	
	public PblJSP(int m, int n, int d, JobOrigine[] jobOrigines) {
		super();
		this.m = m;
		this.n = n;
		D = d;
		this.jobOrigines = jobOrigines;
	}

	
	public int getM() {
		return m;
	}


	public int getN() {
		return n;
	}


	public int getD() {
		return D;
	}


	public JobOrigine[] getJobOrigines() {
		return jobOrigines;
	}
	public JobOrigine getJob(int idxJob) {
		return jobOrigines[idxJob];
	}


	@Override
	public boolean aUneSolution() {
		CertificatJSP certificatJSP = new CertificatJSP(this);
		certificatJSP.reset();
		
		while(!certificatJSP.estDernier()) {
			if (certificatJSP.estCorrect()) {
				this.certificatCorrect = certificatJSP;
				
				return true;
			}
			//certificatJSP.affiche();
			certificatJSP.suivant();
		}
		return false;
	}


	public CertificatJSP getCertificatCorrect() {
		return certificatCorrect;
	}



	
}
