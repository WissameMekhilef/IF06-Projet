package comparateurs;

import java.util.*;
import java.util.Map.Entry;

import jeu.Taquin;


public class Mannathan implements Comparator<Taquin>{

	public int compare(Taquin t, Taquin t1) {
		int s=0, s2=0;
		Iterator<Entry<Integer, Integer[]>> it= t.getDamierFin().entrySet().iterator();
		while(it.hasNext()){
			Entry<Integer, Integer[]> e=it.next();
			if(e.getKey()!=0) s+=t.distanceManhattan(e.getKey());
		}
		Iterator<Entry<Integer, Integer[]>> it2= t1.getDamierFin().entrySet().iterator();
		while(it2.hasNext()){
			Entry<Integer, Integer[]> e=it2.next();
			if(e.getKey()!=0) s2+=t1.distanceManhattan(e.getKey());
		}
		return s-s2;
	}
	
}
