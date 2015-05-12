package algo;

import java.util.Comparator;
import java.util.PriorityQueue;

import jeu.*;

public class Tas implements EnsembleATraiter {
	
	private PriorityQueue<Jeu> file;
	private int nombrePositionTraite;
	
	/**
	 * Constructeur principal
	 * @param c
	 * Le coparateur à utiliser pour le tas à priorité
	 */
	public Tas(Comparator<Jeu> c){
		file=new PriorityQueue<Jeu>(11,c);
		nombrePositionTraite=0;
	}
	
	/**
	 * NonVide
	 * @return
	 * Un boolean true si le tas n'est pas vide, false sinon
	 */
	public boolean nonVide() {
		return !file.isEmpty();
	}

	/**
	 * Fonction de retrait d'un jeu
	 * @return
	 * On retourne le jeu retourner par le tas à priorité, il prend en compte le comparateur
	 */
	public Jeu prend() {
		nombrePositionTraite++;
		return file.poll();
	}

	/**
	 * Fonction d'ajout d'un jeu
	 * @param p
	 * Le jeu à ajouter
	 * @return
	 * On retourne un boolean attestant l'ajout du jeu
	 */
	public boolean ajout(Jeu p) {
		return file.add(p);
	}

	/**
	 * Getteur nombre de positions traité
	 * @return
	 * Le nombre de poisitions traité sous la forme d'un entier
	 */
	public int positionTraite() {
		return nombrePositionTraite;
	}

	/**
	 * Fonction de premier ajout
	 * <p>
	 * Fais appel à ajout(Jeu p)
	 * </p>
	 */
	public boolean premierAjout(Jeu initial) {
		return ajout(initial);
	}

	/**
	 * Fonction d'appartenance
	 * @param p
	 * Le jeu sur lequel il faut tester l'appartenance
	 * @return
	 * True si le jeu est dans le tas, false sinon
	 */
	public boolean appartient(Jeu p) {
		return file.contains(p);
	}

}
