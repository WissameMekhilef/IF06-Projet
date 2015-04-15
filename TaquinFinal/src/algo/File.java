package algo;

import java.util.ArrayList;

import jeu.*;


public class File implements EnsembleATraiter{
	
	private ArrayList<Jeu> file;
	
	public File(){
		file= new ArrayList<Jeu>();
	}
	

	public boolean nonVide() {
		return !file.isEmpty();
	}

	public Jeu prend() {
		return file.remove(0);
	}

	public boolean appartient(Jeu p) {
		return file.contains(p);
	}

	public boolean ajout(Jeu p) {
		return file.add(p);
	}

}
