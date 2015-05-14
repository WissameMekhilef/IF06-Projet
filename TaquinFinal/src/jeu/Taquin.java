package jeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import exceptions.*;

public class Taquin implements Jeu {
	
	private Action action;
	private Commande commande;
	private int[][] damier;
	private int nbCoupsfinale;
	private int[] posZero=new int[2];
	private int profondeur;
	private PositionFinale situationFinale;
	private Taquin pere;
	
	/**
	 * Constructeur d'un fils
	 * @param action
	 * L'action qui mene au fils
	 * @param p
	 * Le taquin pere
	 */
	public Taquin(Action action, Taquin p) {
		this.situationFinale = p.getSituationFinale();
		this.commande = p.commande;
		this.damier = new int[p.damier.length][p.damier[0].length];
		this.action = action;
		this.pere = p;
		this.profondeur = p.profondeur++;
		for(int i = 0; i < damier.length; i++)
			for(int j = 0; j < damier[i].length; j++)
				damier[i][j] = p.damier[i][j];
		nbCoupsfinale = p.nbCoupsfinale;
		posZero[0] = p.posZero[0];
		posZero[1] = p.posZero[1];
	}
	
	/**
	 * Constructeur sans parametre
	 * @param nbL
	 * Le nombre de lignes
	 * @param nbC
	 * Le nombre de colonnes
	 * @param pCommande
	 * Les commandes
	 */
	public Taquin(int nbL, int nbC, Commande pCommande) {
		HashMap<Integer, int[]> damierFin = new HashMap<Integer, int[]>();
		this.damier = new int[nbL][nbC];
		int numero = 1;
		for(int i = 0; i < nbL; i++) {
			for(int j = 0; j < nbC; j++) {
				if(i == nbL - 1 && j == nbC - 1)
					numero = 0;
				damier[i][j] = numero;
				int[] t = new int[2];
				t[0] = i;
				t[1] = j;
				damierFin.put(numero, t);
				numero++;
			}
		}	
		situationFinale = new PositionFinale(damierFin);
		commande = pCommande;
		do {
			for(int i = 0; i < 90; i++)
				melanger();
		} while(this.estResolu());
		action = null;
		this.pere = null;
		this.profondeur = 0;
		this.nbCoupsfinale = this.nbPermutFin();
		posZero = indexOf(0);
	}
	
	/**
	 * Constructeur d'un taquin avec parametre
	 * <p>
	 * Ce constructeur contruit un Taquin a partir d'un fichier source .taq
	 * </p>
	 * @param destfic
	 * La source du jeu
	 * @param pCommande
	 * Les commandes du jeu, pour pouvoir jouer en ligne de commande
	 * @throws NombreDouble
	 * Une exception si un nombre est en double dans le fichier source
	 * @throws NumberFormatException
	 * Une exception si il y a autre chose qu'un entier
	 * @throws FileNotFoundException
	 * Une exception si le fichier source est introuvable
	 * @throws IOException
	 * Une exception pour toutes les autres erreurs
	 */
	@SuppressWarnings("resource")
	public Taquin(String destfic, Commande pCommande) throws FileNotFoundException, IOException, NombreDouble, NumberFormatException {
		BufferedReader fic = new BufferedReader(new FileReader(destfic));
		ArrayList<Integer> nombresPresent = new ArrayList<Integer>();
		this.damier = new int[Integer.parseInt(fic.readLine())][Integer.parseInt(fic.readLine())];
		StringTokenizer valeurs = new StringTokenizer(fic.readLine());
		for(int i = 0; i < damier.length; i++) {
			for(int j = 0; j < damier[0].length; j++) {
				int aAjouter = Integer.parseInt(valeurs.nextToken());
				if(nombresPresent.contains(aAjouter))
					throw new NombreDouble(aAjouter);
				damier[i][j] = aAjouter;
				nombresPresent.add(damier[i][j]);
			}	
		}
		this.commande = pCommande;
		this.pere = null;
		this.profondeur = 0;
		action = null;
		Taquin t = new Taquin(damier.length, damier[0].length, commande);
		situationFinale = t.situationFinale;
		nbCoupsfinale = nbPermutFin();
		posZero = indexOf(0);
		fic.close();
	}
	
	public Jeu clone() {
		Taquin res = new Taquin(damier.length, damier[0].length, commande);
		res.damier = this.copieTableau();
		return res;
	}
	
	/**
	* @return
	* Une copie du damier du taquin
	*/
	public int[][] copieTableau() {
		int[][]t = new int[damier.length][damier[0].length];
		for(int i = 0; i < this.damier.length; i++)
			for(int j = 0; j < this.damier[0].length; j++)
				t[i][j] = damier[i][j];
		return t;
	}
	
	/**
	 * Permet de deplacer la case vide
	 * @param direction
	 * L'action a realiser
	 * @throws MauvaiseTouche 
	 * On leve une exception si l'utilisateur tape une mauvaise touche
	 * @throws ArrayIndexOutOfBoundsException
	 * Exception si on est hors du tableau
	 */
	public void deplacement(Action direction) throws MauvaiseTouche, ArrayIndexOutOfBoundsException {
		int[] pos0 = indexOf(0);
		if(commande.getDeplacement().containsKey(direction)) {
			int[] posX = commande.getDeplacement().get(direction);
			damier[pos0[0]][pos0[1]] = damier[pos0[0] + posX[0]][pos0[1] + posX[1]];
			damier[pos0[0] + posX[0]][pos0[1] + posX[1]] = 0;
			nbCoupsfinale = nbPermutFin();
		} else
			throw new MauvaiseTouche();
	}
	
	/**
	 * Decrit le jeu
	 * @return
	 * Retourne une description du jeu
	 */
	public String description() {
		int n = 0;
		for(int i = 1; i < damier.length * damier[0].length; i++) n += distanceManhattan(i);
		return "Le jeu de taquin a resoudre etait de taille " + damier.length + "x" + damier[0].length
				+ ".\nLa distance de Manhattan de la case vide valait " + distanceManhattan(0)
				+ ".\nLa somme des distances des autres cases etait de " + n + ".";
	}
	
	/**
	 * Methode qui determine la distance entre la position initiale
	 * de la case vide ini et sa position finale fin : 
	 * Dman=|Xfin-Xini|+|Yfin-Yini|
	 * @param i
	 * L'entier sur lequel on veut calculer la distance de Manhattan
	 * @return Un entier qui sera la distance dite de Mannathan
	 */
	public int distanceManhattan(int i) {
		int[] pos = this.indexOf(i);
		int[] posFin = this.situationFinale.getDamierFin().get(i);
		return (int)(Math.abs(posFin[1] - pos[1]) + Math.abs(posFin[0] - pos[0]));
	}
	
	/**
	 * Fonction d'egalite
	 * <p>
	 * On se sert du hashcode pour savoir si deux taquins sont identiques
	 * </p>
	 * @param o
	 * L'objet a comparer au taquin courant
	 * @return
	 * Un booleen true si les taquins sont identiques, false sinon
	 */
	public boolean equals(Object o) {
		if(o instanceof Taquin) {
			Taquin t = (Taquin) o;
			return t.hashCode() == this.hashCode();
		}
		else
			return false;
	}
	
	/**
	 * Permet de savoir si le jeu est resolu
	 * 
	 * @return Un booleean true si le jeu est resolu, false sinon
	 */
	public boolean estResolu() {
		return situationFinale.equals(damier);
	}
	
	/**
	 * Methode qui permet de determiner si le taquin
	 * est soluble ou non en comparant la parite du nombre de 
	 * permutations et de la distance de Mannathan
	 *  @return true si le taquin est soluble, false sinon
	 */
	public boolean estSoluble() {
		return this.distanceManhattan(0) % 2 == this.nbPermutFin() % 2;
	}
	
	/**
	 * Getter de l'action
	 * @return
	 * L'action qui a mene au jeu courant
	 */
	public Action getAction() {
		return action;
	}
	
	/**
	 * Getter des commandes
	 * @return
	 * L'objet commande associe au jeu
	 */
	public Commande getCommande() {
		return commande;
	}
	
	/**
	 * Getter du nombre de coups minimal pour arriver a une solution
	 * @return
	 * Le nombre de coups minimal pour arriver a une solution
	 */
	public int getNbCoupsfinale() {
		return nbCoupsfinale;
	}
	
	/**
	 * Getter du pere
	 * @return
	 * Le pere du taquin courant
	 */
	public Taquin getPere() {
		return pere;
	}
	
	/**
	 * Getter de la profondeur
	 * @return
	 * La profondeur du taquin courant, c'est-a-dire son nombre de pere
	 */
	public int getProfondeur() {
		return profondeur;
	}
	
	/**
	 * Getter de la situation finale
	 * @return
	 * La situation finale correspondant au taquin courant
	 */
	public PositionFinale getSituationFinale() {
		return situationFinale;
	}
	
	/**
	 * Fonction d'indentification
	 * <p>
	 * Cette fonction retourne un entier qui permet de savoir si deux jeux sont identiques, ce nombre est calcule de tel maniere
	 * que deux jeux differents ne puissent avoir le meme identifiant. On calcule le hashcode uniquement sur la grille de jeu sans prendre
	 * en compte la profondeur, le pere, ou l'action precedente du taquin.
	 * </p>
	 * @return
	 * L'entier identifiant du jeu courant
	 */
	public int hashCode() {
		int hash = 1;
		for(int i = 0; i < this.damier.length; i++)
			for(int j = 0; j < this.damier[0].length; j++)
				hash = hash * 7 + damier[i][j];
		return hash;
	}
	
	/**
	 * Permet de retrouver les coordonnees d'une case en particulier
	 * @param nb
	 * L'entier a retrouver dans le tableau
	 * @return
	 * Un tableau d'entier avec les numeros de ligne et de colonne
	 */
	public int[] indexOf(int nb) {
		int[] t = new int[2];
		for(int i = 0; i < damier.length; i++) {
			for(int j = 0; j < damier[0].length; j++) {
				if(damier[i][j] == nb) {
					t[0] = i;
					t[1] = j;
					return t;
				}
			}
		}
		return null;
	}
	
	/**
	 * Melange la grille de jeu
	 */
	public void melanger() {
		int entier = (int) (Math.random() * 4);
		try {
			deplacement((Action) commande.getTabClef()[entier]);
		} catch(IndexOutOfBoundsException | MauvaiseTouche e) {
			
		}
	}
	
	/**
	 * Fonction d'estimation
	 * <p>
	 * Permet de connaitre le nombre de coups minimal a realiser pour resolu le jeu de taquin
	 * </p>
	 * @return
	 * Le nombre de permutions pour ramener toutes les cases a leur position ideale
	 */
	public int nbPermutFin() {
		int res = 0;
		for(int j = 0; j < damier.length; j++)
			for(int i = 0; i < damier[0].length; i++)
				if(damier[j][i] != 0)
					res += this.distanceManhattan(damier[j][i]);
		return res;
	}
	
	/**
	 * Donne les voisins d'un jeu
	 * <p>
	 * Permet d'obtenir sous forme de liste tous les voisins de la position courante
	 * </p>
	 * @return
	 * Une ArrayList contenant tous les jeux voisins
	 */
	public ArrayList<Jeu> succ() {
		ArrayList<Jeu> res = new ArrayList<Jeu>();
		for(Action p: commande.getListeDesClefs()) {
			try {
				this.deplacement(p);
				res.add(new Taquin(p, this));
				this.deplacement(p.getInverse());
			} catch(IndexOutOfBoundsException | MauvaiseTouche e) {
				
			}
		}
		return res;
	}

	/**
	 * Affichage du jeu
	 */
	public String toString() {
		String s = "";
		for(int i = 0; i < damier.length; i++) {
			for(int j = 0; j < damier[0].length; j++)
				s += damier[i][j] + "\t";
			s += "\n";
		}
		return s;
	}
	
}
