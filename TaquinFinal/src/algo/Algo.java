package algo;

import java.util.*;

import automate.Automate;
import automate.Noeud;
import jeu.*;

public class Algo extends Thread{
	private EnsembleMarque marque;
	private EnsembleATraiter aTraiter;
	private Jeu initial;
	private Jeu finale;
	private Stack<Action> solution;
	private long tempExec;
	private Automate automate;
	
	public Algo(Jeu pInit, EnsembleATraiter pTraiter, EnsembleMarque pMarque){
		this.initial=pInit;
		aTraiter=pTraiter;
		marque=pMarque;
		solution=new Stack<Action>();
		automate = new Noeud(pTraiter,pInit.getCommande());
	}
	/**
	 * L'automate peut etre desactive en remplacant "succR" par "succ" a la ligne 44.
	 * Dans ce cas l'algo fonctionne mais est moins efficace.
	 */
	public void run(){
		long timeDeb=System.currentTimeMillis();
		System.out.println("Taquin depart :\n"+initial);
		if(initial.estResolu())
			System.out.println("Le jeu est déjà résolu");
		else{
			boolean fin=false;
			marque.ajout(this.initial);
			ArrayList<Jeu> succ=initial.succ();
			for(Jeu p : succ)
				aTraiter.ajout(p);
			while(aTraiter.nonVide() && !fin){
				Jeu pos = aTraiter.prend();
				succ = pos.succ();
				ArrayList<Jeu>succR = reduireSucc(succ);
				for(Jeu p: succR){
					if(!marque.appartient(p)){
						if(p.estResolu()){
							fin=true;
							finale=p;
							System.out.println("Solution :\n"+p);
						}
						marque.ajout(p);
						aTraiter.ajout(p);
					}
				}
			}
			setSolution();
		}
		long timeFin = System.currentTimeMillis();
		tempExec=timeFin-timeDeb;
	}
	
	public Automate getAutomate() {
		return automate;
	}

	public void setAutomate(Automate automate) {
		this.automate = automate;
	}

	private ArrayList<Jeu> reduireSucc(ArrayList<Jeu> aReduire){
		ArrayList<Jeu> res = new ArrayList<Jeu>();
		for(Jeu j : aReduire){
			//System.out.println("action de j : "+ j.getAction());
			if (automate.suivant(j, j.getAction()))
				res.add(j);
		}
		return res;
	}
	
	public void setSolution(){
		Jeu parcours=finale;
		while(parcours!=null){
			solution.push(parcours.getAction());
			parcours=parcours.getPere();
		}
	}
	
	public String getSolution(){
		String s="";
		while(!solution.isEmpty()){
			if(solution.peek() != null) s+=solution.pop().getAction();
			else solution.pop();
		}
		return s;
	}
	
	public int getNombrePositionTraite(){
		return this.aTraiter.positionTraite();
	}

	public Jeu getFinale() {
		return finale;
	}

	public long getTempExec() {
		return tempExec;
	}
	
}
