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

@AxisRange(min = 0, max = 0.05)
@BenchmarkMethodChart(filePrefix = "testdesalgo")
@BenchmarkOptions(callgc = false, benchmarkRounds = 50)

public class AlgoTest extends AbstractBenchmark{
	Commande commande=new Commande();
	Taquin taq1;
	int tailleEnsembleIncomplet=1213;
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
		
	@Before
	public void setUp(){
		taq1=new Taquin(3,2,commande);
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

	@Test
	public void PileComplet(){
		runTest(taq1, new EnsembleComplet(), new Pile());
	}
	
	@Test
	public void ManhattanIncomplet(){
		runTest(taq1, new EnsembleIncomplet(tailleEnsembleIncomplet), new Tas(new Manhattan()));
	}
	
	@Test
	public void ManhattanComplet(){
		runTest(taq1, new EnsembleComplet(), new Tas(new Manhattan()));
	}
	
	@Test
	public void PManhattanIncomplet(){
		runTest(taq1, new EnsembleIncomplet(tailleEnsembleIncomplet), new Tas(new DepthManhattan()));
	}
	
	@Test
	public void PManhattanComplet(){
		runTest(taq1, new EnsembleComplet(), new Tas(new DepthManhattan()));
	}
	
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
