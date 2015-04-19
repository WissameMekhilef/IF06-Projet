package jeu;

import java.util.HashMap;

public class Commande {
	private HashMap<String, int[]> deplacement;
	
	public Commande(){
		deplacement=new HashMap<String, int[]>();
		int[] t1=new int[2];
		t1[0]=-1;t1[1]=0;
		deplacement.put("z", t1);
		
		int[] t2=new int[2];
		t2[0]=1;t2[1]=0;
		deplacement.put("s", t2);
		
		int[] t3=new int[2];
		t3[0]=0;t3[1]=-1;
		deplacement.put("q", t3);
		
		int[] t4=new int[2];
		t4[0]=0;t4[1]=1;
		deplacement.put("d", t4);
	}

	public HashMap<String, int[]> getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(HashMap<String, int[]> deplacement) {
		this.deplacement = deplacement;
	}
	
}
