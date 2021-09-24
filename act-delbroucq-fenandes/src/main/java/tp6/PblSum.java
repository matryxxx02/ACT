package tp6;

public class PblSum extends PblDecision {

	private int n ;
	private int[] entiers;
	private Certificat certifCorrect;
	private int cible;

	public PblSum(int nb, int[] entiers, int s){
		this.n = nb;
		this.entiers = entiers;
		this.cible = s;
	}

	public Certificat getCertifCorrect() {
		return this.redPolyToPartition().getCertifCorrect();
	}

	public PblPartition redPolyToPartition(){
		return new PblPartition(n,entiers,cible*2);
	}

	public PblJSP redPolyToJSP(){
		return this.redPolyToPartition().redPolyToJSP();
	}

	public boolean aUneSolution() {
		return this.redPolyToJSP().aUneSolution();
	}
}
