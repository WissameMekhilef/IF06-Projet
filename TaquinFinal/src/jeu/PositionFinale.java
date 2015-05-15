package jeu;

import java.util.HashMap;

public class PositionFinale {
	
	private HashMap<Integer, int[]> damierFin;
	
	/**
	 * Constructeur de l'objet PositionFinale pour eviter la surcharge de code
	 * @param pPosFinale
	 */
	public PositionFinale(HashMap<Integer, int[]> pPosFinale){
		damierFin = pPosFinale;
	}

	/**
	 * Getter de la position finale.
	 * @return
	 * sous forme de map.
	 */
	public HashMap<Integer, int[]> getDamierFin() {
		return damierFin;
	}

}
