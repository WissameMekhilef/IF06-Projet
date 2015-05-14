package algo;

import jeu.*;

import java.util.*;

public class Pile implements EnsembleATraiter{
	
	private int nombrePositionTraite;
	private Stack<Jeu> pile;
	
	public Pile() {
		pile = new Stack<Jeu>();
		nombrePositionTraite = 0;
	}
	
	/**
	 * Ajoute un Taquin a la pile
	 * @param p
	 * Le jeu a ajouter
	 * @return
	 * true si le jeu a bien ete ajoute
	 */
	public boolean ajout(Jeu p) {
		return pile.add(p);
	}
	
	/**
	 * Fonction test d'appartenance d'un jeu a la pile
	 * @param p
	 * Le jeu a tester
	 * @return
	 * true si le jeu est dans l'ensemble et false sinon
	 */
	public boolean appartient(Jeu p) {
		return pile.contains(p);
	}

	/**
	 * Permet de savoir si la pile est vide ou non
	 * @return
	 * true si l'ensemble est non vide, false sinon
	 */
	public boolean nonVide() {
		return !pile.isEmpty();
	}
	
	/**
	 * Fonction de premier ajout a l'ensemble
	 * @param initial
	 * Le jeu initial a ajouter
	 * @return
	 * true si le jeu a ete ajoute, false sinon
	 */
	public boolean premierAjout(Jeu initial) {
		return ajout(initial);
	}
	
	/**
	 * Donne le nombre de positions traitees
	 * @return
	 * Le nombre de positions traitees
	 */
	public int positionTraite() {
		return nombrePositionTraite;
	}
	
	/**
	 * Renvoie le sommet de la pile
	 * @return
	 * Le sommet de la pile
	 */
	public Jeu prend() {
		return pile.pop();
	}

}
