package ga;

import org.jgap.InvalidConfigurationException;
import org.jgap.Configuration;
import org.jgap.impl.DefaultConfiguration;

/**
 * Main class to call to GA Library.
 */
public class KnapsackProblemGA {

	/**
	 * Main method.
	 * Create a Configuration and Initial Genotype,
   * with the population size of configuration for JGAP Library.
	 * Evolve the problem.
	 * @param args Arguments for the main function.
	 */
	public static void main(String[] args) throws InvalidConfigurationException {
    Configuration conf = new DefaultConfiguration();
    KnapsackProblem myKnapsack = new KnapsackProblem(50);
    KnapsackFitness myFunc = new KnapsackFitness(myKnapsack);
    //TODO: Create your own Configuration and Initial Genotype
    //TODO: Evolve the population.
	}
}
