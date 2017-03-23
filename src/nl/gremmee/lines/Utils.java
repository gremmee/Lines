package nl.gremmee.lines;

import java.util.Random;

public class Utils {
    private static Random random = new Random();

    public static float getRandomFloat(float aMin, float aMax) {
        return (random.nextFloat() * (aMax - aMin)) + aMin;
    }

    public static final float map(float mappedValue, //
            float inboundMin, //
            float inboundMax, //
            float outboundMin, //
            float outboundMax) {
        return outboundMin + ((outboundMax - outboundMin) * ((mappedValue - inboundMin) / (inboundMax - inboundMin)));
    }
}
