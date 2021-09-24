package tp6;

class PblPartition extends PblDecision{
	
	private int n ;
	private int[] entiers;
	private Certificat certifCorrect;
	private int D;

	public PblPartition(int nb, int[] entiers){
		this.n = nb;
		this.entiers = entiers;
		for(int entier : entiers) D+=entier;
		D/=2;
	}

	public PblPartition(int nb, int[] entiers, int s){
		this.n = nb;
		this.entiers = entiers;
		D=s;
	}

	public Certificat getCertifCorrect() {
		return certifCorrect;
	}

	//reduction polynomiale qui retourne une instance équivalente de JSP
	public PblJSP redPolyToJSP(){
		int debut = 0;
		int nbPartition =2;
		JobOrigine[] jobOrigine = new JobOrigine[n+nbPartition];
        for(int i = 0; i<n; i++){
			jobOrigine[i] = new JobOrigine(debut, entiers[i]);
		}
		jobOrigine[n] = new JobOrigine(debut, D/2);
		jobOrigine[n+1] = new JobOrigine(debut, D/2);

		return new PblJSP(2, this.n+2, D, jobOrigine);
	}

	// en une ligne en utilisant redPolyToJSP() et aUneSolution de JSP
	public boolean aUneSolution() {
		PblJSP pblJSP = redPolyToJSP();
		boolean result = pblJSP.aUneSolution();
		certifCorrect = pblJSP.getCertificatCorrect();
		return result;
	}
}