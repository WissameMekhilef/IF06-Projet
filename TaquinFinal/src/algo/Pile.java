package algo;

import jeu.Taquin;
import java.util.*;

public class Pile implements EnsembleATraiter{
	private Stack<Taquin> pile;
	
	public Pile(){
		pile=new Stack<Taquin>();
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
	public Taquin prend() {
		return pile.pop();
	}

	/**
	 * Teste l'appartenance de p à la pile, renvoie un boolean
	 */
	public boolean appartient(Taquin p) {
		return pile.contains(p);
	}

	/**
	 * Ajoute un Taquin à la pile
	 */
	public boolean ajout(Taquin p) {
		return pile.add(p);
	}

}
