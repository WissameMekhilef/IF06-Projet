package jeu;

import java.util.HashMap;

public class PositionFinale {
	
	private HashMap<Integer, int[]> damierFin;
	
	public PositionFinale(HashMap<Integer, int[]> pPosFinale){
		damierFin = pPosFinale;
	}

	public HashMap<Integer, int[]> getDamierFin() {
		return damierFin;
	}

}
