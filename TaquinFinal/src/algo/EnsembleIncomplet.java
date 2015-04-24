package algo;


import jeu.*;

public class EnsembleIncomplet implements EnsembleMarque {

	private int[] ensemble;
	private int taille;
	
	public EnsembleIncomplet (int pTaille){
		taille=pTaille;
		ensemble=new int[taille];
	}
	public void ajout(Jeu pSommet) {
		int indice=Math.abs(pSommet.hashCode()%taille);
		if(ensemble[indice]==0)
			ensemble[indice]=1;
	}

	public boolean appartient(Jeu pATester) {
		int indice=Math.abs(pATester.hashCode()%taille);
		return ensemble[indice]==1;
	}
	
	public int taille() {
		return taille;
	}
}
