package jeu;

import exceptions.MauvaiseTouche;

public interface Jeu {
	/**
	 * Effectuer un deplacement
	 * @param direction
	 * L'entier correspondant au deplacement
	 * @throws MauvaiseTouche 
	 * @throws ImpossibleMoveException
	 * Une exception est leve si le deplacement est impossible
	 */
	public void deplacement(String direction) throws IndexOutOfBoundsException, MauvaiseTouche;
	/**
	 * Affiche le jeu
	 * @return
	 * Un string
	 */
	public String toString();
	/**
	 * Permet de savoir si un jeu est resolu
	 * @return
	 * Un boolean true si le jeu est resolue et false sinon
	 */
	public boolean estResolu();
	
}
