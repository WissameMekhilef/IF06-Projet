package automate;

import java.util.ArrayList;

import algo.EnsembleATraiter;
import jeu.Action;
import jeu.Commande;
import jeu.Jeu;

public class Noeud implements Automate {
	
	private EnsembleATraiter traite;
	private String chemin;
	private ArrayList<String> fail;
	
	/**
	 * Constructeur d'un Noeud avec paramètre
	 * @param pTraite
	 * L''ensemble sur lequel travail l'automate
	 * @param c
	 * Une pointeur vers les commandes du jeu
	 */
	public Noeud(EnsembleATraiter pTraite, Commande c){
		fail = new ArrayList<String>();
		for(Action a: c.getTabClef()){
			fail.add(a.getAction()+a.getInverse().getAction());
		}
		traite = pTraite;
		chemin="";
	}
	
	public String getChemin() {
		return chemin;
	}

	public ArrayList<String> getFail() {
		return fail;
	}

	/**
	 * 
	 * @param pJeu
	 * @param action
	 */
	public boolean suivant(Jeu pJeu, Action action) {
		if (!fail.contains(chemin+action.getAction())){
			if(traite.appartient(pJeu)){
				fail.add(chemin+action.getAction());
				return false;
			}
			chemin+=action.getAction();
			return true;
		}
		return false;
	}
}
