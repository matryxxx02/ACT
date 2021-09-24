package tp2;
import java.util.Scanner;
import java.util.Stack;

public class Rectangle2 {

	public static void main(String[] args) {
		Rectangle2 tp2 = new Rectangle2();
		Scanner scanner = new Scanner(System.in);
		String tab = scanner.nextLine();
		String[] tmp = null;
		if (tab.charAt(0) == ' ') tmp= tab.substring(1).split(" ");
		else tmp= tab.split(" ");
		Point base= tp2.new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
		int n = Integer.parseInt(scanner.nextLine().replaceAll("\\s+",""));
		Point [] tableau = new Point[n+2];
		tableau[0] = tp2.new Point(0, base.hauteur);
		tableau[n+1] = tp2.new Point(base.largeur, base.hauteur);

		int [] lefts = new int[base.largeur];
		int [] rights = new int [base.largeur];
		int [] y = new int[base.largeur];
		for (int i =0 ; i < base.largeur; i++) {
			y[i] = base.hauteur;
			lefts[i] = 0;
			rights[i] = base.largeur;
		}



		for (int i =1; i< n+1; i++) {
			tab = scanner.nextLine();
			String[] tmp2 = null;
			if (tab.charAt(0) == ' ') tmp2= tab.substring(1).split(" ");
			else tmp2= tab.split(" ");
			tableau[i] = tp2.new Point(Integer.parseInt(tmp2[0]), Integer.parseInt(tmp2[1]));
			y[Integer.parseInt(tmp2[0])] = Math.min(y[Integer.parseInt(tmp2[0])], Integer.parseInt(tmp2[1]));
		}
	
		System.out.println(tp2.n(y, lefts, rights, base));
		
	}
	public int n(int[] y ,int [] lefts, int [] rights, Point base) {
		Stack<Integer> s = new Stack<Integer>();
		//		int [] lefts = new int[base.largeur];
		//		int [] rights = new int [base.largeur];
		//		int [] y = new int[base.hauteur];
		for (int i =0 ; i< base.largeur ; i++) {
			while (!s.empty() && (y[s.peek()] > y[i])) s.pop();
			if (s.isEmpty()) lefts[i] = 0;
			else lefts[i] = s.peek();
			if (y[i] != base.hauteur) s.push(i);

		}
		while(!s.empty()) s.pop();
		for (int i = base.largeur-1; i >= 0 ; i--) {
			while (!s.empty() && (y[s.peek()] > y[i])) s.pop();
			if(s.isEmpty()) rights[i] = base.largeur;
			else rights[i] = s.peek();
			if (y[i] != base.hauteur) s.push(i);
		}
		int aire = base.largeur;
		int temp = 0;
		for (int i =0 ; i< base.largeur ; i++) {
			temp =  y[i] * (rights[i] - lefts[i]);
			aire = (Math.max(aire, temp));
		}

		return aire;
	}
	public int n3(Point[] tableau, Point base) {
		int aire = 0;
		int largeur = 0;
		int hauteur = base.hauteur;
		for (int idxPointG =0 ; idxPointG< tableau.length ; idxPointG++) {
			for (int idxPointD = idxPointG ; idxPointD <tableau.length; idxPointD++) {
				hauteur = base.hauteur;
				largeur = tableau[idxPointD].getLargeur() - tableau [idxPointG].getLargeur(); 

				for (int h = idxPointG+1 ;h < idxPointD; h++ ) {
					if (hauteur > tableau[h].getHauteur()) hauteur = tableau[h].getHauteur();
				}
				if (aire < (largeur * hauteur)) aire = (largeur * hauteur);
			}
		}
		return aire;
	}
	public int n2(Point[] tableau, Point base) {
		int aire = 0;
		int largeur = 0;
		int hauteur = 0;
		for (int idxPointG =0 ; idxPointG< tableau.length ; idxPointG++) {
			hauteur = base.hauteur;
			for (int idxPointD = idxPointG +1 ; idxPointD <tableau.length; idxPointD++) {
				largeur = tableau[idxPointD].getLargeur() - tableau [idxPointG].getLargeur();
				if (aire < (largeur * hauteur)) aire = (largeur * hauteur);
				if (hauteur > tableau[idxPointD].getHauteur()) hauteur = tableau[idxPointD].getHauteur();
			}
		}
		return aire;
	}
	//	public int n(Point[] tableau, Point base) {
	//		int aire = 0;
	//		int aireD = ((base.largeur - tableau[1].getLargeur())*base.hauteur);
	//		int aireG = ((tableau[tableau.length-2].getHauteur()) * base.hauteur);
	//		aire = (Math.max(aireD, aireG));
	//		int left = -1;
	//		int right = -1;
	//		int left_edge;
	//		int right_edge;
	//		for (int i = 1 ; i< tableau.length -1 ; i++) {
	//			int upper_edge = i;
	//			int hauteur = tableau[upper_edge].getHauteur();
	//			
	//			for (left_edge = upper_edge ; left_edge< tableau.length-1; left_edge++) {
	//				if(tableau[left_edge].getHauteur()< hauteur) {
	//					left = tableau[left_edge].getLargeur();
	//					break;
	//				}
	//			}
	//			if (left < 0)left =0;
	//			for (right_edge = upper_edge-1 ; right_edge > -1 ; right_edge -- )
	//				if (tableau[right_edge].getHauteur() < hauteur) {
	//					right = tableau[right_edge].getLargeur();
	//					break;
	//				}
	//			if (right < 0)right =0;
	//			if (aire < (right - left) * hauteur) aire = (right - left) * hauteur ;
	//			
	//			right_edge = tableau[i].getLargeur();
	//			if (i < tableau.length-1) {
	//				left_edge = tableau[i+1].getLargeur();
	//				if (aire < (right_edge - left_edge) * base.hauteur) aire = (right_edge - left_edge) * base.hauteur;
	//			}
	//		}
	//		return aire;
	//	}


	// intervalles qui ont la meme hauteur. On prend alors la plus grande largeur.  
	//	public int diviserPourRegner(Point[]tableau,Point base) {
	//		int idxPivot = 1;
	//		Point pivot = tableau[1];
	//		for(int i = 1; i<tableau.length; i++){
	//			if(tableau[i].getHauteur()<pivot.getHauteur()) {
	//				pivot=tableau[i];
	//				idxPivot = i;
	//			}
	//		}
	//
	//		Point[] tableauG = new Point[idxPivot];
	//		Point[] tableauD = new Point[tableau.length-idxPivot];
	//		for(int i = 0; i<tableau.length; i++) {
	//			if(i < idxPivot) {
	//				tableauG[i] = tableau[i];
	//			} else {
	//				tableauD[i-idxPivot] = tableau[i];
	//			}
	//		}
	//
	//		Point[] sufaceG = n2D(tableauG, base);
	//		Point[] sufaceD = n2D(tableauD, base);
	//		Point alpha = new Point(base.getLargeur(), pivot.getHauteur());
	//		int bas = n2(tableau, alpha);
	//
	//		if(sufaceD[0].getHauteur()<sufaceG[0].getHauteur()){
	//			return sufaceG[0].getHauteur();
	//		} else if (sufaceD[0].getHauteur()<sufaceG[0].getHauteur()){
	//			return sufaceD[0].getHauteur();
	//		} else {
	//			if ()
	//			diviserPourRegner(,Point base);
	//		}
	//	}

	//TODO : a revoir parce j'ai des doutes ... (peut etre meilleure solutions)
	public int diviserPourRegner2(Point[] tableau, Point base){
		int idxPivot = 1;
		Point pivot = tableau[1];
		for(int i = 1; i<tableau.length; i++){
			if(tableau[i].getHauteur()<pivot.getHauteur()) {
				pivot=tableau[i];
				idxPivot = i;
			}
		}

		Point[] tableauG = new Point[idxPivot];
		Point[] tableauD = new Point[tableau.length-idxPivot];
		for(int i = 0; i<tableau.length; i++) {
			if(i < idxPivot) {
				tableauG[i] = tableau[i];
			} else {
				tableauD[i-idxPivot] = tableau[i];
			}
		}

		int sufaceG = n2(tableauG, base);
		int sufaceD = n2(tableauD, base);
		Point alpha = new Point(base.getLargeur(), pivot.getHauteur());


		if(sufaceD<sufaceG){
			return sufaceG;
		} else if (sufaceD>sufaceG){
			return sufaceD;
		} else {
			int bas = n2(tableau, alpha);
			return bas;
		}
	}	
	//	public int n(Point[] tableau, Point base) {
	//		int aire = 0;
	//		Stack<Integer> pile = new Stack<Integer>();
	//		pile.push(0);
	//		for (int idxPointG =1 ; idxPointG< tableau.length ; idxPointG++) {
	//			if (tableau[pile.firstElement()].getHauteur() > tableau[idxPointG].getHauteur()) pile.push(idxPointG);
	//		}
	//		for (Integer idx : pile) {
	//		
	//			tableau[idx]
	//		}
	//		return aire;
	//	}


	private class Point {
		private int largeur;
		private int hauteur;

		public Point(int largeur, int hauteur) {
			super();
			this.largeur = largeur;
			this.hauteur = hauteur;
		}
		public int getLargeur() {
			return largeur;
		}
		public int getHauteur() {
			return hauteur;
		}
		public String toString(){
			return "["+this.getLargeur()+","+this.getHauteur()+"]";
		}
	}

}

