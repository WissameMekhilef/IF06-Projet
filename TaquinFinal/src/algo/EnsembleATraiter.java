package algo;

import jeu.*;

public interface EnsembleATraiter {
	public boolean nonVide();
	public Jeu prend();
	public boolean appartient(Jeu p);
	public boolean ajout(Jeu p);
}
