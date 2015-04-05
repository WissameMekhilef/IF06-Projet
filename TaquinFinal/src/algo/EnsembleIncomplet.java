package algo;


import jeu.Taquin;

public class EnsembleIncomplet implements EnsembleMarque {

	private Object[] ensemble;
	
	public EnsembleIncomplet(int taille) {
		ensemble=new Object[taille];
	}

	public void ajout(Taquin dep, char action, Taquin arr) {
		// TODO Auto-generated method stub
		
	}

	public boolean appartient(Taquin pATester) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ajoutInit(Taquin init) {
		
		
	}
	
}
