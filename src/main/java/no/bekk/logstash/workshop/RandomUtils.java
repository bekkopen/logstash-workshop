package no.bekk.logstash.workshop;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static int randomTime(int mean) {
        double value = ThreadLocalRandom.current().nextGaussian();
        if (value < -1) {
            return 1;
        }
        if (value < 0) {
            return (int) (mean * (-value));
        }
        else {
            return (int) (mean * (value + 1));
        }
    }
}
