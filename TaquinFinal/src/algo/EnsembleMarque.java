package algo;

import jeu.*;

public interface EnsembleMarque {
	void ajoutInit(Taquin init);
	void ajout(Taquin dep, char action, Taquin arr);
	boolean appartient(Taquin pATester);
}
