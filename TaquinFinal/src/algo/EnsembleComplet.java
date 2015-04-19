package algo;

import java.util.*;

import jeu.*;

public class EnsembleComplet implements EnsembleMarque {
	
	private ArrayList<Jeu> ensemble;

	public EnsembleComplet(){
		ensemble=new ArrayList<Jeu>();
	}
	
	public void ajout(Jeu pSommmet) {
		ensemble.add(pSommmet);
	}

	public boolean appartient(Jeu pATester) {
		return ensemble.contains(pATester);
	}

}
