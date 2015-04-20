package comparateurs;

import java.util.Comparator;

import jeu.*;

public class DepthManhattan implements Comparator<Jeu> {
	
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
