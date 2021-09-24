package tp1;

import java.util.Scanner;

public class Rectangle {

    public static void main(String[] args) {
        Rectangle tp2 = new Rectangle();
//        Scanner scanner = new Scanner(System.in);
//        String tab = scanner.nextLine();
//        String[] tmp = null;
//        if (tab.charAt(0) == ' ') tmp = tab.substring(1).split(" ");
//        else tmp = tab.split(" ");
//        Point base = tp2.new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
//        int n = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
//        Point[] tableau = new Point[n + 2];
//        tableau[0] = tp2.new Point(0, base.hauteur);
//        tableau[n + 1] = tp2.new Point(base.largeur, base.hauteur);
//        for (int i = 1; i < n + 1; i++) {
//            tab = scanner.nextLine();
//            String[] tmp2 = null;
//            if (tab.charAt(0) == ' ') tmp2 = tab.substring(1).split(" ");
//            else tmp = tab.split(" ");
//            tableau[i] = tp2.new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
//        }
//        System.out.println(tp2.n3(tableau, base));
        int largeur = 25;
        int hauteur = 20;
        Point[] points = {tp2.new Point(0,0),tp2.new Point(2,5),tp2.new Point(5,17),tp2.new Point(11,4),tp2.new Point(16,6),tp2.new Point(20,1),tp2.new Point(25,20)};
        int n = tp2.diviserPourRegner(points);
    }

    public int n3(Point[] tableau, Point base) {
        int aire = 0;
        int largeur = 0;
        int hauteur = base.hauteur;
        for (int i = 0; i < tableau.length; i++) {
            for (int j = i; j < tableau.length; j++) {
                hauteur = base.hauteur;
                largeur = tableau[j].getLargeur() - tableau[i].getLargeur();

                for (int h = i + 1; h < j; h++) {
                    if (hauteur > tableau[h].getHauteur()) hauteur = tableau[h].getHauteur();
                }
                if (aire < (largeur * hauteur)) aire = (largeur * hauteur);
            }
        }
        return aire;
    }

    public int diviserPourRegner(Point[] tableau){
        int idxPivot = 1;
        Point pivot = tableau[1];
        for(int i = 1; i<tableau.length; i++){
            if(tableau[i].getHauteur()<pivot.getHauteur()) {
                pivot=tableau[i];
                idxPivot = i;
                System.out.println(pivot+" "+idxPivot);
            }
        }

        Point[] idxG = new Point[idxPivot+1];
        Point[] idxD = new Point[tableau.length-idxPivot];
        for(int i = 0; i<tableau.length; i++) {
            if(i <= idxPivot) {
                idxG[i] = tableau[i];
            } else {
                idxD[i-idxPivot] = tableau[i];
            }
        }

        return 0;
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

