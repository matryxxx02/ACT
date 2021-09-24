package tp7;

public class TacheOrd extends Tache{
	
	private int completion;
	private int retard;
	private Tache tache;

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public TacheOrd(Tache t, int completion, int retard) {
		super(t.getTempsExecution(), t.getPoid(), t.getTempsLimite());
		this.completion = completion;
		this.retard = retard;
		this.tache = t;
	}
	
	public int getCompletion() {
		return completion;
	}
	
	public void setCompletion(int completion) {
		this.completion = completion;
	}
	
	public int getRetard() {
		return retard;
	}
	
	public void setRetard(int retard) {
		this.retard = retard;
	}

	@Override
	public String toString() {
		return "TacheOrd{" +
				"completion=" + completion +
				", retard=" + retard +
				", tache=" + tache +
				'}';
	}
}
