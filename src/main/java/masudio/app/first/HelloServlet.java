package masudio.app.first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet
{
    private static final Logger log = LoggerFactory.getLogger(HelloServlet.class);

    private String greeting="Hello World";

    public HelloServlet(){}

    public HelloServlet(String greeting)
    {
        this.greeting=greeting;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        log.info("got a request...");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>"+greeting+"</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
        log.info("finished a request");
    }
}