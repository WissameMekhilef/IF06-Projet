package algo;

import java.util.*;
import jeu.*;

public class Algo extends Thread{
	private EnsembleMarque marque;
	public Taquin getFinale() {
		return finale;
	}

	private EnsembleATraiter aTraiter;
	private Taquin initial;
	private Taquin finale;
	private Stack<String> solution;
	
	public Algo(Taquin pInit, EnsembleATraiter pTraiter, EnsembleMarque pMarque){
		this.initial=pInit;
		aTraiter=pTraiter;
		marque=pMarque;
		solution=new Stack<String>();
	}
	
	public void run(){
		System.out.println("Taquin depart :\n"+initial);
		boolean fin=false;
		marque.ajout(this.initial);
		ArrayList<Taquin> succ=initial.succ();
		for(Taquin p : succ)
			aTraiter.ajout(p);
		while(aTraiter.nonVide() && !fin){
			Taquin pos = aTraiter.prend();
			succ = pos.succ();
			for(Taquin p: succ){
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
	
	public void setSolution(){
		Taquin parcours=finale;
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
	
}
