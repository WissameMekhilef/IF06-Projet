package junit;

import static org.junit.Assert.*;

import com.carrotsearch.junitbenchmarks.*;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

import jeu.*;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

@AxisRange(min = 0, max = 0.05)
@BenchmarkMethodChart(filePrefix = "testMethodeTaquin")
@BenchmarkOptions(callgc = false, benchmarkRounds = 50)

public class TaquinSpeed{
	Commande commande= new Commande();
	Taquin taq1;
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
		
	@Before
	public void setUp(){
		taq1 = new Taquin(20,24,commande);
	}
	
	@Test
	public void testSolvable(){
		taq1.estSoluble();
	}
	@Test
	public void testManhattan(){
		taq1.nbPermutFin();
	}
	
	@Test
	public void testResolu(){
		taq1.estResolu();
	}
	
}
