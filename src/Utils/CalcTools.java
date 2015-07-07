package Utils;

import Simulator.Desk;
import Simulator.Light;

/**
 * Created by Ryoto on 7/7/2015.
 */
public class CalcTools {
    private CalcTools() {}



    public static int calcIlluminance(Light[] lights, Desk desk) {
        int illuminance = 0;

        for(int i=0; i<lights.length; i++) {
            illuminance += 2.0
                    / Math.pow(desk.getPoint().distance(lights[i].getPoint()), 3.0)
                    * lights[i].getLuminosity();
        }

        return illuminance;
    }

    public static int calcSumErrors(Light[] lights, Desk[] desks) {
        int error = 0;

        // “ñæŒë·
        /*
        for(int i=0; i<desks.length; i++) {
            error += Math.pow((calcIlluminance(lights, desks[i]) - desks[i].getTagretIlluminance()), 2.0);
        }
        */
        // Å‘åŒë·
        for(int i=0; i<desks.length; i++) {
            int tmp = calcIlluminance(lights, desks[i]) - desks[i].getTagretIlluminance();
            tmp = tmp < 0 ? -tmp : tmp;
            error = error < tmp ? tmp : error;
        }

        return error;
    }
}
