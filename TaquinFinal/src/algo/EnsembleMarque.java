package algo;

import jeu.*;

public interface EnsembleMarque {
	void ajout(Jeu pSommmet);
	boolean appartient(Jeu pATester);
	int taille();
}
