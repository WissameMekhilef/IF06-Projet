package main;

import java.awt.Event;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import jeu.Action;
import jeu.Commande;
import jeu.Jeu;
import jeu.Taquin;
import algo.Algo;
import algo.EnsembleComplet;
import algo.EnsembleIncomplet;
import algo.File;
import algo.Pile;
import algo.PileAction;
import algo.Tas;

import comparateurs.DepthManhattan;
import comparateurs.Manhattan;

import exceptions.MauvaiseTouche;
import exceptions.NombreDouble;

public class Main {
	
	private static Commande commande = new Commande();
	
	/**
	 * Lecture de l'algorithme.
	 * @return
	 * Retourne un algorithme correspondant a l'algorithme precise dans les parametres du lancement.
	 */
	private static void afficheSol(Jeu jeu, ArrayList<Action> action) {
		System.out.println(jeu);
		for(Action act: action) {
			try {
				jeu.deplacement(act);
			} catch(IndexOutOfBoundsException e) {
				
			} catch (MauvaiseTouche e) {
				
			}
			System.out.println(jeu);
		}
	}
	
	/**
	 * Animation:
	 * <p>
	 * Permet l'animation d'une suite d'action sur un jeu quelconque.
	 * </p>
	 * @param jeu
	 * Le jeu a animer.
	 * @param action
	 * La liste d'actions a realiser.
	 * @throws InterruptedException
	 * Une exception levee dans le cas ou le sleep ne reprend pas.
	 */
	private static void anim(Jeu jeu, ArrayList<Action> action) throws InterruptedException {
		System.out.println((char) Event.ESCAPE + "7");
		System.out.println(jeu);
		for(Action act: action) {
			try {
				jeu.deplacement(act);
			} catch(IndexOutOfBoundsException e) {
				
			} catch(MauvaiseTouche e) {
				
			}
			System.out.println((char) Event.ESCAPE + "8");
			System.out.println(jeu);
			Thread.sleep(500);
		}
	}
	
	/**
	 * Initialise un jeu a partir d'un fichier.
	 * @param destJeu
	 * Source du fichier
	 * @return
	 * Un jeu initialise.
	 */
	public static Jeu jeuFromFile(String destJeu) {
		try {
			return new Taquin(destJeu, commande);
		} catch(NumberFormatException e) {
			System.out.println("Erreur lors de la lecture du fichier .taq un entier ete attendu");
		} catch(NombreDouble e) {
			System.out.println(e.getMessage());
		} catch(FileNotFoundException e) {
			System.out.println("Fichier introuvable");
		} catch(IOException e) {
			System.out.println("Erreur lors de la lecture du fichier,  il manque des arguments");
		}
		System.exit(1);
		return null;
	}
	
	/**
	 * Permet de jouer partir d'un fichier.
	 * @param destJeu
	 * La destination du fichier source.
	 */
	private static void joue(String destJeu){
		jouer(jeuFromFile(destJeu), new Scanner(System.in), System.out);
	}
	
	/**
	 * Permet de jouer a un jeu quelconque sur un flux d'entree et de sortie parametrable
	 * 
	 * @param pJeu
	 * Le jeu a jouer.
	 * @param pScan
	 * Le flux d'entree.
	 * @param pSortie
	 * Le flux de sortie.
	 */
	public static void jouer(Jeu pJeu, Scanner pScan, PrintStream pSortie) {
		Action actionARealiser;
		Stack<Action> deplacementsEffectuer = new Stack<Action>();
		pSortie.println((char) Event.ESCAPE + "7");
		pSortie.println(pJeu);
		while(!pJeu.estResolu()) {
			actionARealiser=lireAction(pScan);
			try {
				pJeu.deplacement(actionARealiser);
				deplacementsEffectuer.push(actionARealiser);
				pSortie.println((char) Event.ESCAPE + "8");
				pSortie.println(pJeu);
			} catch(MauvaiseTouche err) {
				
			} catch(ArrayIndexOutOfBoundsException err) {
				
			}
		}
		pSortie.println((char) Event.ESCAPE + "8");
		pSortie.println(pJeu);
		pSortie.println("Bravo vous avez gagne");
	}
	
	private static Algo lectureAlgo(String[] param) {
		switch(param[3]) {
		case "pile":
			return new Algo(jeuFromFile(param[param.length - 1]), new Pile(), new EnsembleComplet(), false);
		case "file":
			return new Algo(jeuFromFile(param[param.length - 1]), new File(), new EnsembleComplet(), false);
		case "manhattan":
			return new Algo(jeuFromFile(param[param.length - 1]),new Tas(new Manhattan()), new EnsembleComplet(), false);
		case "pmanhattan":
			return new Algo(jeuFromFile(param[param.length - 1]),new Tas(new DepthManhattan()), new EnsembleComplet(), false);
		case "bit":
			switch(param[4]) {
			case "pile":
				return new Algo(jeuFromFile(param[param.length - 1]), new Pile(), new EnsembleIncomplet(Integer.parseInt(param[5])), false);
			case "file":
				return new Algo(jeuFromFile(param[param.length - 1]), new File(), new EnsembleIncomplet(Integer.parseInt(param[5])), false);
			case "manhattan":
				return new Algo(jeuFromFile(param[param.length - 1]), new Tas(new Manhattan()), new EnsembleIncomplet(Integer.parseInt(param[5])), false);
			case "pmanhattan":
				return new Algo(jeuFromFile(param[param.length - 1]), new Tas(new DepthManhattan()), new EnsembleIncomplet(Integer.parseInt(param[5])), false);
			case "prof":
				return new Algo(jeuFromFile(param[param.length - 1]), new PileAction(), new EnsembleIncomplet(Integer.parseInt(param[5])), false);
			default:
				System.out.println("Option invalide");
				System.exit(1);	
			}
		case "prof":
			return new Algo(jeuFromFile(param[param.length - 1]), new PileAction(), new EnsembleComplet(), false);
		case "redondant":
			Algo alg;
			switch(param[5]) {
			case "manhattan":
				alg = new Algo(jeuFromFile(param[param.length - 1]), new Tas(new Manhattan()), new EnsembleComplet(), true);
				alg.setProfondeurAutomate(Integer.parseInt(param[4]));
				return alg;
			default:
				alg = new Algo(jeuFromFile(param[param.length - 1]), new File(), new EnsembleComplet(), true);
				alg.setProfondeurAutomate(Integer.parseInt(param[4]));
				return alg;
			}
		default:
			System.out.println("Option invalide");
			System.exit(1);
		}
		return null;
	}
	
	/**
	 * Lecture des touches.
	 * @param pScan
	 * Le scanner a partir duquel les actions sont lus.
	 * @return
	 * Une action correspondant a l'action lue.
	 */
	private static Action lireAction(Scanner pScan) {
		return commande.getTabCorrespondance().get(pScan.nextLine());
	}
	
	/**
	 * Fonction d'affichage des noms des personnes de l'equipe.
	 */
	private static void printName() {
		System.out.println("Ce programme a ete developpe par : ARNOULT Simon, MEKHILEF Wissame, OUSSAD Jihad et RETY Martin");
	}
	
	/**
	 * Imprime la liste des options disponibles.
	 */
	private static void printOptionList() {
		String gras = (char) Event.ESCAPE + "[1m";
		String defaut = (char) Event.ESCAPE + "[0m";
		System.out.print("Voici une liste des options disponible :\n");
		System.out.print("\t" + gras + "-name" + defaut + "\t: java -jar taquin.jar -name\n\t\tAfficher le nom des developpeurs\n");
		System.out.print("\t" + gras + "-h" + defaut + "\t: java -jar taquin.jar -h\n\t\tAfficher les differentes options possible\n");
		System.out.print("\t" + gras + "-sol" + defaut + "\t: java -jar taquin.jar -sol [fichier.taq] -j\n\t\tTest si le fichier .taq passe en parametre a  une solution, finir par -j\n");
		System.out.print("\t" + gras + "-joue" + defaut + "\t: java -jar taquin.jar -joue [fichier.taq]\n\t\t Permet de jouer sur un fichier .taq place en parametre\n");
		System.out.print("\t" + gras + "-cal" + defaut + "\t: java -jar taquin.jar -cal [delai] [algo] fichier.taq\n\t\tCalcule une position en utilisant l'ago dans le temps du delai\n");
		System.out.print("\t" + gras + "-anime" + defaut + "\t: java -jar taquin.jar -anime [delai] [algo] fichier.taq\n\t\tComme precedement mais avec une animation de la solution\n");
		System.out.print("\t" + gras + "-stat" + defaut + "\t: java -jar taquin.jar -stat [delai] [algo] fichier.taq\n\t\tComme precedement mais avec un retour de statistiques sur l'exection.\n");
		System.out.print("\t" + gras + "-stat" + defaut + "\t: java -jar taquin.jar -stat [delai] fichier.taq\n\t\tComme precedement mais avec un retour html de toutes les statistiques.\n");
		System.out.print("\t" + gras + "-alea" + defaut + "\t: java -jar taquin.jar -aleatoire [n] [largeur] [hauteur] [delai] fichier.taq\n\t\tApplique tous les algorithmes n fois a des taquin de largeur et hauteur.\n\t\tOn recoit des informations detaillees sur le deroulement.\n");
	}
	
	/**
	 * Test solvable.
	 * @param jeuaTester
	 * Retourne true si le jeu est solvable, false sinon.
	 */
	private static void testSolvable(String jeuaTester) {
		String res = "Le jeu a tester est ";
		if(!jeuFromFile(jeuaTester).estSoluble())
			res += "non";
		res += "soluble";
		System.out.println(res);
	}
	
	public static void main(String[] args) {
		Algo alg;
		switch(args[0]) {
		case "-name":
			printName();
			break;
		case "-h":
			printOptionList();
			break;
		case "-sol":
			testSolvable(args[1]);
			break;
		case "-joue":
			joue(args[1]);
			break;
		case "-cal":
			alg = lectureAlgo(args);
			alg.run(Integer.parseInt(args[1]));
			afficheSol(alg.getInitial(),alg.getSolution());
			break;
		case "-anime":
			alg = lectureAlgo(args);
			alg.run(Integer.parseInt(args[1]));
			try {
				anim(alg.getInitial(),alg.getSolution());
			} catch (InterruptedException e) {
				
			}
			break;
		case "-stat":
			alg = lectureAlgo(args);
			alg.run(Integer.parseInt(args[1]));
			System.out.println(alg.description());
			break;
		case "-aleatoire":
			break;
		default:
			System.out.println("Option invalide");
			printOptionList();
			System.exit(1);
		}
		System.exit(0);		
	}

}
