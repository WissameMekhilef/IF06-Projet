package comparateurs;

import java.util.*;

import jeu.*;


public class Manhattan implements Comparator<Jeu>{
	/**
	 * Fonction de comparaisons
	 * <p>
	 * On utilise dans ce comparateur uniquement la distance de manhattan
	 * </p>
	 * @param t
	 * Le 1er jeu à comparer
	 * @param t1
	 * Le 2eme jeu à comparer
	 * @return
	 * -1 : t1 plus petit t2
	 * 0 : t1 = t2
	 * 1 : t1 plus grand t2
	 */
	public int compare(Jeu t, Jeu t1) {
		int m1=t.getNbCoupsfinale();
		int m2=t1.getNbCoupsfinale();
		if(m1<m2)
			return -1;
		else if(m1==m2)
			return 0;
		return 1;
	}
	
}
