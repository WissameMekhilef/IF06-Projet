package junit;

import static org.junit.Assert.*;
import jeu.*;

import org.junit.Test;

import exceptions.MauvaiseTouche;

public class TaquinPreuve {
	private Commande commande=new Commande();
	
	@Test
	public void testSolvableCarre() {
		for(int i=2; i<20; i++){
			assertTrue("Doit etre resolu", (new Taquin(i,i,commande)).estSoluble());
		}
		
	}
	
	@Test
	public void testAmeliorant(){
		Taquin t= new Taquin(3,3,commande);
		int [][] temp = {{1,3,2},{4,0,6},{5,7,8}};
		t.setDamier(temp);
		System.out.println("La distance de Manhattan est de : "+t.getNbCoupsfinale());
		System.out.println("La distance de Manhattan doit etre de : "+t.nbPermutFin());
		System.out.println("La distance de la case '3' est : "+t.distanceManhattan(3));
		int [][] svg = t.copieTableau();
		try {
			t.deplacement("z");
		} catch (IndexOutOfBoundsException | MauvaiseTouche e) {}
		Taquin d=new Taquin("z",t);
		t.setDamier(svg);
		System.out.println("La distance de Manhattan est de : "+d.getNbCoupsfinale());
		System.out.println("La distance de Manhattan doit etre de : "+d.nbPermutFin());
		System.out.println("La distance de la case '3' est : "+d.distanceManhattan(3));
		
		//System.out.println("La distance de la case '0' est : "+t.distanceManhattan(0));
		//System.out.println("Le jeu est solvable? : "+t.estSoluble());
	}

}
