package algo;

import java.util.*;
import jeu.*;

public class Algo extends Thread{
	private EnsembleMarque marque;
	public Jeu getFinale() {
		return finale;
	}

	private EnsembleATraiter aTraiter;
	private Jeu initial;
	private Jeu finale;
	private Stack<String> solution;
	
	public Algo(Jeu pInit, EnsembleATraiter pTraiter, EnsembleMarque pMarque){
		this.initial=pInit;
		aTraiter=pTraiter;
		marque=pMarque;
		solution=new Stack<String>();
	}
	
	public void run(){
//		System.out.println("Taquin depart :\n"+initial);
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
				for(Jeu p: succ){
					if(!marque.appartient(p)){
						if(p.estResolu()){
							fin=true;
							finale=p;
//							System.out.println("Solution :\n"+p);
						}
						marque.ajout(p);
						aTraiter.ajout(p);
					}
				}
			}
			setSolution();
		}
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
			s+=solution.pop();
		}
		return s;
	}
	
	public int getNombrePositionTraite(){
		return this.aTraiter.positionTraite();
	}
	
}
