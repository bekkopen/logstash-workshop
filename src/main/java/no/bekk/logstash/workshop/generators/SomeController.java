package no.bekk.logstash.workshop.generators;

import no.bekk.logstash.workshop.RandomCollection;
import no.bekk.logstash.workshop.log.LogFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Arrays.asList;
import static no.bekk.logstash.workshop.RandomUtils.randomTime;
import static no.bekk.logstash.workshop.random.Names.names;

public class SomeController {

    public static Logger LOG = LoggerFactory.getLogger(SomeController.class);

    public static RandomCollection<String> pages = new RandomCollection<String>(asList(
            "/index.html",
            "/payment.html",
            "/error.html",
            "/show.html",
            "/party.html",
            "/"
    ));

    @LogFunction(weight = 1d)
    public void showingPage() {
        LOG.info("Showing page " + pages.next() + ", took " + randomTime(200));
    }

    @LogFunction(weight = 1d)
    public void showingNamesPage() {
        LOG.info("Showing home page for " + names.next() + " which took " + randomTime(300) + " ms to generate");
    }

    @LogFunction(weight = 0.01d)
    public void showErrorPage() {
        LOG.warn("Got exception while processing request", new RuntimeException("Whooa, an error happened", new RuntimeException()));
    }
}