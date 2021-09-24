package tp7;

import java.util.ArrayList;
import java.util.List;

public class ResultOrd {
	
	private List<TacheOrd> taches;
	
	public ResultOrd(ArrayList<TacheOrd> taches) {
		this.taches = taches;
	}

	public List<TacheOrd> getTaches() {
		return taches;
	}

	public void setTaches(List<TacheOrd> taches) {
		this.taches = taches;
	}

}
