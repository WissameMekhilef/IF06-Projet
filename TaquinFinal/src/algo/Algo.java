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
	
	/**
	 * Constructeur d'un Algo
	 * @param pInit
	 * Le jeu qu'il faut résoudre à son état initial
	 * @param pTraiter
	 * L'ensemble à traiter à utiliser
	 * @param pMarque
	 * L'ensemble marqué à utiliser
	 * @param pAutomate
	 * Un boolean pour savoir si l'algorithme utilise un automate ou non
	 */
	public Algo(Jeu pInit, EnsembleATraiter pTraiter, EnsembleMarque pMarque, boolean pAutomate){
		this.initial=pInit;
		aTraiter=pTraiter;
		marque=pMarque;
		solution=new ArrayList<Action>();
		if(pAutomate)
			//On crée l'automate
			automate = new Noeud(pTraiter,pInit.getCommande());
		else
			//Sinon on passe l'automate à null
			automate=null;
		nombrePositionsTraite=0;
		nbIterations = 0;
	}
	
	/**
	 * Méthode run progressif
	 * <p>
	 * Cette méthode vaut pour la partie E du sujet
	 * </p>
	 */
	public void runProgressif(){
		aTraiter=new PileAction(initial.getNbCoupsfinale());
		while(!run(0)){
			System.out.println("On relance un parcours");
			aTraiter=new PileAction(initial.getNbCoupsfinale()+1);
		}
	}
	
	
	/**
	 * Fonction run de l'algo
	 * <p>
	 * C'est cette fonction qui permet la résolution
	 * </p>
	 * @param temps
	 * C'est le paramétre de temps durant lequel la résolution doit tourner
	 * @return
	 * On retourne un boolean true pour savoir si l'algorithme a trouver une solution ou pas
	 */
	public boolean run(int temps){
		if(temps != 0) {
			Timer t = new Timer();
			t.schedule(new Arret(), temps);
		}
		long timeDeb=System.currentTimeMillis();
		System.out.println("Taquin depart :\n"+initial);
			boolean fin=false;
			marque.ajout(this.initial);
			aTraiter.premierAjout(initial);
			ArrayList<Jeu> succ=initial.succ();
			for(Jeu p : succ)
				aTraiter.ajout(p);
			while(aTraiter.nonVide() && !fin){
				Jeu pos = aTraiter.prend();
				succ = pos.succ();
				if(automate!=null){
					ArrayList<Jeu> succR = reduireSucc(succ);
					succ=succR;
				}
				for(Jeu p: succ){
					nbIterations++;
					if(!marque.appartient(p)){
						if(p.estResolu()){
							fin=true;
							finale=p;
							Toolkit.getDefaultToolkit().beep();
							System.out.println("Solution :\n"+p);
						}else{
							marque.ajout(p);
							aTraiter.ajout(p);
						}
					}
				}
			setSolution();
		}
		long timeFin = System.currentTimeMillis();
		tempExec=timeFin-timeDeb;
		return fin;
	}
	
	/**
	 * Fonction de description
	 * <p>
	 * Récapitule le déroulement de d'algorithme
	 * </p>
	 * @return
	 * Un string pour pouvoir afficher la description
	 */
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
	
	/**
	 * Getteur sur l'automate
	 * @return
	 * L'automate
	 */
	public Automate getAutomate() {
		return automate;
	}
	
	/**
	 * Setteur de l'automate
	 * @param automate
	 * L'automate à utiliser
	 */
	public void setAutomate(Automate automate) {
		this.automate = automate;
	}
	
	/**
	 * Fonction d'élagage
	 * <p>
	 * C'est cette méthode qui fais appel à l'automate
	 * </p>
	 * @param aReduire
	 * La liste des positions à traiter
	 * @return
	 * La liste avec les positi//TODO
	 */
	private ArrayList<Jeu> reduireSucc(ArrayList<Jeu> aReduire){
		ArrayList<Jeu> res = new ArrayList<Jeu>();
		for(Jeu j : aReduire){
			//System.out.println("action de j : "+ j.getAction());
			if (automate.suivant(j, j.getAction()))
				res.add(j);
		}
		return res;
	}
	
	/**
	 * Fonction d'initialisation de la solution
	 * <p>
	 * Cette fonction permet de remonter tous les pères à partir de l'état final jusqu'au dernier pour avoir le chemin menant
	 * à la solution
	 * </p>
	 */
	public void setSolution(){
		Jeu parcours=finale;
		Stack<Action> temp = new Stack<Action>();
		while(parcours!=null){
			temp.push(parcours.getAction());
			parcours=parcours.getPere();
		}
		while(!temp.isEmpty()){
			solution.add(temp.pop());
		}
		
	}
	
	
	/**
	 * Fonction solution to string
	 * @return
	 * Une chaine de caractère représentant la solution
	 */
	public String getStringSolution(){
		String s="";
		for(Action act : solution){
			if(act!=null)
				s+=act.getAction();
		}
		return s;
	}
	
	/**
	 * Getteur solution
	 * @return
	 * La solution dans une arraylist
	 */
	public ArrayList<Action> getSolution(){
		return solution;
	}
	
	/**
	 * Getteur nombre positions traité
	 * @return
	 * Le nombre de positions traité
	 */
	public int getNombrePositionTraite(){
		return this.aTraiter.positionTraite();
	}

	/**
	 * Getteur position finale
	 * @return
	 * Retourne la position finale, trouvé par l'algorithme
	 */
	public Jeu getFinale() {
		return finale;
	}

	/**
	 * Getteur temps execution
	 * @return
	 * Le temps qu'a mis l'algorithme pour terminer
	 */
	public long getTempExec() {
		return tempExec;
	}
	
}
