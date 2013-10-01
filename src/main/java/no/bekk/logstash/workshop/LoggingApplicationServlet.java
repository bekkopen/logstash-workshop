package no.bekk.logstash.workshop;

import no.bekk.logstash.workshop.log.LogGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoggingApplicationServlet extends HttpServlet {

    public static final int NUMBER_OF_THREADS = 20;
    public static Logger LOG = LoggerFactory.getLogger(LoggingApplicationServlet.class);
    private ExecutorService executor;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        LOG.info("Got request for " + req.getQueryString());

        writer.append("<html><h1>A page</h1></html>");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            executor.execute(new LogGenerator());
        }
    }

    @Override
    public void destroy() {
        executor.shutdownNow();
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                LOG.warn("Failed to wait for logging threads to shutdown, stopping application anyway");
            }
        }
        catch (InterruptedException e) {
            LOG.warn("Got interrupted while waiting for logging threads to stop", e);
        }
        super.destroy();
    }

}
