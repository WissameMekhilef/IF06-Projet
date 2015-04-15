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
		System.out.print("Voici une liste des options disponible :\n");
		System.out.print("\t-name : java -jar taquin.jar -name\n\t\tAfficher le nom des développeurs\n");
		System.out.print("\t-h : java -jar taquin.jar -h\n\t\tAfficher les différentes options possible\n");
		System.out.print("\t-sol : Test si le fichier .taq passé en paramètre à une solution, finir par -j\n");
		System.out.print("\t-joue : Permet de jouer sur un fichier .taq placé en paramètre\n");
		System.out.print("\t-cal : \n");



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
			
			break;
		case "-joue":
			
			break;
		case "-cal":
			
			break;
		case "-anime":
			
			break;
		case "-stat":
			
			break;
		case "-aleatoire":
			
			break;
		}
		
		
	/*	//On initialise les commandes du jeu
		initialiserCommande();

		//On cree un jeu
		Taquin t = new Taquin(Integer.parseInt(args[0]), Integer.parseInt(args[1]), commande);
		Taquin s = new Taquin(Integer.parseInt(args[0]), Integer.parseInt(args[1]), commande);
		s.setDamier(t.copieTableau());
		//On initialise un algo
		Algo a=new Algo(t, new File(), new EnsembleIncomplet(2000003));
		//On lance l'algorithme
		a.run();
		String soluce = a.getSolution();
		//On interprete le resultat de l'algo
		if(a.getFinale()==null)
			System.out.println("Pas de solution trouvé");
		else
			System.out.println("Chemin : "+soluce);

		
		try {
			anim(s,soluce);
		} catch (IndexOutOfBoundsException | MauvaiseTouche | InterruptedException e) {}

/*		Scanner s = new Scanner(System.in);
		PrintStream p=System.out;
		jouer(t,s,p,commande);*/
		System.exit(0);
	}

}
