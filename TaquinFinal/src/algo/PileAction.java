package algo;

import java.util.ArrayList;

import exceptions.MauvaiseTouche;
import jeu.Action;
import jeu.Jeu;

public class PileAction implements EnsembleATraiter{
	private Jeu initial=null;
	private ArrayList<Action> ensemble = new ArrayList<Action>();
	private int PosTraite=0;
	
	public PileAction(){
		
	}
	
	public boolean premierAjout(Jeu initial){
		this.initial=initial;
		return true;
	}
	
	public boolean nonVide() {
		return !ensemble.isEmpty();
	}

	public Jeu prend() {
		Jeu res = initial;
		for(Action act : ensemble){
			try {
				res.deplacement(act);
			} catch (IndexOutOfBoundsException | MauvaiseTouche e) {}
		}
		return res;
	}

	public boolean ajout(Jeu p) {
		return ensemble.add(p.getAction());
	}

	public int positionTraite() {
		return 0;
	}
	
	
}
