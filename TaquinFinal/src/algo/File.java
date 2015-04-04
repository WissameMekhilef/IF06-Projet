package algo;

import java.util.ArrayList;

import jeu.Taquin;


public class File implements EnsembleATraiter{
	
	private ArrayList<Taquin> file;
	
	public File(){
		file= new ArrayList<Taquin>();
	}
	
	
	@Override
	public boolean nonVide() {
		return !file.isEmpty();
	}

	@Override
	public Taquin prend() {
		return file.remove(0);
	}

	@Override
	public boolean appartient(Taquin p) {
		return file.contains(p);
	}

	@Override
	public boolean ajout(Taquin p) {
		return file.add(p);
	}

}
