package tp0;

import java.util.Scanner;

public class Equilibre {
	public static void main(String[] args) {
		Equilibre tp1 = new Equilibre();
		Scanner scanner = new Scanner(System.in); 
		int n = Integer.parseInt(scanner.nextLine().replaceAll("\\s+",""));
		String[] tmp = null;
		String tab = scanner.nextLine();
		if (tab.charAt(0) == ' ') tmp= tab.substring(1).split(" ");
		else tmp= tab.split(" ");
		int[] t = new int[n];
		for (int i =0; i< n; i++) {
			t[i]= Integer.parseInt(tmp[i]);	
		}
		System.out.println(tp1.c(t));
	}
	
	public int c(int[] t) {
		int max = 0;
		int nbr1 =0;
		int nbr0 =0;
		int nbr1total = 0;
		int nbr0total = 0;
		int cpt = 0;
		
		int offset = 0;
		boolean minIs1 = false;
		//on regarde le nombre max de 1 et de 0
		for (int i =0 ; i< t.length ; i++) {
			if (t[i] == 0) nbr0total ++;
			else if (t[i] == 1) nbr1total ++;
		}
		minIs1 = nbr1total == Math.min(nbr0total, nbr1total);
		// on stock le minimum qui deviendra notre condition d'arret pour le calcul d'une suite. 
		if (nbr1total - nbr0total == 0 ) return t.length;
		offset = Math.min(nbr0total,nbr1total);
		for (int i =0 ; i < t.length; i++) {
			if (t[i] == 0) {
				nbr0 ++;
			}
			else if (t[i] == 1) {
				nbr1 ++;
			}
			if (nbr1 - nbr0 == 0 && max < nbr0+nbr1) {
				max = nbr0+nbr1;
			}
			if ( (minIs1 &&  nbr0>= offset) || (!minIs1 && nbr1 >= offset) ) {
				if (minIs1 && t[cpt] == 1) offset--;
				else if (!minIs1 && t[i] == 0) offset--;
				cpt ++;
				i = cpt; 
				nbr0 = 0;
				nbr1 = 0;
			}
		}
		return max;
	}
}
