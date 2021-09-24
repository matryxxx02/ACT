package tp3;

public enum Pion {
	
	BLANC('P'),NOIR('p'), VIDE(' ');
	
	public char pion;
	
	private Pion(char pion) {
		this.pion=pion;
	}
	public Pion getAdversaire() {
		if (this.pion == 'P') return Pion.NOIR;
		if (this.pion == 'p') return Pion.BLANC;
		else return Pion.VIDE;
	}

	@Override
	public String toString() {
		return ""+this.pion;
	}
}
