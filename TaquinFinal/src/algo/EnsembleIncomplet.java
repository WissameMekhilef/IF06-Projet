package algo;


import jeu.Taquin;

public class EnsembleIncomplet implements EnsembleMarque {

	private int[] ensemble;
	
	public EnsembleIncomplet (int pTaille){
		ensemble=new int[pTaille];
	}
	@Override
	public void ajout(Taquin pSommet) {
		int indice=pSommet.hashCode()%ensemble.length;
		if(indice<0)
			indice=indice*-1;
		//System.out.println("On ajoute a l'indice : "+indice);
		if(ensemble[indice]==0)
			ensemble[indice]=1;
	}

	@Override
	public boolean appartient(Taquin pATester) {
		int indice=pATester.hashCode()%ensemble.length;
		if(indice<0)
			indice=indice*-1;
		//System.out.println("On cherche à l'indice : "+indice);
		return ensemble[indice]==1;
	}
}
