package junit;

import static org.junit.Assert.*;

import com.carrotsearch.junitbenchmarks.*;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

import jeu.*;
import algo.*;

import java.util.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "map-types-benchmark-barchart")
@BenchmarkOptions(callgc = false, benchmarkRounds = 20, warmupRounds = 3)

public class TaquinTest extends AbstractBenchmark{
	HashMap<String, int[]> commande;
	Taquin taq1, taq2, taq3, taq4;
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
		
	@Before
	public void setUp(){
		System.out.println("On initialise des commandes");
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

	
		taq1 = new Taquin(3,3,commande);
		taq2 = new Taquin(4,4,commande);
		taq3 = new Taquin(4,5,commande);
		taq4 = new Taquin(4,3,commande);

	}
	
	@Test
	public void testAlgoFileComplet(){
		Algo a1 = new Algo(taq1, new File(), new EnsembleIncomplet(2000003));
		a1.run();
		assertTrue("Doit etre resolu",a1.getFinale().estResolu());
//		Algo a2 = new Algo(taq2, new Pile(), new EnsembleComplet());
//		a2.run();	
//		assertTrue("Doit etre resolu",a2.getFinale().estResolu());
	}
	
	@Test
	public void testResolu(){
		Taquin t = new Taquin(3, 3, commande);
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
	
}
