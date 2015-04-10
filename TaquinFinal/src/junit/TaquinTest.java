package junit;

import org.junit.Test;

import jeu.Taquin;
import junit.framework.TestCase;

public class TaquinTest extends TestCase{
	
	@Test
	public void testSoluble(){
		
	}
	
	@Test
	public void testResolu(){
		Taquin t = new Taquin(3, 3, "N","S","E","O");
		int [][]d= {{1,2,3},
					{4,5,6},
					{7,8,0}};
		t.setDamier(d);
		assertTrue("Doit etre resolu",t.estResolu());
		int [][]f= {{2,1,3},
					{4,5,6},
					{7,8,0}}; 
		t.setDamier(f);
		assertFalse("Doit etre resolu",t.estResolu());
	}
	
	public static void main(String args []){
		junit.textui.TestRunner.run(TaquinTest.class);
	}
}
