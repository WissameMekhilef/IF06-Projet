package algo;

import java.util.Comparator;
import java.util.PriorityQueue;

import jeu.*;

public class Tas implements EnsembleATraiter {
	
	private PriorityQueue<Taquin> file;
	
	public Tas(Comparator<Taquin> c){
		file=new PriorityQueue<Taquin>(200,c);
	}
	
	public boolean nonVide() {
		return !file.isEmpty();
	}

	public Taquin prend() {
		return file.peek();
	}

	public boolean appartient(Taquin p) {
		return file.contains(p);
	}

	public boolean ajout(Taquin p) {
		return file.add(p);
	}

}
