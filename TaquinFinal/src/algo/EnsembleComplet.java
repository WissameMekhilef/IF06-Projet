package algo;

import java.util.*;

import jeu.*;

public class EnsembleComplet implements EnsembleMarque {
	
	private ArrayList<Taquin> ensemble;

	public EnsembleComplet(){
		ensemble=new ArrayList<Taquin>();
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
		ensemble.add(init);
	}

}
