package comparateurs;

import java.util.Comparator;

import jeu.Taquin;

public class DepthManhattan implements Comparator<Taquin> {
	
	public int compare(Taquin t1, Taquin t2) {
		return (t1.nbPermutFin() + t1.getProfondeur())<(t2.nbPermutFin() + t2.getProfondeur())?1:0;
	}

}
