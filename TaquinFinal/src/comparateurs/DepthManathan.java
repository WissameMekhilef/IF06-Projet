package comparateurs;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;

import jeu.Taquin;

public class DepthManathan implements Comparator<Taquin> {
	
	public int compare(Taquin t1, Taquin t2) {
		int s=0, s2=0;
		Iterator<Entry<Integer, Integer[]>> it= t1.getDamierFin().entrySet().iterator();
		while(it.hasNext()){
			Entry<Integer, Integer[]> e=it.next();
			if(e.getKey()!=0) s+=t1.distanceManhattan(e.getKey());
		}
		Iterator<Entry<Integer, Integer[]>> it2= t2.getDamierFin().entrySet().iterator();
		while(it2.hasNext()){
			Entry<Integer, Integer[]> e=it2.next();
			if(e.getKey()!=0) s2+=t2.distanceManhattan(e.getKey());
		}
		return (s + t1.getProfondeur())-(s2 + t2.getProfondeur());
	}

}
