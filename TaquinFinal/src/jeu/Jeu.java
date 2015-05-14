package jeu;

import exceptions.MauvaiseTouche;

import java.util.ArrayList;

public interface Jeu {
	
	public Jeu clone();
	
	public void deplacement(Action direction) throws IndexOutOfBoundsException, MauvaiseTouche;
	
	public String description();
	
	public boolean estResolu();
	
	public boolean estSoluble();
	
	public Action getAction();
	
	public Commande getCommande();
	
	public int getNbCoupsfinale();
	
	public Jeu getPere();
	
	public int getProfondeur();
	
	public PositionFinale getSituationFinale();
	
	public ArrayList<Jeu> succ();
	
}
