package algo;

import java.util.*;
import jeu.*;

public class Algo{
	private EnsembleMarque marque;
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
		while(finale!=null){
			solution.push(finale.getAction());
			finale=finale.getPere();
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
