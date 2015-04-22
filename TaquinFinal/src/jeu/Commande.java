package jeu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Commande {
	private HashMap<Action, int[]> deplacement;
	private Set<Action> listeDesClefs;
	private Action[] tabClef;

	public Commande(){
		deplacement=new HashMap<Action, int[]>();
		int[] t1=new int[2];
		t1[0]=-1;t1[1]=0;
		deplacement.put(new Action("z", new Action("s")), t1);
		
		int[] t2=new int[2];
		t2[0]=1;t2[1]=0;
		deplacement.put(new Action("s", new Action("z")), t2);
		
		int[] t3=new int[2];
		t3[0]=0;t3[1]=-1;
		deplacement.put(new Action("q", new Action("d")), t3);
		
		int[] t4=new int[2];
		t4[0]=0;t4[1]=1;
		deplacement.put(new Action("d", new Action("q")), t4);
		
		listeDesClefs=deplacement.keySet();
		tabClef = new Action[listeDesClefs.size()];
		Iterator<Action> it=listeDesClefs.iterator();
		int i=0;
		while(it.hasNext()){
			tabClef[i]=it.next();
			i++;
		}
	}

	public HashMap<Action, int[]> getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(HashMap<Action, int[]> deplacement) {
		this.deplacement = deplacement;
	}
	
	public Set<Action> getListeDesClefs() {
		return listeDesClefs;
	}

	public void setListeDesClefs(Set<Action> listeDesClefs) {
		this.listeDesClefs = listeDesClefs;
	}

	public Action[] getTabClef() {
		return tabClef;
	}

	public void setTabClef(Action[] tabClef) {
		this.tabClef = tabClef;
	}
	
	
}
