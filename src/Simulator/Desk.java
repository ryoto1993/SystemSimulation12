package Simulator;

/**
 * Created by Ryoto on 7/7/2015.
 */
public class Desk extends Supply {
    public int illuminance;
    public int targetIlluminance;
    private String user;

    public Desk(double x, double y, double z) {
        super(x, y, z);
    }

    public void getInfo() {
        super.getInfo();
        System.out.print("希望照度:" + String.format("%4dLx ", targetIlluminance));
        System.out.print(" 照度:" + String.format("%4dLx ", illuminance));
        System.out.print(" ユーザ:" + String.format(" %-10s ", user));
    }

    public void setTargetIlluminance(int target) {
        targetIlluminance = target;
    }
    public int getTagretIlluminance() { return targetIlluminance;}
    public void setUser(String name) {
        user = name;
    }
    public void setIlluminance(int calc) {
        illuminance = calc;
    }
}
