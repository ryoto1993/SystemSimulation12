package Utils;

import Simulator.Light;
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
    public static Office office;

    private static int eval(final Genotype<IntegerGene> genotype) {
        Office tempOffice = office;

        for(int i=0; i<12; i++) {
            tempOffice.getLight(i).setLuminosity(genotype.getChromosome().getGene(i).intValue());
        }

        // ToDo: calculate minimul error here.
        int tmp = CalcTools.calcSumErrors(tempOffice.getLights(), tempOffice.getDesks());

        return tmp;
    }

    public GASolution(Office office) {
        this.office = office;
    }

    public Light[] run() {
        final Engine<IntegerGene, Integer> engine = Engine
                .builder(
                        GASolution::eval ,
                        IntegerChromosome.of(0, 2000, 12)
                )
                .populationSize(1000)
                .optimize(Optimize.MINIMUM)
                .alterers(
                        new Mutator<>(0.03),
                        new MeanAlterer<>(0.08)
                )
                .build();

        final EvolutionStatistics<Integer, ?>
                statistics = EvolutionStatistics.ofNumber();

        final Phenotype<IntegerGene, Integer> best = engine.stream()
                .limit(bySteadyFitness(500))
                .limit(10000)
                .peek(statistics)
                .collect(toBestPhenotype());

        System.out.println(statistics);
        System.out.println(best);

        for(int i=0; i<12; i++) {
            this.office.getLight(i).setLuminosity(best.getGenotype().getChromosome().getGene(i).intValue());
        }

        System.out.println(CalcTools.calcSumErrors(this.office.getLights(), this.office.getDesks()));

        return this.office.getLights();
    }


}
