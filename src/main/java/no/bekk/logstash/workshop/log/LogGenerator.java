package no.bekk.logstash.workshop.log;

import no.bekk.logstash.workshop.generators.SomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Arrays.asList;

public class LogGenerator implements Runnable {

    public static Logger LOG = LoggerFactory.getLogger(LogGenerator.class);
    private final ArrayList<Logging> logging;

    public LogGenerator() {
        List<?> logClasses = asList(
                new SomeController()
        );

        this.logging = findLogFunctions(logClasses);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            int index = ThreadLocalRandom.current().nextInt(0, logging.size());
            Logging logFunction = logging.get(index);
            try {
                logFunction.method.invoke(logFunction.logClass);
            }
            catch (Exception e) {
                LOG.warn("Got error while trying to run a log function", e);
            }
            try {
                Thread.sleep((long) (ThreadLocalRandom.current().nextDouble() * 500));
            }
            catch (InterruptedException e) {
                break;
            }
        }
        LOG.info("Interrupted logging thread, shutting down");
    }

    private ArrayList<Logging> findLogFunctions(List<?> logClasses) {
        ArrayList<Logging> logging = new ArrayList<Logging>();
        for (Object logClass : logClasses) {
            for (Method method : logClass.getClass().getMethods()) {
                if (method.isAnnotationPresent(LogFunction.class)) {
                    if (method.getParameterTypes().length != 0) {
                        throw new IllegalStateException("Method " + method.getName() + " on class " + logClass.getClass().getSimpleName() + " must not have parameters");
                    }
                    LOG.info("Adding " + method.getName());
                    logging.add(new Logging(method.getAnnotation(LogFunction.class), method, logClass));
                }
            }
        }
        return logging;
    }

    private class Logging {
        public final LogFunction logFunction;
        public final Method method;
        public final Object logClass;

        private Logging(LogFunction logFunction, Method method, Object logClass) {
            this.logFunction = logFunction;
            this.method = method;
            this.logClass = logClass;
        }
    }
}
