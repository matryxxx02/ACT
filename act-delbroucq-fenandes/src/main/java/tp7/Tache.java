package tp7;

public class Tache {
    private int tempsExecution;
    private int poid;
    private int tempsLimite;

    public Tache(int tempsExecution, int poid, int temps){
        this.tempsExecution = tempsExecution;
        this.poid = poid;
        this.tempsLimite= temps;
    }

    public int getTempsExecution() {
        return tempsExecution;
    }

    public void setTempsExecution(int tempsExecution) {
        this.tempsExecution = tempsExecution;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public int getTempsLimite() {
        return tempsLimite;
    }

    public void setTemps(int temps) {
        this.tempsLimite = temps;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "tempsExecution=" + tempsExecution +
                ", poid=" + poid +
                ", tempsLimite=" + tempsLimite +
                '}';
    }
}
