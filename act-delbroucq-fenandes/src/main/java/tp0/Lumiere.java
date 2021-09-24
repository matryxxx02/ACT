package tp0;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Lumiere {
	
	public static void main(String[] args) {
		Lumiere tp1 = new Lumiere();
		Scanner scanner = new Scanner(System.in); 
		int n = Integer.parseInt(scanner.nextLine().replaceAll("\\s+",""));
		String[] tmp = null;
		String tab = scanner.nextLine();
		if (tab.charAt(0) == ' ') tmp= tab.substring(1).split(" ");
		else tmp= tab.split(" ");
		Tuple[] list = new Tuple[n*2];
		
		for (int i =0; i< tmp.length-1; i= i+2) {
				list[i]=(tp1.new Tuple (1, Integer.parseInt(tmp[i])));
				list[i+1]=(tp1.new Tuple (-1, Integer.parseInt(tmp[i+1])));
		}
		
		Arrays.sort(list, new Comparator<Tuple>() {
			public int compare(Tuple o1, Tuple o2) {
				if (o1.getValue() < o2.getValue()) return -1;
				else if (o1.getValue() > o2.getValue()) return 1;
				return 0;
			}});
		
		Tuple precedent = null;
		for (int i =0 ; i< list.length ; i++) {
			if (precedent != null &&precedent.getValue() == list[i].getValue()) {
				list[i] = tp1.new Tuple (list[i].getExitEnter() + precedent.getExitEnter(), list[i].getValue());
				list[i-1] = null;
			}
			precedent = list[i];
		}
		System.out.println(tp1.d(list));
	}
	
	public int d (Tuple[] list) {
		int nbrInside = 0;
		int turnOnLight=0;
		for (Tuple t : list) {
			if (t != null) {
				if (nbrInside == 0) {
					nbrInside += t.getExitEnter();
					if (nbrInside > 0) {
						turnOnLight ++;
					}
				}
				else {
					nbrInside += t.getExitEnter();
				}
			}
		}
		return turnOnLight;
	}
	private class Tuple {
		private int exitEnter = 0;
		private int value; 
		
		public Tuple(int e , int v) {
			this.exitEnter=e;
			this.value=v;
		}
		
		public int getExitEnter() {
			return this.exitEnter;
		}
		public int getValue() {
			return this.value;
		}
	}
}




//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Scanner;
//
//public class Lumiere {
//	
//	public static void main(String[] args) {
//		Lumiere tp1 = new Lumiere();
//		Scanner scanner = new Scanner(System.in); 
//		int n = Integer.parseInt(scanner.nextLine().replaceAll("\\s+",""));
//		String[] tmp = null;
//		String tab = scanner.nextLine();
//		if (tab.charAt(0) == ' ') tmp= tab.substring(1).split(" ");
//		else tmp= tab.split(" ");
//		List<Tuple> list = new ArrayList<Tuple>();
//		
//		for (int i =0; i< tmp.length-1; i= i+2) {
//				list.add(tp1.new Tuple (1, Integer.parseInt(tmp[i])));
//				list.add(tp1.new Tuple (-1, Integer.parseInt(tmp[i+1])));
//		}
//		
//		Collections.sort(list, new Comparator<Tuple>() {
//			public int compare(Tuple o1, Tuple o2) {
//				if (o1.getValue() < o2.getValue()) return -1;
//				else if (o1.getValue() > o2.getValue()) return 1;
//				return 0;
//			}});
//		
//		Tuple precedent = null;
//		List <Integer> toRemove = new ArrayList<Integer>();
//		for (int i =0 ; i< list.size() ; i++) {
//			if (precedent != null &&precedent.getValue() == list.get(i).getValue()) {
//				list.set(i,tp1.new Tuple (list.get(1).getExitEnter() + precedent.getExitEnter(), list.get(i).getValue()));
//				toRemove.add(i-1);
//			}
//			precedent = list.get(i);
//		}
//		for (Integer i : toRemove) {
//			list.remove(i);
//		}
//		System.out.println(tp1.d(list));
//	}
//	
//	public int d (List <Tuple> list) {
//		int nbrInside = 0;
//		int turnOnLight=0;
//		for (Tuple t : list) {
//			if (nbrInside == 0) {
//				nbrInside += t.getExitEnter();
//				if (nbrInside > 0) {
//					turnOnLight ++;
//				}
//			}
//			else {
//				nbrInside += t.getExitEnter();
//			}
//		}
//		return turnOnLight;
//	}
//	private class Tuple {
//		private int exitEnter = 0;
//		private int value; 
//		
//		public Tuple(int e , int v) {
//			this.exitEnter=e;
//			this.value=v;
//		}
//		
//		public int getExitEnter() {
//			return this.exitEnter;
//		}
//		public int getValue() {
//			return this.value;
//		}
//	}
//}
