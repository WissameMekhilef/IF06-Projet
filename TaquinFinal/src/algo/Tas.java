package algo;

import java.util.Comparator;
import java.util.PriorityQueue;

import jeu.*;

public class Tas implements EnsembleATraiter {
	
	private PriorityQueue<Jeu> file;
	private int nombrePositionTraite;
	
	public Tas(Comparator<Jeu> c){
		file=new PriorityQueue<Jeu>(11,c);
		nombrePositionTraite=0;
	}
	
	public boolean nonVide() {
		return !file.isEmpty();
	}

	public Jeu prend() {
		nombrePositionTraite++;
		return file.poll();
	}

	public boolean ajout(Jeu p) {
		return file.add(p);
	}

	public int positionTraite() {
		return nombrePositionTraite;
	}

	public boolean premierAjout(Jeu initial) {
		return ajout(initial);
	}

}
