package algo;


import jeu.*;

public class EnsembleIncomplet implements EnsembleMarque {

	private int[] ensemble;
	private int taille;
	
	/**
	 * Constructeur d'un ensemble incomplet
	 * @param pTaille
	 * La taille souhaité pour l'ensemble
	 */
	public EnsembleIncomplet (int pTaille){
		taille=pTaille;
		ensemble=new int[taille];
	}

	/**
	 * Fonction d'ajout d'un jeu
	 * @param pSommet
	 * Le jeu à ajouter
	 */
	public void ajout(Jeu pSommet) {
		int indice=Math.abs(pSommet.hashCode()%taille);
		if(ensemble[indice]==0)
			ensemble[indice]=1;
	}

	/**
	 * Fonction d'appartenance d'un jeu
	 * @param pATester
	 * Le jeu sur lequel tester l'appartenance
	 * @return
	 * Un boolean à true l'ensemble contient le jeu, et à false sinon
	 */
	public boolean appartient(Jeu pATester) {
		int indice=Math.abs(pATester.hashCode()%taille);
		return ensemble[indice]==1;
	}
	
	/**
	 * Fonction de taille de l'ensemble
	 * @return
	 * Retourne le nombre de positions dans l'ensemble
	 */
	public int taille() {
		return taille;
	}
}
