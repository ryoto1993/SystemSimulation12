package Simulator;

import Utils.GASolution;

/**
 * Created by Ryoto on 7/7/2015.
 * SystemSimulation
 */
public class Main {
    public static Office office;
    public static void main(String[] args) {

        office = new Office();

        office.createDesks(6);
        office.createLights(12);

        // 照明設置
        int tmp = 0;
        for(int i=0; i<3; i++)
        for(int j=0; j<4; j++) {
            office.constructLights(tmp, j * 1.8, i * 1.8);
            tmp++;
        }

        // デスク設置
        tmp = 0;
        for(int i=0; i<2; i++)
        for(int j=0; j<3; j++) {
            office.constructDesks(tmp, 1.0 + 1.2*j, 0.75 + 0.7*i);
            tmp++;
        }
        office.getDesk(0).setUser("前田");
        office.getDesk(0).setTargetIlluminance(700);
        office.getDesk(1).setUser("大島");
        office.getDesk(1).setTargetIlluminance(500);
        office.getDesk(2).setUser("吉田");
        office.getDesk(2).setTargetIlluminance(300);
        office.getDesk(3).setUser("市川");
        office.getDesk(3).setTargetIlluminance(900);
        office.getDesk(4).setUser("雨宮");
        office.getDesk(4).setTargetIlluminance(700);
        office.getDesk(5).setUser("宮崎");
        office.getDesk(5).setTargetIlluminance(500);

        // 計算
        // office.getLight(0).setLuminosity(300);
        // office.getLight(1).setLuminosity(500);
        new GASolution(office);

        office.calculateDesksIlluminance();

        // 環境チェック
        office.getLightsInfo();
        office.getDesksInfo();

    }
}
