package Utils;

import Simulator.Desk;
import Simulator.Light;
import Simulator.Office;

import static org.jenetics.engine.EvolutionResult.*;
import static org.jenetics.engine.limit.bySteadyFitness;

import org.jenetics.*;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionStatistics;

/**
 * Created by Ryoto on 7/7/2015.
 */
public class GASolution {
    public static Office calcOffice;

    private static int eval(final Genotype<IntegerGene> gt) {
        int error = 0;
        int[] lum = new int[12];

        for(int i=0; i<12; i++) {
            lum[i] = gt.getChromosome().getGene(i).intValue();
        }

        // ToDo: calculate minimul error here.
        for(int i=0; i<calcOffice.desks.length; i++) {
            int tmp = calcIlluminance(lum, calcOffice.desks[i]) - calcOffice.desks[i].getTagretIlluminance();
            tmp = tmp < 0 ? -tmp : tmp;
            error = error < tmp ? tmp : error;
        }


        return error;
    }

    public GASolution() {
        calcOffice = new Office();

        calcOffice.createDesks(6);
        calcOffice.createLights(12);

        // Æ–¾Ý’u
        int tmp = 0;
        for(int i=0; i<3; i++)
            for(int j=0; j<4; j++) {
                calcOffice.constructLights(tmp, j * 1.8, i * 1.8);
                tmp++;
            }

        // ƒfƒXƒNÝ’u
        tmp = 0;
        for(int i=0; i<2; i++)
            for(int j=0; j<3; j++) {
                calcOffice.constructDesks(tmp, 1.0 + 1.2*j, 0.75 + 0.7*i);
                tmp++;
            }
        calcOffice.getDesk(0).setUser("‘O“c");
        calcOffice.getDesk(0).setTargetIlluminance(700);
        calcOffice.getDesk(1).setUser("‘å“‡");
        calcOffice.getDesk(1).setTargetIlluminance(500);
        calcOffice.getDesk(2).setUser("‹g“c");
        calcOffice.getDesk(2).setTargetIlluminance(300);
        calcOffice.getDesk(3).setUser("Žsì");
        calcOffice.getDesk(3).setTargetIlluminance(900);
        calcOffice.getDesk(4).setUser("‰J‹{");
        calcOffice.getDesk(4).setTargetIlluminance(700);
        calcOffice.getDesk(5).setUser("‹{è");
        calcOffice.getDesk(5).setTargetIlluminance(500);
    }

    public Light[] run() {
        final Engine<IntegerGene, Integer> engine = Engine
                .builder(
                        GASolution::eval ,
                        IntegerChromosome.of(0, 2000, 12)
                )
                .populationSize(5000)
                .optimize(Optimize.MINIMUM)
                .alterers(
                        new Mutator<>(0.03),
                        new MeanAlterer<>(0.08)
                )
                .build();

        final EvolutionStatistics<Integer, ?>
                statistics = EvolutionStatistics.ofNumber();

        final Phenotype<IntegerGene, Integer> best = engine.stream()
                .limit(bySteadyFitness(2500))
                .limit(10000)
                .peek(statistics)
                .collect(toBestPhenotype());

        System.out.println(statistics);
        System.out.println(best);

        for(int i=0; i<12; i++) {
            calcOffice.getLight(i).setLuminosity(best.getGenotype().getChromosome().getGene(i).intValue());
        }

        System.out.println(CalcTools.calcSumErrors(calcOffice.getLights(), calcOffice.getDesks()));

        return calcOffice.getLights();
    }

    public static int calcIlluminance(int[] lights, Desk desk) {
        int illuminance = 0;

        for(int i=0; i<lights.length; i++) {
            illuminance += 2.0
                    / Math.pow(desk.getPoint().distance(calcOffice.lights[i].getPoint()), 3.0)
                    * lights[i];
        }

        return illuminance;
    }


}
