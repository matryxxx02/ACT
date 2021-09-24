package tp6;

public class Job {
	private int dateDebut;
	private int numeroMachine;
	
	public Job(int dateDebut, int numeroMachine) {
		super();
		this.dateDebut = dateDebut;
		this.numeroMachine = numeroMachine;
	}
	
	public int getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(int dateDebut) {
		this.dateDebut = dateDebut;
	}
	public int getNumeroMachine() {
		return numeroMachine;
	}
	public void setNumeroMachine(int numeroMachine) {
		this.numeroMachine = numeroMachine;
	}
	public String toString() {
		return "(date_début : "+this.dateDebut+" numéro_machine : "+ this.numeroMachine +")\t ";
	}
	
}
