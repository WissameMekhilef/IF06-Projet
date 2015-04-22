package automate;

import java.util.ArrayList;

import algo.EnsembleMarque;

import jeu.Action;
import jeu.Commande;
import jeu.Jeu;

public class Noeud implements Automate {
	
	private EnsembleMarque marque;
	private String chemin;
	private ArrayList<String> fail;
	
	public Noeud(EnsembleMarque pMarque, Commande c){
		fail = new ArrayList<String>();
		for(Action a: c.getTabClef()){
			fail.add(a.getAction()+a.getInverse().getAction());
		}
		marque = pMarque;
		chemin="";
	}
	
	public String getChemin() {
		return chemin;
	}

	public ArrayList<String> getFail() {
		return fail;
	}

	public boolean suivant(Jeu pJeu, Action action) {
		//System.out.println("chemin : "+chemin);
		//System.out.println("action : "+action.getAction());
		if (!fail.contains(chemin+action.getAction())){
			if(marque.appartient(pJeu)){
				fail.add(chemin+action.getAction());
				return false;
			}
			chemin+=action;
			return true;
		}
		return false;
	}
}
