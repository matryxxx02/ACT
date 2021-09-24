package tp6;

public interface Certificat {
	//pour l’énumération on utilisera un ordre total sur les certificats
	//transforme le certificat en son successeur pour l’ordre
	//déclenche une erreur ou indéfini si le certificat est le dernier
	public void suivant();
	
	//retourne True Ssi le certificat n’a pas de successeur pour l’ordre
	public boolean estDernier();
	
	public void reset(); //réinitialise le certificat au plus petit pour l’ordre
	//retourne True Ssi le certificat est correct pour l’instance du pb associée
	//remarque: un certificat est donc ici associé toujours a une instance du pb
	
	public boolean estCorrect();
	
	//modifie aléatoirement la valeur du certificat, chaque valeur doit pouvoir être générée
	public void alea();
	
	public void saisie(); //permet la saisie d’un certificat
	
	public void affiche(); //affiche un certificat

}
