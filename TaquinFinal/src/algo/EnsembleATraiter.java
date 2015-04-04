package algo;

import jeu.Taquin;

public interface EnsembleATraiter {
	public boolean nonVide();
	public Taquin prend();
	public boolean appartient(Taquin p);
	public boolean ajout(Taquin p);
}
