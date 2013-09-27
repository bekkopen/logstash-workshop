package no.bekk.logstash.workshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggingApplicationServlet extends HttpServlet {

    public static Logger LOG = LoggerFactory.getLogger(LoggingApplicationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        LOG.info("Got request for " + req.getQueryString());

        writer.append("<html><h1>A page</h1></html>");
    }
}
