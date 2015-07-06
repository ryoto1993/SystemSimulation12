package Simulator;

import Utils.CalcTools;

/**
 * Created by Ryoto on 7/7/2015.
 */
public class Office {
    private Desk[] desks;
    private Light[] lights;
    private double height;

    public Office() {
        height = 2.0;
    }

    public void createLights(int num) {
        lights = new Light[num];
    }
    public void createDesks(int num) {
        desks = new Desk[num];
    }
    public void constructDesks(int id, double x, double y) {
        desks[id] = new Desk(x, y, 0);
    }
    public void constructLights(int id, double x, double y) {
        lights[id] = new Light(x, y, height);
    }
    public void getLightsInfo() {
        for(int i=0; i<lights.length; i++) {
            System.out.print("照明" + String.format("%-2d", i) + " -> ");
            lights[i].getInfo();
            System.out.println();
        }
        System.out.println();
    }
    public void getDesksInfo() {
        for(int i=0; i<desks.length; i++) {
            System.out.print("デスク" + String.format("%-2d", i) + " -> ");
            desks[i].getInfo();
            System.out.println();
        }
        System.out.println();
    }
    public Desk getDesk(int id) {
        return desks[id];
    }
    public Light getLight(int id) {
        return lights[id];
    }
    public void calculateDesksIlluminance() {
        for(int i=0; i<desks.length; i++) {
            desks[i].setIlluminance(CalcTools.calcIlluminance(lights, desks[i]));
        }
    }
}
