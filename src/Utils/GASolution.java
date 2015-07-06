package Utils;

import Simulator.Office;

import static org.jenetics.engine.EvolutionResult.*;
import static org.jenetics.engine.limit.bySteadyFitness;

import org.jenetics.*;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionStatistics;

import org.jenetics.util.RandomRegistry;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Ryoto on 7/7/2015.
 */
public class GASolution {
    private Office office;

    private static int eval(final Genotype<IntegerGene> genotype) {
        final int[] randomIlluminances = new int[12];
        for(int i=0; i<12; i++) {
            randomIlluminances[i] = genotype.getGene().intValue();
        }

        // ToDo: calculate minimul error here.

        return 0;
    }

    public GASolution(Office office) {

        final Engine<IntegerGene, Integer> engine = Engine
                .builder(
                        GASolution::eval ,
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000),
                        IntegerChromosome.of(0, 2000)
                )
                .populationSize(500)
                .optimize(Optimize.MAXIMUM)
                .alterers(
                        new Mutator<>(0.03),
                        new MeanAlterer<>(0.6)
                )
                .build();

        final EvolutionStatistics<Integer, ?>
                statistics = EvolutionStatistics.ofNumber();

        final Phenotype<IntegerGene, Integer> best = engine.stream()
                .limit(bySteadyFitness(7))
                .limit(100)
                .peek(statistics)
                .collect(toBestPhenotype());

        System.out.println(statistics);
        System.out.println(best);
    }


}
