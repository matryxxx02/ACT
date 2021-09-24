package tp2;
import java.util.Scanner;
import java.util.Stack;

public class Rectangle {

	public static void main(String[] args) {
		Rectangle tp2 = new Rectangle();
		Scanner scanner = new Scanner(System.in);
		String tab = scanner.nextLine();
		String[] tmp = null;
		if (tab.charAt(0) == ' ') tmp= tab.substring(1).split(" ");
		else tmp= tab.split(" ");
		Point base= tp2.new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
		int n = Integer.parseInt(scanner.nextLine().replaceAll("\\s+",""));
		Point [] tableau = new Point[n];
		tableau[0] = tp2.new Point(0, base.hauteur);
		tableau[n+1] = tp2.new Point(base.largeur, base.hauteur);
		for (int i =1; i< n+1; i++) {
			tab = scanner.nextLine();
			String[] tmp2 = null;
			if (tab.charAt(0) == ' ') tmp2= tab.substring(1).split(" ");
			else tmp= tab.split(" ");
			tableau[i] = tp2.new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
		}
		//System.out.println(tp2.n3(tableau, base));
		//System.out.println(tp2.n2(tableau, base));
		//System.out.println(tp2.diviserPourRegner(tableau, base));
		System.out.println(tp2.linear(tableau, base));
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

	public int linear(Point[] tableau, Point base){
		Stack<Point> pointStack = new Stack<Point>();
		pointStack.push(tableau[0]);
		int aire = 0;
		int minHeight = base.getHauteur();
		for(int i = 1; i<tableau.length; i++){
			aire = Math.max(aire,base.getHauteur()*(tableau[i].getLargeur() - pointStack.peek().getLargeur()));
			while(!pointStack.isEmpty() && pointStack.peek().getHauteur() > tableau[i].getHauteur()){
				minHeight = pointStack.pop().getHauteur();
				if(!pointStack.isEmpty())
					aire = Math.max(aire,minHeight*(tableau[i].getLargeur() - pointStack.peek().getLargeur()));
			}
			pointStack.push(tableau[i]);
		}
		return aire;
	}

	public int diviserPourRegner(Point[] tableau, Point base){
		int idxPivot = 1;
		Point pivot = tableau[1];
		for(int i = 1; i<tableau.length; i++){
			if(tableau[i].getHauteur()<pivot.getHauteur()) {
				pivot=tableau[i];
				idxPivot = i;
				System.out.println(pivot+" "+idxPivot);
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
		int bas = n2(tableau, alpha);

		if(sufaceD<sufaceG){
			return sufaceG;
		} else if (sufaceD>sufaceG){
			return sufaceD;
		} else {
			return bas;
		}
	}

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

