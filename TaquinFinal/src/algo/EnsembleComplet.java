package algo;

import java.util.*;

import jeu.*;

public class EnsembleComplet implements EnsembleMarque {
	
	private ArrayList<Taquin> ensemble;

	public EnsembleComplet(){
		ensemble=new ArrayList<Taquin>();
	}
	
	public void ajout(Taquin pSommmet) {
		ensemble.add(pSommmet);		
	}

	public boolean appartient(Taquin pATester) {
		return ensemble.contains(pATester);
	}

}
