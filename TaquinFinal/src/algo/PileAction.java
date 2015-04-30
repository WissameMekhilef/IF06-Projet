package algo;

import java.util.Stack;

import jeu.Action;
import jeu.Jeu;
import exceptions.MauvaiseTouche;

public class PileAction implements EnsembleATraiter{
	private Jeu initial=null;
	private Stack<Stack <Action>> ensemble= new Stack<>();
	private int PosTraite=0;
	
	public PileAction(){
		
	}
	
	public boolean premierAjout(Jeu initial){
		//System.out.println("Premier ajout");
		this.initial=initial;
		return true;
	}
	
	public boolean nonVide() {
		return !ensemble.isEmpty();
	}

	public Jeu prend() {
		//System.out.println("Prend");
		Jeu res=initial.clone();
		Stack<Action> aAppliquer=ensemble.pop();
		while(!aAppliquer.isEmpty()){
			try {
				res.deplacement(aAppliquer.pop());
			} catch (IndexOutOfBoundsException | MauvaiseTouche e) {}
		}
		aAppliquer=null;
		PosTraite++;
		return res;
	}

	public boolean ajout(Jeu p) {
		//System.out.println("Ajout");
		Stack<Action> temp = new Stack<Action>();
		while(p.getPere()!=null){
			temp.add(p.getPere().getAction());
			p=p.getPere();
		}
		return ensemble.add(temp);
	}

	public int positionTraite() {
		return PosTraite;
	}
	
	
}
