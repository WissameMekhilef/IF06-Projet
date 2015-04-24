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

	public boolean suivant(Jeu pJeu, Action action) {
		//System.out.println("chemin : "+chemin);
		//System.out.println("action : "+action.getAction());
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
