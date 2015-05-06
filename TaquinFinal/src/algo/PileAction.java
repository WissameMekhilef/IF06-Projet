package algo;

import java.util.Stack;

import jeu.Action;
import jeu.Jeu;
import exceptions.MauvaiseTouche;

public class PileAction implements EnsembleATraiter{
	private Jeu initial=null;
	private Stack<Stack <Action>> ensemble= new Stack<>();
	private int PosTraite=0;
	private int TAILLE_LIMITE=Integer.MAX_VALUE;
	
	public PileAction(int maxSize){
		TAILLE_LIMITE=maxSize;
	}
	
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
				res=res.deplacement(aAppliquer.pop());
			} catch (IndexOutOfBoundsException | MauvaiseTouche e) {}
		}
		aAppliquer=null;
		PosTraite++;
		return res;
	}

	public boolean ajout(Jeu p) {
		//System.out.println("Le jeu a "+p.getProfondeur()+" pere(s)");
		//System.out.println("Ajout");
		Stack<Action> temp = new Stack<Action>();
		while(p.getPere()!=null){
			temp.add(p.getPere().getAction());
			p=p.getPere();
		}
		//System.out.println(this);
		if(temp.size()<TAILLE_LIMITE){
			//System.out.println("Je suis long de "+temp.size());
			return ensemble.add(temp);
		}
		//System.out.println("TRop grand tu rentre pas");
		
		return false;
	}

	public int positionTraite() {
		return PosTraite;
	}
	
	public String toString(){
		String res="";
		for(Stack<Action> ligne : ensemble){
			if(ligne!=null)
				for(Action act : ligne){
					if(act!=null)
						res+=act.getAction()+" ";
				}
			res+="\n";
		}
		return res;
	}
}
