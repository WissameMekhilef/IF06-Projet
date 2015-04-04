package algo;

import java.util.*;
import jeu.*;

public class Algo{
	private EnsembleMarque marque;
	private EnsembleATraiter aTraiter;
	private Taquin initial;
	
	public Algo(Taquin pInit, EnsembleATraiter pTraiter, EnsembleMarque pMarque){
		this.initial=pInit;
		aTraiter=pTraiter;
		marque=pMarque;
	}
	
	public void run(){
		marque.ajout(this.initial);
		ArrayList<Taquin> succ=initial.succ();
		for(Taquin p : succ)
			aTraiter.ajout(p);
		while(aTraiter.nonVide()){
			Taquin pos = aTraiter.prend();
			succ = pos.succ();
			for(Taquin p: succ){
				if(!marque.appartient(p)){
					marque.ajout(p);
					aTraiter.ajout(p);
				}
			}
		}
		System.out.println();
	}
}
