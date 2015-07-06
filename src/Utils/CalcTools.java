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
}
