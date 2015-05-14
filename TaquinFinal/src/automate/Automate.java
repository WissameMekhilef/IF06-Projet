package automate;

import java.util.ArrayList;

import jeu.Action;
import jeu.Jeu;

public interface Automate {
	
	public ArrayList<String> getFail();
	public boolean suivant(Jeu pJeu, Action action);
	
}
