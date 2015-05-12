package comparateurs;

import java.util.Comparator;

import jeu.*;

public class DepthManhattan implements Comparator<Jeu> {
	/**
	 * Fonction de comparaisons
	 * <p>
	 * On utilise dans ce comparateur la distance de manhattan et la profondeur
	 * </p>
	 * @param t1, t2
	 * Les deux jeux à comparer
	 * @return
	 * -1 : t1 plus petit t2
	 * 0 : t1 = t2
	 * 1 : t1 plus grand t2
	 */
	public int compare(Jeu t1, Jeu t2) {
		int m1=t1.getNbCoupsfinale()+t1.getProfondeur();
		int m2=t2.getNbCoupsfinale()+t2.getProfondeur();
		if(m1<m2)
			return -1;
		else if(m1==m2)
			return 0;
		return 1;
	}

}
