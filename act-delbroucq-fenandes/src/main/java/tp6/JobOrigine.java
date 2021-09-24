package tp6;

/**
 * Donnée d'origine d'une tâche. 
 * @author alixd
 *
 */
public class JobOrigine {
	/**
	 * Date de début de la tâche 
	 */
	private int debut;
	/**
	 * Durée d'exécution de la tâche
	 */
	private int duree;
	
	/**
	 * Construire une tâche d'origine. 
	 * @param debut debut de la tâche
	 * @param duree durée de la tâche
	 */
	public JobOrigine(int debut, int duree) {
		super();
		this.debut = debut;
		this.duree = duree;
	}
	/**
	 * 
	 * @return
	 */
	public int getDebut() {
		return debut;
	}
	/**
	 * 
	 * @param debut
	 */
	public void setDebut(int debut) {
		this.debut = debut;
	}
	/**
	 * 
	 * @return
	 */
	public int getDuree() {
		return duree;
	}
	/**
	 * 
	 * @param duree
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
}
