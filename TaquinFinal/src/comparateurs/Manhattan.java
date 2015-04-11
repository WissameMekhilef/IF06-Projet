package comparateurs;

import java.util.*;

import jeu.Taquin;


public class Manhattan implements Comparator<Taquin>{

	public int compare(Taquin t, Taquin t1) {
		int m1=t.nbPermutFin();
		int m2=t1.nbPermutFin();
		if(m1<m2)
			return -1;
		else if(m1==m2)
			return 0;
		return 1;
	}
	
}
