package Simulator;

/**
 * Created by Ryoto on 7/7/2015.
 */
public class Light extends Supply {
    private int luminosity;

    public Light(double x, double y, double z) {
        super(x, y, z);
    }

    public void getInfo() {
        super.getInfo();
        System.out.print("光度:" + String.format("%4dcd ", luminosity));
    }

    public void setLuminosity(int lm) {
        luminosity = lm;
    }
    public int getLuminosity() {
        return luminosity;
    }

}
