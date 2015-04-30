package algo;

import java.util.ArrayList;

import jeu.*;


public class File implements EnsembleATraiter{
	
	private ArrayList<Jeu> file;
	private int nombrePositionTraite;
	
	public File(){
		file= new ArrayList<Jeu>();
		nombrePositionTraite=0;
	}

	public boolean nonVide() {
		return !file.isEmpty();
	}

	public Jeu prend() {
		nombrePositionTraite++;
		return file.remove(0);
	}

	public boolean ajout(Jeu p) {
		return file.add(p);
	}


	public int positionTraite() {
		return nombrePositionTraite;
	}

	@Override
	public boolean premierAjout(Jeu initial) {
		return ajout(initial);
	}

}
