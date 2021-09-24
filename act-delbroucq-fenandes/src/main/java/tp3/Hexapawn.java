package tp3;

import java.util.*;


public class Hexapawn {
	boolean gagnantBlanc = false;
	public static void main(String[] args) {
		String ligne = "";
		
		Hexapawn tp3 = new Hexapawn();
		Scanner scanner = new Scanner(System.in);
		int nbrLignes = Integer.parseInt(scanner.nextLine().replaceAll("\\s+","")); 
		int nbrColonnes = Integer.parseInt(scanner.nextLine().replaceAll("\\s+",""));
		Pion[][] plateau = new Pion[nbrLignes][nbrColonnes];
		for (int idxLigne =0 ; idxLigne < nbrLignes; idxLigne ++ ) {
			ligne = scanner.nextLine();
			for (int idxColonne =0 ; idxColonne < ligne.length(); idxColonne ++) {
				if (ligne.charAt(idxColonne) == Pion.VIDE.pion ) plateau [idxLigne][idxColonne] = Pion.VIDE;
				else if (ligne.charAt(idxColonne) == Pion.NOIR.pion ) plateau [idxLigne][idxColonne] = Pion.NOIR;
				else if (ligne.charAt(idxColonne) == Pion.BLANC.pion ) plateau [idxLigne][idxColonne] = Pion.BLANC;
			}
			
		}
		// Dans le sujet on précise que c'est toujours au joueur blanc de commencer. 
		//System.out.println(tp3.hexapawnNaif(plateau, Pion.BLANC));
		HashMap<String,Integer> listePlateaux = new HashMap();
		System.out.println(tp3.hexapawnMemo(plateau, Pion.BLANC));
	}
	
	public List<Etape> calculSuccesseurs(Pion[][] plateau, Pion joueurCourant){
		List<Etape> successeurs = new LinkedList<Etape>();
		//On regarde le cas où le pion ennemi est sur notre ligne et donc le cas où le joueur courant est perdant. 
		// On suppose ici que les pions noir commencent toujours en bas du tableau et les pions blancs en haut. 
		for (int idxColonne =0 ; idxColonne < plateau[0].length; idxColonne++) {
			if (joueurCourant == Pion.NOIR && plateau[0][idxColonne] == Pion.BLANC ) return successeurs;
			if (joueurCourant == Pion.BLANC && plateau[plateau.length-1][idxColonne] == Pion.NOIR) {
				this.gagnantBlanc= false;
				return successeurs;
			}
		}
		//Sinon on regarde toutes les autres cases du tableau. 
		for (int idxLigne =0 ; idxLigne < plateau.length; idxLigne++) {
			for (int idxColonne=0; idxColonne< plateau[0].length; idxColonne++) {
				if (peutActionMangeDroite(idxLigne, idxColonne, plateau, joueurCourant)) {
					if (joueurCourant == Pion.NOIR ) {
					successeurs.add(action(idxLigne, idxColonne, 1, 1, plateau, joueurCourant));
					}
					if (joueurCourant == Pion.BLANC )
						successeurs.add(action(idxLigne, idxColonne, -1, 1, plateau, joueurCourant));
				}
				else if (peutActionMangeGauche(idxLigne, idxColonne,plateau, joueurCourant)) {
					if (joueurCourant == Pion.NOIR )
						successeurs.add(action(idxLigne, idxColonne, 1, -1, plateau, joueurCourant));
					if (joueurCourant == Pion.BLANC )
						successeurs.add(action(idxLigne, idxColonne, -1, -1, plateau, joueurCourant));
				}
				else if (peutActionAvance(idxLigne, idxColonne, plateau, joueurCourant)) {
					if (joueurCourant == Pion.NOIR )
						successeurs.add(action(idxLigne, idxColonne, 1, 0, plateau, joueurCourant));
					if (joueurCourant == Pion.BLANC )
						successeurs.add(action(idxLigne, idxColonne, -1, 0, plateau, joueurCourant));
				}
			}
		}
		if (joueurCourant == Pion.BLANC) this.gagnantBlanc = false;
		return successeurs;
	}
	
	public boolean peutActionAvance(int idxLigne, int idxColonne, Pion[][] plateau, Pion joueurCourant) {
		if (joueurCourant == plateau[idxLigne][idxColonne] ) {
			if (joueurCourant == Pion.NOIR && idxLigne +1 < plateau.length && plateau[idxLigne+1][idxColonne] == Pion.VIDE) return true;
			else if (joueurCourant == Pion.BLANC && idxLigne -1 >=0 && plateau[idxLigne-1][idxColonne] == Pion.VIDE) return true;
		}
		return false;
	}
	
	public Pion[][] copyPlateau(Pion[][] plateau){
		Pion[][] plateauCopy = new Pion[plateau.length][plateau[0].length];
		for (int i =0 ; i < plateauCopy.length; i++)
			for (int j =0; j< plateauCopy[0].length; j++)
				plateauCopy[i][j] = plateau[i][j];
		return plateauCopy;
	}
	public Etape action(int idxLigne, int idxColonne, int ratioLigne, int ratioColonne, Pion[][]plateau, Pion joueurCourant) {
		//pas sur que le copyOf marche
		Pion[][] plateauAfterAction = copyPlateau(plateau) ;
		plateauAfterAction[idxLigne][idxColonne] = Pion.VIDE;
		plateauAfterAction[idxLigne + ratioLigne][idxColonne+ratioColonne] = joueurCourant;
		return new Etape(plateauAfterAction, joueurCourant);
	}
	public boolean peutActionMangeDroite(int idxLigne, int idxColonne, Pion[][] plateau, Pion joueurCourant) {
		if (joueurCourant == plateau[idxLigne][idxColonne] ) {
			if (joueurCourant == Pion.NOIR && idxLigne +1 < plateau.length && idxColonne +1 < plateau[0].length &&  plateau[idxLigne+1][idxColonne+1] == joueurCourant.getAdversaire()) return true;
			if (joueurCourant == Pion.BLANC && idxLigne -1 >=0  && idxColonne +1 < plateau[0].length &&  plateau[idxLigne-1][idxColonne+1] == joueurCourant.getAdversaire()) return true;
		}
		return false;
	}
	public boolean peutActionMangeGauche(int idxLigne, int idxColonne, Pion[][] plateau, Pion joueurCourant) {
		if (joueurCourant == plateau[idxLigne][idxColonne] ) {
			if (joueurCourant == Pion.NOIR && idxLigne +1 < plateau.length && idxColonne -1 > 0 &&  plateau[idxLigne+1][idxColonne-1] == joueurCourant.getAdversaire()) return true;
			if (joueurCourant == Pion.BLANC && idxLigne -1 >=0 && idxColonne -1 > 0 &&  plateau[idxLigne-1][idxColonne-1] == joueurCourant.getAdversaire()) return true;
		}
		return false;
	}
	
	public int hexapawnNaif(Pion [][] plateau, Pion joueurCourant) {

		List<Etape> etapes = calculSuccesseurs(plateau, joueurCourant);
		boolean positif = true;
		int maxPositif =0;
		int maxNegatif=0;
		if (etapes.isEmpty()) return 0;
		for (Etape e : etapes) {

			int valeur = hexapawnNaif(e.plateau, e.joueurCourant.getAdversaire());
			//System.out.println(valeur);
			if (positif) {
				if (maxPositif < valeur) maxPositif = valeur;
				if (valeur <= 0) {
					positif = false;
					maxNegatif = valeur;
				}
			}
			else {
				if (maxNegatif < valeur && valeur <= 0) maxNegatif = valeur;
			}

		}
		//maintenant nous devons appliqué la formule de la question 1 sur le reste de nos étapes.
		if (positif) return -(maxPositif+1);
		else return Math.abs(maxNegatif) + 1;
	}

	public String afficherPlateau(Pion[][] p){
		String res = "";
		for (int i = 0; i<p.length; i++){
			for (int j = 0; j<p[0].length; j++){
				res += p[i][j];
			}
		}
		return res;
	}

	public int hexapawnMemo(Pion [][] plateau, Pion joueurCourant) {

		List<Etape> etapes = calculSuccesseurs(plateau, joueurCourant);
		HashMap<String, Integer> listePlateaux = new HashMap();
		boolean positif = true;
		int maxPositif = 0;
		int maxNegatif = 0;
		if (etapes.isEmpty()) return 0;
		for (Etape e : etapes) {
			if(listePlateaux.containsKey(this.afficherPlateau(e.plateau))){
				return listePlateaux.get(this.afficherPlateau(e.plateau));
			}else{
				//int valeur = hexapawnMemo(e.plateau, e.joueurCourant.getAdversaire(), listePlateaux);
				int valeur = hexapawnMemo(e.plateau, e.joueurCourant.getAdversaire());
				listePlateaux.put(this.afficherPlateau(e.plateau), valeur);
				//System.out.println(valeur);
				if (positif) {
					if (maxPositif < valeur) maxPositif = valeur;
					if (valeur <= 0) {
						positif = false;
						maxNegatif = valeur;
					}
				}
				else {
					if (maxNegatif < valeur && valeur <= 0) maxNegatif = valeur;
				}
			}
		}
		//maintenant nous devons appliqué la formule de la question 1 sur le reste de nos étapes.
		if (positif) return -(maxPositif+1);
		else return Math.abs(maxNegatif) + 1;
	}
}
class Etape {
	Pion[][] plateau;
	Pion joueurCourant;
	
	public Etape(Pion[][] plateau, Pion joueur) {
		super();
		this.plateau = plateau;
		this.joueurCourant = joueur;
	}

}
