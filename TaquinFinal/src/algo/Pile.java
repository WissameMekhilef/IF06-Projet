package algo;

import jeu.*;

import java.util.*;

public class Pile implements EnsembleATraiter{
	private Stack<Jeu> pile;
	private int nombrePositionTraite;
	
	public Pile(){
		pile=new Stack<Jeu>();
		nombrePositionTraite=0;
	}

	/**
	 * Permet de savoir si la pile est vide ou non
	 * @return
	 * true si l'ensemble est non vide
	 */
	public boolean nonVide() {
		return !pile.isEmpty();
	}

	/**
	 * Renvoie le Taquin de la pile
	 * @return
	 * Le Jeu au dessus de la pile
	 */
	public Jeu prend() {
		return pile.pop();
	}
	
	/**
	 * Ajoute un Taquin �� la pile
	 * @param p
	 * Le jeu à ajouter
	 * @return
	 * true si le jeu est bien ajouté
	 */
	public boolean ajout(Jeu p) {
		return pile.add(p);
	}

	/**
	 * Donne le nombre de positions traité
	 * @return
	 * Le nombre de positions traité
	 */
	public int positionTraite() {
		return nombrePositionTraite;
	}

	/**
	 * Fonction de premier ajout à l'ensemble
	 * @param initial
	 * Le jeu initial à ajouter
	 * @return
	 * Un boolean qui atteste l'ajout du Jeu
	 */
	public boolean premierAjout(Jeu initial) {
		return ajout(initial);
	}

	/**
	 * Fonction test d'appartenance
	 * @param p
	 * Le jeu a tester
	 * @return
	 * true si le jeu est dans l'ensemble et false sinon
	 */
	public boolean appartient(Jeu p) {
		return pile.contains(p);
	}

}
