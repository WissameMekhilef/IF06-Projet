package algo;

import java.util.ArrayList;

import jeu.Taquin;


public class File implements EnsembleATraiter{
	
	private ArrayList<Taquin> file;
	
	public File(){
		file= new ArrayList<Taquin>();
	}
	

	public boolean nonVide() {
		return !file.isEmpty();
	}

	public Taquin prend() {
		return file.remove(0);
	}

	public boolean appartient(Taquin p) {
		return file.contains(p);
	}

	public boolean ajout(Taquin p) {
		return file.add(p);
	}

}
