package tp7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HillClimbing  {

	private ResultOrd solution;

	private PblJSP problem;

	public HillClimbing(PblJSP problem, ResultOrd solution) {
		this.problem = problem;
		this.solution = solution;
	}


	public ResultOrd getSolution() {
		return solution;
	}


	public PblJSP getProblem() {
		return problem;
	}


	public ResultOrd algorithme() {
		List<TacheOrd> taches = this.solution.getTaches();
		//System.out.println(taches.toString());
		// PARTIR d'une solution bonne
		int qualitySolution = this.problem.getQuality(solution);
		int qualityVoisin = qualitySolution;

		// TANT QUE...
		while (true) {
			boolean meilleureSolution = true;
			// POUR CHAQUE voisin DE solution
			for (int idxCurrent = 0; idxCurrent < taches.size() - 1; idxCurrent++) {
				int idxVoisin = this.trouverProchainVoisin(idxCurrent);
				ArrayList<TacheOrd> voisin= new ArrayList<TacheOrd>(taches);
				Collections.swap(voisin, idxCurrent, idxVoisin);
				majRetardAfterSwap(voisin, idxCurrent, idxVoisin);
				qualityVoisin = this.problem.getQuality(new ResultOrd(voisin));

				if (qualitySolution > qualityVoisin) {
						qualitySolution = qualityVoisin;
						this.solution = new ResultOrd(voisin);
						meilleureSolution = false;
					}
				}
			if (meilleureSolution) return this.solution;
		}
	}

	private void majRetardAfterSwap (ArrayList<TacheOrd>list, int idxCurrent, int idxVoisin) {
		 int completion = list.get(idxVoisin).getCompletion();

		 for(int i = idxCurrent; i<=idxVoisin; i++) {
			 list.set(i, majRetardCompletionTache(list.get(i),completion,Math.max(0,completion - list.get(i).getTempsLimite())));
			 completion += list.get(i).getTempsExecution();
		 }

	}

	private TacheOrd majRetardCompletionTache (TacheOrd old, int completion, int retard){
		return new TacheOrd(old.getTache(), completion, retard);
	}

	/**
	 * Prochain voisin correspond à la prochaine tache en retard
	 * @param current
	 * @return
	 */
	private int trouverProchainVoisin(int current) {
		for (int i =current+1; i< this.problem.getTaches().size(); i++) {
			List<TacheOrd> taches = this.solution.getTaches();
			if (taches.get(i).getRetard() >0 ) return i;
		}
		return current;
	}

	public int getQuality() {
		return this.problem.getQuality(algorithme());
	}


}
