package comparateurs;

import java.util.Comparator;

import jeu.Taquin;

public class ManhattanProfondeur implements Comparator<Taquin> {

	public int compare(Taquin t, Taquin t1) {
		return t.nbPermutFin()<t1.nbPermutFin()?1:0;
	}

}
