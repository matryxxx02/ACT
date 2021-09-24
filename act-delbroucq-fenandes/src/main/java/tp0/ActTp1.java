package tp0;

import java.util.Scanner; 
public class ActTp1 
{
	public static void main( String[] args )
	{
		ActTp1 tp1 = new ActTp1();
		Scanner scanner = new Scanner(System.in); 
		int c = Integer.parseInt(scanner.nextLine().replaceAll("\\s+","")); 
		int n = Integer.parseInt(scanner.nextLine().replaceAll("\\s+",""));
		String[] tmp = null;
		String tab = scanner.nextLine();
		if (tab.charAt(0) == ' ') tmp= tab.substring(1).split(" ");
		else tmp= tab.split(" ");
		int[] t = new int[n];
		for (int i =0; i< n; i++) {
			t[i]= Integer.parseInt(tmp[i]);
		}
		//System.out.println(tp1.a(c, t));
		System.out.println(tp1.c(c, t));
	}

	/**
	 * 
	 * @param t Le tableau contient des nombres distincts et non triés
	 * @param c l'entier à trouvé
	 * @return
	 */
	public int a(int c ,int[] t ) {
		int n=t.length;
		for (int x =0 ; x< n ; x++) {
			for (int y =0 ; y< n; y++) {
				if(x != y) if (t[x]+t[y] ==c) return 1;
			}
		}
		return 0;
	}
	/**
	 * 
	 * @param t le tableau est trié
	 * @param c
	 * @return
	 */
	public int b( int c ,int[] t) {
		//int idxmid = (t.length-1)/2
		int idxsmall =0;
		int idxbig= t.length-1;
		int equals = 0;
		while((idxsmall < idxbig) && equals == 0 ) {
			if (t[idxsmall]+ t[idxbig] < c) idxsmall ++;
			if (t[idxsmall]+ t[idxbig] > c) idxbig --;

			if( t[idxsmall] + t[idxbig] == c ) return 1;
		}
		return equals;
	}	

	public int c (int c, int[] t) {
		return 0;
	}
}
