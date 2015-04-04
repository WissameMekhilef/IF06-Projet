package algo;


import jeu.Taquin;

public class EnsembleIncomplet implements EnsembleMarque {

	@Override
	public void ajout(Taquin pSommmet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean appartient(Taquin pATester) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*private Info[] ensemble;
	
	public EnsembleIncomplet(int taille) {
		ensemble=new Info[taille];
	}
	
	@Override
	public void ajout(Taquin depart,String action, Taquin arrive) {
		int pos =depart.hashCode()% ensemble.length;
		if(ensemble[pos]== null){
			
		}
	}

	@Override
	public boolean appartient(Taquin pATester) {
		return false;
	}*/

}
