package jeu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Commande {
	private HashMap<Action, int[]> deplacement;
	private Set<Action> listeDesClefs;
	private Action[] tabClef;
	private HashMap<String, Action> tabCorrespondance;

	public Commande(){
		deplacement=new HashMap<Action, int[]>();
		int[] t1=new int[2];
		t1[0]=-1;t1[1]=0;
		deplacement.put(new Action("Nord", new Action("Sud")), t1);
		
		int[] t2=new int[2];
		t2[0]=1;t2[1]=0;
		deplacement.put(new Action("Sud", new Action("N")), t2);
		
		int[] t3=new int[2];
		t3[0]=0;t3[1]=-1;
		deplacement.put(new Action("Est", new Action("Ouest")), t3);
		
		int[] t4=new int[2];
		t4[0]=0;t4[1]=1;
		deplacement.put(new Action("Ouest", new Action("Est")), t4);
		
		listeDesClefs=deplacement.keySet();
		tabClef = new Action[listeDesClefs.size()];
		Iterator<Action> it=listeDesClefs.iterator();
		int i=0;
		while(it.hasNext()){
			tabClef[i]=it.next();
			i++;
		}

		//Initialisation du tableau des correspondance entre les touches tappé et l'action souhaité
		tabCorrespondance= new HashMap<String,Action>();
		
		tabCorrespondance.put("z",tabClef[0]);
		tabCorrespondance.put("s",tabClef[1]);
		tabCorrespondance.put("q",tabClef[2]);
		tabCorrespondance.put("d",tabClef[3]);
	}

	public HashMap<String, Action> getTabCorrespondance() {
		return tabCorrespondance;
	}

	public void setTabCorrespondance(HashMap<String, Action> tabCorrespondance) {
		this.tabCorrespondance = tabCorrespondance;
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
