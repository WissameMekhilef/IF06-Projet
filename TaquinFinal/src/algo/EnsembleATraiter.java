package algo;

import jeu.*;

public interface EnsembleATraiter {
	
	public boolean ajout(Jeu p);
	
	public boolean appartient(Jeu p);
	
	public boolean nonVide();
	
	public boolean premierAjout(Jeu initial);
	
	public int positionTraite();
	
	public Jeu prend();
	
}
