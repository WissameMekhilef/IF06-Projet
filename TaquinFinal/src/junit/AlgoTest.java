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
@BenchmarkMethodChart(filePrefix = "map-types-benchmark-barchart")
@BenchmarkOptions(callgc = false, benchmarkRounds = 50, warmupRounds = 1)

public class AlgoTest {
	HashMap<String, int[]> commande;
	Taquin taq1;
	int[] nbPremier={149,1213,13997,21061,200383,2002009,2000423};
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
	
	@Before
	public void setUp() throws Exception {
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
	}

	@Test
	public void 
	
	private void runAlgo() {
		
	}

}
