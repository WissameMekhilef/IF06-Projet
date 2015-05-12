package jeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import exceptions.*;

public class Taquin implements Jeu{
	private int[][] damier;
	private PositionFinale situationFinale;
	private Commande commande;
	private Action action;
	private Taquin pere;
	private int profondeur;
	private int nbCoupsfinale;
	
	
	/**
	 * Constructeur d'un Taquin avec paramètre 2
	 * <p>
	 * Ce constructeur contruit un Taquin à partir d'un fichier source .taq
	 * </p>
	 * @param destfic
	 * La source du jeu
	 * @param pCommande
	 * Les commandes du jeu, pour pouvoir jouer en ligne de commande
	 * @throws NombreDouble
	 * Une exception si jamais un nombre est en double dans le fichier source
	 * @throws NumberFormatException
	 * Une exception si jamais il y'a autre chose qu'un entier 
	 * @throws FileNotFoundException
	 * Une exception si jamais le fichier source n'est pas trouvé
	 * @throws IOException
	 * Une excpetion pour toute les autres erreurs
	 */
	public Taquin(String destfic, Commande pCommande) throws NombreDouble, NumberFormatException, FileNotFoundException, IOException{
		//On met le fichier source dans un BufferedReader
		BufferedReader fic = new BufferedReader( new FileReader (destfic));
		//On se sert de cette arrayList pour savoir si le fichier source contient des nombres en double
		ArrayList<Integer> nombresPresent = new ArrayList<Integer>();
		//On lit les dimensions du Taquin
		this.damier = new int[Integer.parseInt(fic.readLine())][Integer.parseInt(fic.readLine())];
		
		StringTokenizer valeurs = new StringTokenizer(fic.readLine());
		for(int i=0; i<damier.length;i++){
			for(int j=0; j<damier[0].length;j++){
				int aAjouter = Integer.parseInt(valeurs.nextToken());
				if(nombresPresent.contains(aAjouter))
					throw new NombreDouble(aAjouter);
				damier[i][j]=aAjouter;
				nombresPresent.add(damier[i][j]);
			}
				
		}

		this.commande=pCommande;
		this.pere=null;
		this.profondeur=0;
		//L'action qui mene au pere est null
		action=null;
		
		//On construit un Taquin en local pour récuperer l'etat final
		Taquin t = new Taquin(damier.length, damier[0].length, commande);
		situationFinale=t.situationFinale;
		
		nbCoupsfinale=nbPermutFin();
		
		fic.close();
	
	}
	
	/**
	 * Constructeur sans paramètre
	 * @param nbL
	 * Le nombre de ligne
	 * @param nbC
	 * Le nombre de colonne
	 * @param pCommande
	 * Les commandes
	 */
	public Taquin(int nbL, int nbC, Commande pCommande) {
		//Initialisation d'un damier initial
		HashMap<Integer, int[]> damierFin=new HashMap<Integer, int[]>();
		this.damier= new int[nbL][nbC];
		int numero=1;
		for(int i=0; i<nbL;i++){
			for(int j=0; j<nbC;j++){
				if(i==nbL-1 && j==nbC-1) numero=0;
				damier[i][j]=numero;
				int[] t=new int[2];
				t[0]=i;t[1]=j;
				damierFin.put(numero, t);
				numero++;
			}
		}	
		situationFinale = new PositionFinale(damierFin);
		
		//On initialise les deplacements
		commande = pCommande;
		//On melange le jeu
		do{
			for(int i=0; i<90; i++)
				melanger();
		}while(this.estResolu());
		
		//Initialisation de l'action
		action=null;
		//Intialisation du pere
		this.pere=null;
		//Initialisation d'une profondeur
		this.profondeur=0;
		//Init nombre coups finaux
		this.nbCoupsfinale=this.nbPermutFin();
		
		
	}
	
	/**
	 * Constructeur d'un fils
	 * @param action
	 * L'action qui mène au fils
	 * @param p
	 * Le Taquin père
	 */
	public Taquin(Action action, Taquin p){
		this.situationFinale=p.getSituationFinale();
		this.commande=p.commande;
		this.damier= new int[p.damier.length][p.damier[0].length];
		this.action=action;
		this.pere=p;
		this.profondeur=p.profondeur++;
		for(int i=0; i<damier.length;i++){
			for(int j=0; j<damier[i].length;j++){
				damier[i][j]=p.damier[i][j];
			}
		}
		nbCoupsfinale=p.nbCoupsfinale;
	}
	/**
	 * Melange la grille de jeu
	 */
	public void melanger() {
		int entier = (int) (Math.random() * 4);
		try {
			deplacement((Action) commande.getTabClef()[entier]);
		} catch (IndexOutOfBoundsException | MauvaiseTouche e) {}
	}

	/**
	 * Permet de retrouver les coordonnees d'une case en particulier
	 * @param nb
	 * L'entier à retrouver dans le tableau
	 * @return
	 * Un tableau d'entier avec numero  de la ligne et celle de la colonne
	 */
	public int[] indexOf(int nb){
		//System.out.println("\t\tAppel a indexOf");
		int[] t=new int[2];
		for(int i=0; i<damier.length;i++){
			for(int j=0; j<damier[0].length; j++){
				if (damier[i][j]==nb){
					t[0]=i;t[1]=j;
					return t;
				}
			}
		}
		return null;
	}
	/**
	 * Permet de deplacer la case vide
	 * @param direction
	 * L'action à réaliser
	 * @throws MauvaiseTouche 
	 * On leve une exception si l'utilisateur rentre une mauvaise touche
	 * @throws ArrayIndexOutOfBoundsException
	 * Exception si on est en dehors du tableau
	 */
	public void deplacement(Action direction) throws MauvaiseTouche, ArrayIndexOutOfBoundsException {
		int[] pos0=indexOf(0);
		if(commande.getDeplacement().containsKey(direction)){
			int temp=damier[pos0[0]][pos0[1]];
			int[] posX=commande.getDeplacement().get(direction);
			damier[pos0[0]][pos0[1]]=damier[pos0[0]+posX[0]][pos0[1]+posX[1]];
			damier[pos0[0]+posX[0]][pos0[1]+posX[1]]=0;
	//		indexZero[0]=pos0[0]+posX[0];
		//	indexZero[1]=pos0[1]+posX[1];
			
			//ameliorant=ancienMan-distanceManhattan(temp);
			nbCoupsfinale=nbPermutFin();
		}else throw new MauvaiseTouche();
	}
	/**
	 * Methode qui determine la distance entre la position initiale
	 * de la case vide que l'on nommera ini et sa position finale
	 * pour que le taquin puisse pretendre etre resolu dont on 
	 * nommera fin : Dman=|Xfin-Xini|+|Yfin-Yini|
	 * @param i
	 * L'entier sur lequel on veut calculer la distance de Manhattan
	 * @return un entier qui sera la distance dite de Mannathan
	 */
	public int distanceManhattan(int i) {
		int[] pos=this.indexOf(i);
		int[] posFin=this.situationFinale.getDamierFin().get(i);
		return (int)(Math.abs(posFin[1]-pos[1])+Math.abs(posFin[0]-pos[0]));
	}
	/**
	 * Methode pour savoir si le jeu est resolu
	 * 
	 * @return Un boolean true si le jeu est resolue, false sinon
	 */
	public boolean estResolu() {
		return situationFinale.equals(damier);
	}
	/**
	 * Fonction d'estimations
	 * <p>
	 * Permet de savoir le nombre de coups minimal à réaliser pour que le jeu soit fini
	 * </p>
	 * @return
	 * Le nombre de permution pour amener toutes les cases à leurs positions finales
	 */
	public int nbPermutFin(){
		int res=0;
		for(int j=0; j<damier.length;j++)
			for(int i=0; i<damier[0].length;i++)
				if(damier[j][i]!=0)
					res+=this.distanceManhattan(damier[j][i]);
		return res;
	}
	/**
	 * Donne les voisins d'un jeu
	 * <p>
	 * Permet de sous forme de liste tout les voisins de la positions courantes
	 * </p>
	 * @return
	 * Une ArrayList contenant tout les jeux voisins
	 */
	public ArrayList<Jeu> succ(){
		//System.out.println("Succ traite le Taquin : \n"+this);
		ArrayList<Jeu> res=new ArrayList<Jeu>();
		for(Action p : commande.getListeDesClefs()){
			try{
				//System.out.println("On y vas");
				this.deplacement(p);
				res.add(new Taquin(p,this));
				//System.out.println("On revient");
				this.deplacement(p.getInverse());
				//System.out.println(this);
			}catch (IndexOutOfBoundsException | MauvaiseTouche e) {}
		}
		//System.out.println("On renvoie "+ res.size()+" succeseurs");
		return res;
	}
	/**
	 * Fonction d'indentification
	 * <p>
	 * Cette fonction retourne un entier qui permet de savoir si deux jeu sont identique, ce nombre est calculer de tel maniere
	 * que deux jeux différents peuvent avoir le identifiant. On calcule le HashCode uniquement sur la grille de jeu sans prendre
	 * en compte la profondeur, le père, ou l'action précédente.
	 * </p>
	 * @return
	 * L'entier identifiant du jeu courant
	 */
	public int hashCode(){
		int hash=1;
		for(int i=0;i<this.damier.length;i++){
			for(int j=0;j<this.damier[0].length;j++){
				hash=hash*107+damier[i][j];
			}
		}
		return hash;
	}

	/**
	 * Affichage du jeu
	 */
	public String toString() {
		String s="";
		for(int i=0; i<damier.length; i++){
			for(int j=0; j<damier[0].length;j++){
				s+=damier[i][j]+"\t";
			}
			s+="\n";
		}
		return s;
	}
	
	/**
	 * Fonction d'égualité
	 * <p>
	 * On se sert du HashCode pour savoir si deux taquins sont identiques
	 * </p>
	 * @param o
	 * L'objet à comparer
	 * @return
	 * Un boolean vrai si les taquin sont identiques et faus sinon
	 */
	public boolean equals(Object o){
		if(o instanceof Taquin){
			Taquin t =(Taquin)o;
			return t.hashCode()==this.hashCode();
		}
		else return false;
	}

	/**
	 * Methode qui permet de determiner si ce taquin
	 * est resolvable ou non en regardant si le nombre de 
	 * permutation permettant d avoir un taquin resolu est identique
	 * a la distance de Mannathan de 0
	 *  @return vrai si le taquin est resolvable, faux sinon
	 */
	public boolean estSoluble(){
		return this.distanceManhattan(0)%2==this.nbPermutFin()%2;
	}

	/**
	 * Setteur de la situation finale
	 * @param situationFinale
	 * La situation final correspondant au jeu courant
	 */
	public void setSituationFinale(PositionFinale situationFinale) {
		this.situationFinale = situationFinale;
	}

	/**
	 * Getteur du père
	 * @return
	 * Le taquin pere du taquin courant
	 */
	public Taquin getPere() {
		return pere;
	}

	/**
	 * Getteur de l'action
	 * @return
	 * L'action qui à mené au jeu courant
	 */
	public Action getAction() {
		return action;
	}
	
	/**
	 * Getteur des commandes
	 * @return
	 * L'objet commande associé au jeu
	 */
	public Commande getCommande() {
		return commande;
	}
	
	/**
	 * Getteur de la profondeur
	 * @return
	 * La profondeur du Taquin courant, son nombre de pere
	 */
	public int getProfondeur() {
		return profondeur;
	}
	
	/**
	 * Getteur de la situation finale
	 * @return
	 * La situation finale correspondant au Taquin courant
	 */
	public PositionFinale getSituationFinale() {
		return situationFinale;
	}

	/**
	 * Getteur du nombre de coups minimale pour arriver à une solution
	 * @return
	 * Le nombre en question
	 */
	public int getNbCoupsfinale() {
		return nbCoupsfinale;
	}

	/**
	 * Setteur du nombre de coups minimal
	 * @param nbCoupsfinale
	 * Le nombre de coups minimal pour arriver à une solution
	 */
	public void setNbCoupsfinale(int nbCoupsfinale) {
		this.nbCoupsfinale = nbCoupsfinale;
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
	
	
	public Jeu clone(){
		Taquin res = new Taquin(damier.length, damier[0].length, commande);
		//TODO
		return res;
	}
	
}
