package tp7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PblJSP {
	private List<Tache> taches;

	public PblJSP(List<Tache> taches) {
		super();
		this.taches = taches;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public ResultOrd getRandOrd() {
		ArrayList<TacheOrd> result = new ArrayList<TacheOrd>();
		ArrayList<Tache> copyTaches = new ArrayList<Tache>(taches);
		int completion = 0;
		int retard = 0;

		for (Tache t : taches){
			int idx = new Random().nextInt(copyTaches.size());
			Tache tacheRand = copyTaches.get(idx);
			completion += tacheRand.getTempsExecution();
			retard = Integer.max(completion - tacheRand.getTempsLimite(), 0);
			result.add(new TacheOrd(tacheRand,completion,retard));
			copyTaches.remove(idx);
		}

		return new ResultOrd(result);
	}

	public ResultOrd getOrdHeuristi() {
		ArrayList<TacheOrd> result = new ArrayList<TacheOrd>();
		ArrayList<Tache> copyTaches = new ArrayList<Tache>(taches);
		
		HeuristiLimiteSurPoidRecursion(copyTaches, result, 0, 0);
		return new ResultOrd(result);
	}

	public ResultOrd getOrdHeuristi2() {
		ArrayList<TacheOrd> result = new ArrayList<TacheOrd>();
		ArrayList<Tache> copyTaches = new ArrayList<Tache>(taches);
		HeuristiTempsFoisPoidRecursion(copyTaches, result, 0, 0);
		return new ResultOrd(result);
	}

	public ArrayList<TacheOrd> HeuristiLimiteSurPoidRecursion(ArrayList<Tache> copyTaches, ArrayList<TacheOrd> result, int completion, int retard) {

		if(!copyTaches.isEmpty()){
			Tache tacheRand = minLimiteSurPoid(copyTaches);
			retard = Integer.max(completion - tacheRand.getTempsLimite(), 0);
			result.add(new TacheOrd(tacheRand,completion,retard));
			completion += tacheRand.getTempsExecution();
			copyTaches.remove(tacheRand);
			return HeuristiLimiteSurPoidRecursion(copyTaches, result, completion, retard);
		}else{
			return result;
		}
	}

	public ArrayList<TacheOrd> HeuristiTempsFoisPoidRecursion(ArrayList<Tache> copyTaches, ArrayList<TacheOrd> result, int completion, int retard) {

		if(!copyTaches.isEmpty()){
			Tache tacheRand = maxPoidFoisTemps(copyTaches);
			retard = Integer.max(completion - tacheRand.getTempsLimite(), 0);
			result.add(new TacheOrd(tacheRand,completion,retard));
			completion += tacheRand.getTempsExecution();
			copyTaches.remove(tacheRand);
			return HeuristiTempsFoisPoidRecursion(copyTaches, result, completion, retard);
		}else{
			return result;
		}
	}

	public Tache minLimiteSurPoid(ArrayList<Tache> taches) {
		Tache min = taches.get(0);
		int ratioMax = min.getTempsLimite()/min.getPoid();
		for (Tache t : taches){
			int ratio = t.getTempsLimite()/t.getPoid();
			if(ratio<ratioMax) {
				ratioMax = ratio;
				min = t;
			}
		}
		return min;
	}

	public Tache maxPoidFoisTemps(ArrayList<Tache> taches){
		Tache max = taches.get(0);
		int ratioMax = max.getPoid()/max.getTempsExecution();
		for (Tache t : taches){
			int ratio = max.getPoid()/t.getTempsExecution();
			if(ratio>ratioMax) {
				ratioMax = ratio;
				max = t;
			}
		}
		return max;
	}

	public int getQuality(ResultOrd result) {
		int somme=0;
		for (TacheOrd t : result.getTaches())
			somme += (t.getPoid()*t.getRetard());
		return somme;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
