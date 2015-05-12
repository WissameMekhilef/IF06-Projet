package main;

import java.awt.Event;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import comparateurs.Manhattan;

import jeu.Action;
import jeu.Commande;
import jeu.Jeu;
import jeu.Taquin;
import algo.Algo;
import algo.EnsembleComplet;
import algo.File;
import algo.Tas;
import exceptions.MauvaiseTouche;
import exceptions.NombreDouble;

public class Main {
	private static Commande commande=new Commande();
	private static HashMap <String,Action> tableCorrespondance = new HashMap<String,Action>();
	
	/**
	 * Jeu à partir d'un fichier
	 * @param destJeu
	 * Source du fichier
	 * @return
	 * Un jeu initiali
	 */
	private static Jeu jeuFromFile(String destJeu){
		try {
			return new Taquin(destJeu, commande);
		} catch (NumberFormatException e) {
			System.out.println("Erreur lors de la lecture du fichier .taq un entier été attendu");
		} catch (NombreDouble e) {
			System.out.println(e.getMessage());
		}catch (FileNotFoundException e){
			System.out.println("Fichier introuvable");
		} catch (IOException e) {
			System.out.println("Erreur lors de la lecture du fichier,  il manque des arguments");
		}
		return null;
	}
	
	/**
	 * Jouer à partir d'un fichier
	 * @param destJeu
	 * La destination du fichier source
	 */
	private static void joue(String destJeu){
		jouer(jeuFromFile(destJeu),new Scanner(System.in),System.out);
	}
	
	
	/**
	 * Lecture des touches
	 * @param pScan
	 * Le scanner à partir duqel il faut les actions
	 * @return
	 * Une action correspondant à l'action lue
	 */
	private static Action lireAction(Scanner pScan){
		return commande.getTabCorrespondance().get(pScan.nextLine());
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
		Action actionARealiser;
		// Un string pour enregistrer les deplacements
		Stack<Action> deplacementsEffectuer = new Stack<Action>();
		// On enregistre la posistion du curseur au debut du programme, pour y
		// revenir apres
		pSortie.println((char) Event.ESCAPE + "7");
		pSortie.println(pJeu);
		// On boucle tant que le jeu n'est pas resolu
		while (!pJeu.estResolu()) {
			// Lecture du caractere tappe au clavier
			actionARealiser=lireAction(pScan);
			// On essaie d'effectuer le deplacement voulue
			try {
				// On recupere la correspondance touche | deplacement
				// C'est la methode deplacement qui peut retourner une erreur
				pJeu.deplacement(actionARealiser);
				// On ajoute les deplacements au deplacement effectuer
				deplacementsEffectuer.push(actionARealiser);
				// On revient a la position de depart
				pSortie.println((char) Event.ESCAPE + "8");
				// Et on reecrit le jeu par dessus l'ancien
				pSortie.println(pJeu);
			} catch (MauvaiseTouche err) {
				
			}
		}
		// On revient au debut
				pSortie.println((char) Event.ESCAPE + "8");
				pSortie.println(pJeu);
				pSortie.println("Bravo vous avez gagne");
				//pSortie.println("Voici la liste des mouvements effectues : " + deplacements);
	}
	
	/**
	 * Animation
	 * <p>
	 * Permet l'animation d'une suite d'action sur un jeu quelconque
	 * </p>
	 * @param jeu
	 * Le jeu à animer
	 * @param action
	 * La liste d'action à réaliser
	 * @throws InterruptedException
	 * Une exception est lévé dans le cas on le sleep ne reprend pas
	 */
	private static void anim(Jeu jeu, ArrayList<Action> action) throws InterruptedException{
		System.out.println(jeu);
		int nbAction = action.size();
		for(Action act : action){
			try {
				jeu.deplacement(act);
			} catch (IndexOutOfBoundsException e) {
				//On ne rentre jamais dans ce cas car les actions a réaliser sont possible
			} catch (MauvaiseTouche e) {
				System.out.println("Mauvaise touche ");
			}
			System.out.println(jeu);
			Thread.sleep(10);
		}
		
	}

	/**
	 * Test solvable
	 * @param jeuaTester
	 * Retourne vrai si le jeu est solvable, faux sinon
	 */
	private static void testSolvable(String jeuaTester){
		String res = "Le jeu a tester est ";
		if(jeuFromFile(jeuaTester).estSoluble()) res+="";
		else res+="non";
		res+="resolvable";
		System.out.println(res);
	}
	
	
	/**
	 * Fonction d'affichage des noms des personnes de l'équipe
	 */
	private static void printName(){
		System.out.println("Ce programme a� �t� d�velopp� par : ARNOULT Simon, MEKHILEF Wissame, OUSSAD Jihad et RETY Martin");
	}
	
	/**
	 * Imprime la liste des options disponibles
	 */
	private static void printOptionList(){
		String gras = (char) Event.ESCAPE+"[1m";
		String defaut = (char) Event.ESCAPE+"[0m";
		System.out.print("Voici une liste des options disponible :\n");
		System.out.print("\t"+gras+"-name"+defaut+"\t: java -jar taquin.jar -name\n\t\tAfficher le nom des développeurs\n");
		System.out.print("\t"+gras+"-h"+defaut+"\t: java -jar taquin.jar -h\n\t\tAfficher les différentes options possible\n");
		System.out.print("\t"+gras+"-sol"+defaut+"\t: java -jar taquin.jar -sol [fichier.taq] -j\n\t\tTest si le fichier .taq passé en paramètre à une solution, finir par -j\n");
		System.out.print("\t"+gras+"-joue"+defaut+"\t: java -jar taquin.jar -joue [fichier.taq]\n\t\t Permet de jouer sur un fichier .taq placé en paramètre\n");
		System.out.print("\t"+gras+"-cal"+defaut+"\t: java -jar taquin.jar -cal [delai] [algo] fichier.taq\n\t\tCalcule une position en utilisant l'ago dans le temps du delai\n");
		System.out.print("\t"+gras+"-anime"+defaut+"\t: java -jar taquin.jar -anime [delai] [algo] fichier.taq\n\t\tComme précédement mais avec une animation de la solution\n");
		System.out.print("\t"+gras+"-stat"+defaut+"\t: java -jar taquin.jar -stat [delai] [algo] fichier.taq\n\t\tComme précédement mais avec un retour de statistiques sur l'exection.\n");
		System.out.print("\t"+gras+"-stat"+defaut+"\t: java -jar taquin.jar -stat [delai] fichier.taq\n\t\tComme précédement mais avec un retour html de toutes les statistiques.\n");
		System.out.print("\t"+gras+"-alea"+defaut+"\t: java -jar taquin.jar -aleatoire [n] [largeur] [hauteur] [delai] fichier.taq\n\t\tApplique tous les algorithmes n fois a des taquin de largeur et hauteur.\n\t\tOn recoit des informations détaillées sur le déroulement.\n");
	}
	
	
	/**
	 * Lecture de l'algorithme
	 * @return
	 * Retourne un algorithme correspondant à l'algorithme préciser dans les paramètre du lancement
	 */
	private Algo lectureAlgo(){
		switch(args[1]){
		
		}
		return null;
	}
	
	public static void main(String[] args) {
		//Lecture des paramètres
		switch(args[0]){
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
			Algo alg=lectureAlgo();
		case "-anime":
		case "-stat":
		case "-aleatoire":
			break;
		default:
			System.out.println("Option invalide");
			printOptionList();
			System.exit(1);
		}
		System.exit(0);
				
/*		//On cree un jeu
		Jeu t = jeuFromFile("taquin/taq1.taq");
		//On initialise un algo
		Algo b=new Algo(t,  new Tas(new Manhattan()), new EnsembleComplet(), true);
		//On lance l'algorithme	
		System.out.println("Le jeu est solvable : "+t.estSoluble()+" en au moins "+t.getNbCoupsfinale());
		b.run(0);
		//On interprete le resultat de l'algo
		System.out.println(b.description());
		System.out.println(b.getFinale());*/

		//On quite le programme avec comme sortie 0 car il n'y a pas eu d'erreur
		System.exit(0);
		
	}

}
