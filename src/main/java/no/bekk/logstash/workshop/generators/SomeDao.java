package no.bekk.logstash.workshop.generators;

import no.bekk.logstash.workshop.RandomUtils;
import no.bekk.logstash.workshop.log.LogFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class SomeDao {

    public static Logger LOG = LoggerFactory.getLogger(SomeDao.class);

    @LogFunction(weight = 1d)
    public void accessingDatabase() {
        LOG.info("Finding user in db, took " + RandomUtils.randomTime(40) + " ms");
    }

    @LogFunction(weight = 0.05)
    public void criticalDatabaseFailure() {
        LOG.error("CRITICAL FAILURE, database unavailable", new IllegalStateException("Lost connection to database",
                new SQLException("Connection reset", "-1", ThreadLocalRandom.current().nextInt(1, 9999))));
    }
}
