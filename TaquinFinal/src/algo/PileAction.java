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
	
	
	/**
	 * Constructeur d'une pile avec taille limité
	 * @param maxSize
	 * La taille limite voulue
	 */
	public PileAction(int maxSize){
		TAILLE_LIMITE=maxSize;
	}
	
	/**
	 * Constructeur sans paramètre
	 */
	public PileAction(){
		
	}
	
	/**
	 * Premier ajout d'un jeu
	 * <p>
	 * Dans le cas d'une pile d'action on stocke l'etat initial pour pouvoir recalculer tout les autres
	 * @param initial
	 * Le jeu initial à ajouter
	 * @return
	 * On return un boolean qui permet de savoir que l'ajout c'est correctement passé
	 */
	public boolean premierAjout(Jeu initial){
		this.initial=initial;
		return true;
	}
	
	/**
	 * nonVide est une fonction qui permet de savoir si la liste n'est pas vide
	 * @return
	 * On retourne un boolean true si la liste n'est pas vide
	 */
	public boolean nonVide() {
		return !ensemble.isEmpty();
	}

	/**
	 * Prend
	 * <p>
	 * Prend est une fonction qui renvoie un jeu de l'ensemble, le type de l'ensemble différencie le type de parcours de graphe
	 * </p>
	 * @return
	 * On retourne le jeu concerné
	 */
	public Jeu prend() {
		//On part de l'état initial
		Jeu res=initial.clone();
		//On dépile la pile d'action à effectuer pour renvoyer le bon jeu
		Stack<Action> aAppliquer=ensemble.pop();
		//On effectue tout les déplacements
		while(!aAppliquer.isEmpty()){
			try {
				//TODO
				res=res.deplacement(aAppliquer.pop());
			} catch (IndexOutOfBoundsException | MauvaiseTouche e) {}
		}
		aAppliquer=null;
		PosTraite++;
		return res;
	}
	
	/**
	 * ajout
	 * <p>
	 * Fonction qui permet d'ajouter un jeu à l'ensemble, dans cet ensemble de pile d'action, on ajoute les actions qui on mené au jeu
	 * </p>
	 * @param p
	 * Le jeu à ajouter
	 * @return
	 * On retourne un boolean pour attester de l'ajout du jeu à l'ensemble
	 */
	public boolean ajout(Jeu p) {
		Stack<Action> temp = new Stack<Action>();
		while(p.getPere()!=null){
			temp.add(p.getPere().getAction());
			p=p.getPere();
		}
		//On teste la taille de la pile pour savoir si le jeu ne depasse pas la limite fixé
		if(temp.size()<TAILLE_LIMITE){
			return ensemble.add(temp);
		}
		return false;
	}
	
	/**
	 * positionTraite
	 * <p>
	 * Permet de savoir le nombre de positions qui sont sorti de l'ensemble
	 * </p>
	 * @return
	 * On retourne cet entier
	 */
	public int positionTraite() {
		return PosTraite;
	}
	
	/**
	 * Fonction qui permet de savoir si un jeu est dans la pile
	 * <p>
	 * Cette fonction est nécéssaire pour le fonctionnement de l'automate, mais cet ensemble ne tourne jamais avec l'automate
	 * donc la méthode retourne false pour ne pas perturber l'algorithme en supprimant des positions de jeu
	 * </p>
	 */
	public boolean appartient(Jeu p) {
		return false;
	}
}
