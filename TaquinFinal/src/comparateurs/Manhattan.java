package comparateurs;

import java.util.*;

import jeu.Taquin;


public class Manhattan implements Comparator<Taquin>{

	public int compare(Taquin t, Taquin t1) {
		return (t.nbPermutFin()<t1.nbPermutFin())?1:0;
	}
	
}
