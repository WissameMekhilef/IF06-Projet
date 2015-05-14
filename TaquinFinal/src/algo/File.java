package algo;

import java.util.ArrayList;

import jeu.*;


public class File implements EnsembleATraiter{
	
	private ArrayList<Jeu> file;
	private int nombrePositionTraite;
	
	
	/**
	 * Constrcuteur sans paramètres
	 */
	public File(){
		file= new ArrayList<Jeu>();
		nombrePositionTraite=0;
	}

	/**
	 * Permet de savoir si la pile est vide ou non
	 * @return
	 * true si l'ensemble est non vide
	 */
	public boolean nonVide() {
		return !file.isEmpty();
	}

	/**
	 * Renvoie le Taquin de la pile
	 * @return
	 * Le Jeu au début de la pile
	 */	
	public Jeu prend() {
		nombrePositionTraite++;
		return file.remove(0);
	}
	/**
	 * Ajoute un Taquin �� la pile
	 * @param p
	 * Le jeu à ajouter
	 * @return
	 * true si le jeu est bien ajouté
	 */

	public boolean ajout(Jeu p) {
		return file.add(p);
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
		return file.contains(p);
	}

}
