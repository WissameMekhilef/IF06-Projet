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

public class AlgoSpeed extends AbstractBenchmark{
	static Commande commande=new Commande();
	Jeu jeu;
	int tailleEnsembleIncomplet=200383;
		
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
			
	@Test
	public void FileIncomplet(final Jeu taq){
		runTest(jeu, new EnsembleIncomplet(tailleEnsembleIncomplet), new File());
	}
	
	@Test
	public void FileComplet(){
		runTest(jeu, new EnsembleComplet(), new File());
	}
	
	@Test
	public void PileIncomplet(){
		runTest(jeu, new EnsembleIncomplet(tailleEnsembleIncomplet), new Pile());
	}

	@Test
	public void PileComplet(){
		runTest(jeu, new EnsembleComplet(), new Pile());
	}
	
	@Test
	public void ManhattanIncomplet(){
		runTest(jeu, new EnsembleIncomplet(tailleEnsembleIncomplet), new Tas(new Manhattan()));
	}
	
	@Test
	public void ManhattanComplet(){
		runTest(jeu, new EnsembleComplet(), new Tas(new Manhattan()));
	}
	
	@Test
	public void PManhattanIncomplet(){
		runTest(jeu, new EnsembleIncomplet(tailleEnsembleIncomplet), new Tas(new DepthManhattan()));
	}
	
	@Test
	public void PManhattanComplet(){
		runTest(jeu, new EnsembleComplet(), new Tas(new DepthManhattan()));
	}
	
	public AlgoSpeed(Jeu aTester){
		jeu=aTester;
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
		Algo algo = new Algo(jeu, eat, em, false);
		algo.run();
	}
	
	public static void main(String [] args){
		
	}
	

}
