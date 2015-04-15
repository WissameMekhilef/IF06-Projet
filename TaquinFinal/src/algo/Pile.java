package algo;

import jeu.*;
import java.util.*;

public class Pile implements EnsembleATraiter{
	private Stack<Jeu> pile;
	
	public Pile(){
		pile=new Stack<Jeu>();
	}

	/**
	 * Permet de savoir si la pile est vide ou non
	 */
	public boolean nonVide() {
		return !pile.isEmpty();
	}

	/**
	 * Renvoie le Taquin de la pile
	 */
	public Jeu prend() {
		return pile.pop();
	}

	/**
	 * Teste l'appartenance de p �� la pile, renvoie un boolean
	 */
	public boolean appartient(Jeu p) {
		return pile.contains(p);
	}

	/**
	 * Ajoute un Taquin �� la pile
	 */
	public boolean ajout(Jeu p) {
		return pile.add(p);
	}

}
