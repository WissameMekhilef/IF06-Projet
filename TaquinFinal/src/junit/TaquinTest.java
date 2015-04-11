package junit;

import org.junit.*;

import jeu.Taquin;
import junit.framework.TestCase;

import java.util.*;

public class TaquinTest extends TestCase{
	HashMap<String, int[]> commande;
	
	@Before
	private void initCmde(){
		//Initialisation des deplacements
		commande=new HashMap<String, int[]>();
		
		int[] t1=new int[2];
		t1[0]=-1;t1[1]=0;
		commande.put("z", t1);
		
		int[] t2=new int[2];
		t2[0]=1;t2[1]=0;
		commande.put("s", t2);
		
		int[] t3=new int[2];
		t3[0]=0;t3[1]=-1;
		commande.put("q", t3);
		
		int[] t4=new int[2];
		t4[0]=0;t4[1]=1;
		commande.put("d", t4);		
	}
	
	@Test
	public void testSoluble(){
		
	}
	
	@Test
	public void testResolu(){
		Taquin t = new Taquin(3, 3, commande);
		int [][]d= {{1,2,3},
					{4,5,6},
					{7,8,0}};
		t.setDamier(d);
		assertTrue("Doit etre resolu",t.estResolu());
/*		int [][]f= {{2,1,3},
					{4,5,6},
					{7,8,0}}; 
		t.setDamier(f);
		assertFalse("Doit etre resolu",t.estResolu());*/
	}
	
	public static void main(String args []){
		junit.textui.TestRunner.run(TaquinTest.class);
	}
}
