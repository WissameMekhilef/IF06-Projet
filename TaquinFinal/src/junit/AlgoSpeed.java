package junit;
import jeu.Commande;
import jeu.Jeu;
import jeu.Taquin;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import algo.Algo;
import algo.EnsembleATraiter;
import algo.EnsembleComplet;
import algo.EnsembleIncomplet;
import algo.EnsembleMarque;
import algo.File;
import algo.Pile;
import algo.Tas;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;
import comparateurs.DepthManhattan;
import comparateurs.Manhattan;

@AxisRange(min = 0, max = 0.05)
@BenchmarkMethodChart(filePrefix = "testdesalgo")
@BenchmarkOptions(callgc = false, benchmarkRounds = 50)
@RunWith(JUnitParamsRunner.class)l

public class AlgoSpeed extends AbstractBenchmark{
	static Commande commande=new Commande();
	Taquin taq1;
	int tailleEnsembleIncomplet=200383;
	
	private Taquin[] parametersForTest(){
		return new Taquin[]{
				new Taquin(3,3, commande),
				new Taquin(4,4, commande)
		};
	}
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
		
	@Before
	public void setUp(){
		taq1=new Taquin(4,7,commande);
	}
	
	@Test
	@Parameters
	public void FileIncomplet(final Jeu taq){
		runTest(taq, new EnsembleIncomplet(tailleEnsembleIncomplet), new File());
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
		//System.out.println("Nouveau test en cours");
		Algo algo = new Algo(jeu, eat, em);
		algo.run();
	}
	

}
