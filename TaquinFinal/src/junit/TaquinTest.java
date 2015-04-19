package junit;

import static org.junit.Assert.*;

import com.carrotsearch.junitbenchmarks.*;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

import comparateurs.*;
import jeu.*;
import algo.*;

import java.util.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "testMethodeTaquin")
@BenchmarkOptions(callgc = false, benchmarkRounds = 5, warmupRounds = 1)

public class TaquinTest extends AbstractBenchmark{
	Commande commande= new Commande();
	Taquin taq1, taq2, taq3, taq4;
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
		
	@Before
	public void setUp(){
		taq1 = new Taquin(3,3,commande);
		taq2 = new Taquin(4,4,commande);
		taq3 = new Taquin(4,5,commande);
		taq4 = new Taquin(4,3,commande);

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
