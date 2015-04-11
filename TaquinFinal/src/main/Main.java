package main;

import java.awt.Event;
import java.io.PrintStream;
import java.util.*;

import comparateurs.*;
import algo.*;
import exceptions.*;
import jeu.*;

public class Main {
	private HashMap<String, Integer[]> commande=new HashMap<>();
	
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
	public static void jouer(Jeu pJeu, Scanner pScan, PrintStream pSortie) {
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
	
	
	
	public static void main(String[] args) {
		//On initialise un scanner d'entree
		Scanner s = new Scanner(System.in);
		// Un jeu
		Taquin t = new Taquin(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], args[3], args[4], args[5]);
		Algo a=new Algo(t, new Tas(new Mannathan()), new EnsembleIncomplet(200));
		System.out.println("Taquin départ :\n"+t);
		a.run();
		System.out.println("Chemin : "+a.getSolution());
		// un PrintStream
		PrintStream p=System.out;
		//jouer(t,s,p);
		System.exit(0);
	}

}
