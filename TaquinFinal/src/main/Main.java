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
	private static Commande commande=new Commande();
	
	private static void joue(String destJeu){
		jouer(new Taquin(3,3,commande),new Scanner(System.in),System.out,commande);
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
	public static void jouer(Jeu pJeu, Scanner pScan, PrintStream pSortie, Commande commande) {
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
	
	private static void anim(Jeu jeu, String action) throws IndexOutOfBoundsException, MauvaiseTouche, InterruptedException{
		System.out.println((char) Event.ESCAPE + "[s");
		System.out.println(jeu);
		int nbAction = action.length();
		System.out.println("On a "+nbAction+" action à réaliser");
		for(int i=0; i<nbAction; i++){
			jeu.deplacement(Character.toString(action.charAt(i)));
			System.out.println((char) Event.ESCAPE + "[u");
			System.out.println(jeu);
			Thread.sleep(1000);
		}
		
	}
	
	private static void printName(){
		System.out.println("Ce programme à été développé par : ARNOULT Simon, MEKHILEF Wissame, OUSSAD Jihad et RETY Martin");
	}
	
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
	
	public static void main(String[] args) {
		
/*		//Lecture des paramètres
		switch(args[0]){
		case "-name":
			printName();
			break;
		case "-h":
			printOptionList();
			break;
		case "-sol":
			
			break;
		case "-joue":
			if(args[1]!=null)
				joue(args[1]);
			else
				System.out.println("Erreur vous n'avez pas spécifié de fichier de jeu");
			break;
		case "-cal":
			
			break;
		case "-anime":
			
			break;
		case "-stat":
			
			break;
		case "-aleatoire":
			
			break;
		}*/
		

		//On cree un jeu
		Taquin t = new Taquin(Integer.parseInt(args[0]), Integer.parseInt(args[1]), commande);
		//On initialise un algo
		Algo b=new Algo(t, new Tas(new DepthManhattan()), new EnsembleIncomplet(200383));
		//On lance l'algorithme
		b.run();
		
		String soluce = b.getSolution();
		//On interprete le resultat de l'algo
		if(b.getFinale()==null)
			System.out.println("Pas de solution trouvé");
		else{
			System.out.println("Chemin : "+soluce);
			System.out.println("L'algorithme à traité : "+ b.getNombrePositionTraite()+" position(s)");
		}

		
		try {
			anim(t,soluce);
		} catch (IndexOutOfBoundsException | MauvaiseTouche | InterruptedException e) {}

/*		Scanner s = new Scanner(System.in);
		PrintStream p=System.out;
		jouer(t,s,p,commande);*/
		//System.exit(0);
	}

}
