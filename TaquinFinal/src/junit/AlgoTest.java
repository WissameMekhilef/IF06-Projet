package junit;
import static org.junit.Assert.*;

import org.junit.Test;

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
@BenchmarkMethodChart(filePrefix = "testDesAlgo")
@BenchmarkOptions(callgc = false, benchmarkRounds = 50, warmupRounds = 1)

public class AlgoTest extends AbstractBenchmark{
	HashMap<String, int[]> commande;
	Taquin taq1;
	int tailleEnsembleIncomplet=1213;
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
		
	@Before
	public void setUp(){
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
		
		taq1=new Taquin(3,3,commande);
	}
	
	@Test
	public void FileIncomplet(){
		runTest(taq1, new EnsembleIncomplet(tailleEnsembleIncomplet), new File());
	}
	
	@Test
	public void FileComplet(){
		runTest(taq1, new EnsembleComplet(), new File());
	}
	
	@Test
	public void PileIncomplet(){
		runTest(taq1, new EnsembleIncomplet(tailleEnsembleIncomplet), new Pile());
	}

/*	@Test
	public void PileComplet(){
		runTest(taq1, new EnsembleComplet(), new Pile());
	}*/
	
/*	@Test
	public void ManhattanIncomplet(){
		runTest(taq1, new EnsembleIncomplet(tailleEnsembleIncomplet), new Tas(new Manhattan()));
	}*/
	
/*	@Test
	public void ManhattanComplet(){
		runTest(taq1, new EnsembleComplet(), new Tas(new Manhattan()));
	}*/
	
/*	@Test
	public void PManhattanIncomplet(){
		runTest(taq1, new EnsembleIncomplet(tailleEnsembleIncomplet), new Tas(new DepthManhattan()));
	}*/
	
/*	@Test
	public void PManhattanComplet(){
		runTest(taq1, new EnsembleComplet(), new Tas(new DepthManhattan()));
	}*/
	
	/**
	 * Execute un algo
	 * @param jeu
	 * Le jeu à résoudre
	 * @param em
	 * L'ensemble marqué
	 * @param eat
	 * L'ensemble à traiter
	 */
	private void runTest(Jeu jeu, EnsembleMarque em, EnsembleATraiter eat){
		System.out.println("Nouveau test en cours");
		Algo algo = new Algo(jeu, eat, em);
		algo.run();
		//assertTrue("Doit etre resolu",algo.getFinale().estResolu());
	}
	

}
