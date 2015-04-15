package main;

import java.awt.Event;
import java.io.PrintStream;
import java.util.*;

import comparateurs.*;
import algo.*;
import exceptions.*;
import jeu.*;

@SuppressWarnings("unused")
public class Main {
	private static HashMap<String, int[]> commande;
	/**
	 * Initialise les commandes du jeu
	 */
	private static void initialiserCommande(){
		//Initialisation des deplacements
		commande=new HashMap<String, int[]>();
		
		int[] t1=new int[2];
		t1[0]=-1;t1[1]=0;
		commande.put("z", t1);
		
		int[] t2=new int[2];
		t2[0]=1;t2[1]=0;
		commande.put("s", t2);
		
		int[] t3=new int[2];
		t3[0]=0;t3[1]=-1;
		commande.put("q", t3);
		
		int[] t4=new int[2];
		t4[0]=0;t4[1]=1;
		commande.put("d", t4);
	}
	
	/**
	 * Permet de jouer a un jeu quelconque sur un flux d'entree et de sortie
	 * parametrable
	 * 
	 * @param pJeu
	 *            Le jeu a jouer
	 * @param pScan
	 *            Le flux d'entree
	 * @param pSortie
	 *            Le flux de sortie
	 */
	public static void jouer(Jeu pJeu, Scanner pScan, PrintStream pSortie, HashMap<String, int[]> commande) {
		// Un string pour enregistrer les deplacements
		String deplacements = "";
		// On enregistre la posistion du curseur au debut du programme, pour y
		// revenir apres
		pSortie.println((char) Event.ESCAPE + "7");
		pSortie.println(pJeu);
		// On boucle tant que le jeu n'est pas resolu
		while (!pJeu.estResolu()) {
			// Lecture du caractere tappe au clavier
			String sc = pScan.next();
			// On essaie d'effectuer le deplacement voulue
			try {
				// On recupere la correspondance touche | deplacement
				// C'est la methode deplacement qui peut retourner une erreur
				pJeu.deplacement(sc);
				// On ajoute les deplacements au deplacement effectuer
				deplacements += sc;
				// On revient a la position de depart
				pSortie.println((char) Event.ESCAPE + "8");
				// Et on reecrit le jeu par dessus l'ancien
				pSortie.println(pJeu);
			} catch (IndexOutOfBoundsException | MauvaiseTouche err) {
				
			}
		}
		// On revient au debut
				pSortie.println((char) Event.ESCAPE + "8");
				pSortie.println(pJeu);
				pSortie.println("Bravo vous avez gagne");
				pSortie.println("Voici la liste des mouvements effectues : " + deplacements);
	}
	
	private static void anim(Jeu jeu, String action) throws IndexOutOfBoundsException, MauvaiseTouche{
		System.out.println((char) Event.ESCAPE + "7");
		System.out.println(jeu);
		int nbAction = action.length();
		for (int i=0; i<nbAction; i++){
			jeu.deplacement(Character.toString(action.charAt(i)));
			//System.out.println((char) Event.ESCAPE + "8");
			System.out.println(jeu);			
		}
		
	}
	
	public static void main(String[] args) {
		//On initialise les commandes du jeu
		initialiserCommande();

		//On cree un jeu
		Taquin t = new Taquin(Integer.parseInt(args[0]), Integer.parseInt(args[1]), commande);
		Taquin s = new Taquin(Integer.parseInt(args[0]), Integer.parseInt(args[1]), commande);
		s.setDamier(t.copieTableau());
		//On initialise un algo
		Algo a=new Algo(t, new File(), new EnsembleIncomplet(2000003));
		//On lance l'algorithme
		a.run();
		
		//On interprete le resultat de l'algo
		if(a.getFinale()==null)
			System.out.println("Pas de solution trouvé");
		else
			System.out.println("Chemin : "+a.getSolution());

		
		try {
			anim(s,a.getSolution());
		} catch (IndexOutOfBoundsException | MauvaiseTouche e) {}

/*		Scanner s = new Scanner(System.in);
		PrintStream p=System.out;
		jouer(t,s,p,commande);*/
		System.exit(0);
	}

}
