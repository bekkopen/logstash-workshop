package no.bekk.logstash.workshop.generators;

import no.bekk.logstash.workshop.log.LogFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static no.bekk.logstash.workshop.random.Emails.emails;

public class SomeService {

    public static Logger LOG = LoggerFactory.getLogger(SomeService.class);

    @LogFunction(weight = 0.2d)
    public void service() {
        LOG.info("Performing calculations.... (TODO: improve this logmessage)");
    }

    @LogFunction(weight = 1d)
    public void user() {
        LOG.info("Getting user information for user: " + emails.next());
    }

    @LogFunction(weight = 0.1d)
    public void userMissingPhoneNumber() {
        LOG.warn("The user is missing phone number");
    }

}
