package devicroft.tripcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by m on 07-Mar-17.
 */

public class Trip {

    double distance;
    double efficiency;
    double price;



    public Trip(double d, double e, double p) {
        super();
        distance = d;
        efficiency = e;
        price = p;
    }

    public double getTripCost() {
        //TODO incorporate locality

        return round(getFuelUsage() * price, 2);
    }

    public double getFuelUsage(){
        return efficiency * (distance / 100);
    }

    //stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
