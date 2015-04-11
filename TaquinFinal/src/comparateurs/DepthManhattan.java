package comparateurs;

import java.util.Comparator;

import jeu.Taquin;

public class DepthManhattan implements Comparator<Taquin> {
	
	public int compare(Taquin t1, Taquin t2) {
		int m1=t1.nbPermutFin()+t1.getProfondeur();
		int m2=t2.nbPermutFin()+t2.getProfondeur();
		if(m1<m2)
			return -1;
		else if(m1==m2)
			return 0;
		return 1;
	}

}
