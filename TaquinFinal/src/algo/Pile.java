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

	public int positionTraite() {
		return nombrePositionTraite;
	}

}
