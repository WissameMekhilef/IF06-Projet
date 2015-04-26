package algo;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Timer;

import jeu.Action;
import jeu.Jeu;
import automate.Automate;
import automate.Noeud;

public class Algo extends Thread{
	private EnsembleMarque marque;
	private EnsembleATraiter aTraiter;
	private Jeu initial;
	private Jeu finale;
	private ArrayList<Action> solution;
	private long tempExec;
	private Automate automate;
	private int nombrePositionsTraite;
	private int nbIterations;
	
	public Algo(Jeu pInit, EnsembleATraiter pTraiter, EnsembleMarque pMarque){
		this.initial=pInit;
		aTraiter=pTraiter;
		marque=pMarque;
		solution=new ArrayList<Action>();
		automate = new Noeud(pTraiter,pInit.getCommande());
		nombrePositionsTraite=0;
		nbIterations = 0;
	}
	
	public void run(int temps){
		if(temps != 0) {
			Timer t = new Timer();
			t.schedule(new Arret(), temps);
		}
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
					nbIterations++;
					if(!marque.appartient(p)){
						if(p.estResolu()){
							fin=true;
							finale=p;
							Toolkit.getDefaultToolkit().beep();
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
	
	public String description() {
		if(finale==null)
			return "L'algorithme n'a pas trouvé de solution";
		return initial.description() + "\n\nL'algorithme a dure " + nbIterations
				+ " iterations, au cours desquelles il a traite " + aTraiter.positionTraite()
				+ " positions du jeu.\nSon execution a pris " + tempExec
				+ " ms.\n\nAu cours de son execution, l'algorithme est parti "
				+ (automate.getFail().size() - 4)
				+ " fois dans une mauvaise direction.\n\nAu final, il aura supprime " + automate.getFail().size()
				+ " combinaisons de coups redondants afin de donner une solution optimale de longueur "
				+ getStringSolution().length() + ".\nChemin : " + getStringSolution();
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
		Stack<Action> temp = new Stack<Action>();
		while(parcours!=null){
			temp.push(parcours.getAction());
			parcours=parcours.getPere();
		}
		
		solution = new ArrayList<Action>(); 
		while(!temp.isEmpty()){
			solution.add(temp.pop());
		}
		
	}
	
	public String getStringSolution(){
		String s="";
		for(Action act : solution){
			if(act!=null)
				s+=act.getAction();
		}
		return s;
	}
	
	public ArrayList<Action> getSolution(){
		return solution;
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
