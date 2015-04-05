package main;

import java.awt.Event;

import java.io.PrintStream;
import java.util.*;
import jeu.*;
import algo.*;
import comparateurs.*;
import exceptions.*;

public class Main {
	
	private static HashMap<String, int[]> initialisationTouches(){
		HashMap<String, int[]> deplacement= new HashMap<>();
		int[] t1=new int[2];
		t1[0]=-1;t1[1]=0;
		deplacement.put("N", t1);
		int[] t2=new int[2];
		t2[0]=1;t2[1]=0;
		deplacement.put("S", t2);
		int[] t3=new int[2];
		t3[0]=0;t3[1]=-1;
		deplacement.put("E", t3);
		int[] t4=new int[2];
		t4[0]=0;t4[1]=1;
		deplacement.put("O", t4);
		return deplacement;
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
		// On initialise un scanner d'entree
		Scanner s = new Scanner(System.in);
		//On initialise une map pour les deplacements
		HashMap<String, int[]> mapDeplacement=initialisationTouches();
		//On initialise un jeu de Taquin
		Taquin t = new Taquin(Integer.parseInt(args[0]), Integer.parseInt(args[1]),mapDeplacement);
		//On initialise la sortie du jeu
		PrintStream p=System.out;
		//On lance la partie
		jouer(t,s,p);
	}

}
