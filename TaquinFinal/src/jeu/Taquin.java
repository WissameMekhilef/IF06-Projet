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
	private int ameliorant;
	
	public Taquin(String destfic, Commande pCommande) throws NombreDouble, NumberFormatException, FileNotFoundException, IOException{
		BufferedReader fic = new BufferedReader( new FileReader (destfic));
		ArrayList<Integer> nombresPresent = new ArrayList<Integer>();
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
		action=null;
		
		Taquin t = new Taquin(damier.length, damier[0].length, commande);
		situationFinale=t.situationFinale;
		nbCoupsfinale=nbPermutFin();
		
		fic.close();
	
	}
	
	/**
	 * Constructeur d'un Taquin
	 * 
	 * @param le nombre de lignes et de colonnes
	 * <p>
	 * Taille de la matrice
	 * </p>
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
		nbCoupsfinale=pere.nbCoupsfinale+pere.ameliorant;
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
	 * @throws MauvaiseTouche 
	 */
	public void deplacement(Action direction) throws MauvaiseTouche{
		//System.out.println("\t\t\tAppel a indexOf de 0");
		int[] pos0=this.indexOf(0);
		if(commande.getDeplacement().containsKey(direction)){
			int temp=damier[pos0[0]][pos0[1]];
			int ancienMan=distanceManhattan(temp);
			//System.out.println("La distance de manhattan de l'ancienne position eé : "+ancienMan);
			int[] posX=commande.getDeplacement().get(direction);
			damier[pos0[0]][pos0[1]]=damier[pos0[0]+posX[0]][pos0[1]+posX[1]];
			damier[pos0[0]+posX[0]][pos0[1]+posX[1]]=0;
			
			
			ameliorant=ancienMan-distanceManhattan(temp);
			nbCoupsfinale=nbPermutFin();
		}else throw new MauvaiseTouche();
	}
	/**
	 * Methode qui determine la distance entre la position initiale
	 * de la case vide que l'on nommera ini et sa position finale
	 * pour que le taquin puisse pretendre etre resolu dont on 
	 * nommera fin : Dman=|Xfin-Xini|+|Yfin-Yini|
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
	 * 
	 * @return
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
	 * 
	 */
	public ArrayList<Jeu> succ(){
		ArrayList<Jeu> res=new ArrayList<Jeu>();
		int[][] first=this.copieTableau();
		for(Action p : commande.getListeDesClefs()){
			try{
				this.deplacement(p);
				res.add(new Taquin(p,this));
				this.setDamier(first);
			}catch (IndexOutOfBoundsException | MauvaiseTouche e) {}
		}
		return res;
	}

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
	 * @return un copieTableau du tableau nommer damier
	 */
	public int[][] copieTableau(){
		int[][]t=new int[damier.length][damier[0].length];
		for(int i=0;i<this.damier.length;i++){
			for(int j=0;j<this.damier[0].length;j++){
				t[i][j]=damier[i][j];
			}
		}
		return t;
	}
	/**
	 * 
	 * @param d
	 */
	
	public void setDamier(int[][] d){
		for(int i=0;i<d.length;i++){
			for(int j=0;j<d[0].length;j++){
				this.damier[i][j]=d[i][j];
			}
		}
		nbCoupsfinale=nbPermutFin();
	}
	public void setSituationFinale(PositionFinale situationFinale) {
		this.situationFinale = situationFinale;
	}

	/**
	 * 
	 * @return
	 */
	public Taquin getPere() {
		return pere;
	}

	/**
	 * 
	 * @return
	 */
	public Action getAction() {
		return action;
	}

	public int[][] getDamier() {
		return damier;
	}

	public Commande getCommande() {
		return commande;
	}

	public int getProfondeur() {
		return profondeur;
	}

	public PositionFinale getSituationFinale() {
		return situationFinale;
	}

	public int getNbCoupsfinale() {
		return nbCoupsfinale;
	}

	public void setNbCoupsfinale(int nbCoupsfinale) {
		this.nbCoupsfinale = nbCoupsfinale;
	}
	
	public String description() {
		int n = 0;
		for(int i = 1; i < damier.length * damier[0].length; i++) n += distanceManhattan(i);
		return "Le jeu de taquin a resoudre etait de taille " + damier.length + "x" + damier[0].length
				+ ".\nLa distance de Manhattan de la case vide valait " + distanceManhattan(0)
				+ ".\nLa somme des distances des autres cases etait de " + n + ".";
	}
	
}
