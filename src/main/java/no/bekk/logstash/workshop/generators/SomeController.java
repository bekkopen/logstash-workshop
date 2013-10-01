package no.bekk.logstash.workshop.generators;

import no.bekk.logstash.workshop.log.LogFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static no.bekk.logstash.workshop.RandomUtils.randomTime;

public class SomeController {

    private final Random random;

    public SomeController() {
        random = new Random();
    }

    public static List<String> pages = asList(
            "/index.html",
            "/payment.html",
            "/error.html",
            "/show.html",
            "/party.html",
            "/"
    );

    public static Logger LOG = LoggerFactory.getLogger(SomeController.class);

    @LogFunction(weight = 1d)
    public void showingPage() {
        LOG.info("Showing page " + randomPage() + ", took " + randomTime(200));
    }

    private String randomPage() {
        int value = (int) (pages.size() * random.nextDouble());
        return pages.get(value);
    }
}
