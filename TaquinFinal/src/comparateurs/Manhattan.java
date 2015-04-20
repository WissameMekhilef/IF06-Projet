package comparateurs;

import java.util.*;

import jeu.*;


public class Manhattan implements Comparator<Jeu>{

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
