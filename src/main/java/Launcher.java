import masudio.app.first.HelloServlet;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Shared launcher for executing all sample skills within a single servlet container.
 */
public final class Launcher {

    private static final Logger log = LoggerFactory.getLogger(Launcher.class);

    /**
     * port number for the jetty server.
     */
    private static final int PORT = 80;

    /**
     * Security scheme to use.
     */
    private static final String HTTPS_SCHEME = "https";

    /**
     * default constructor.
     */
    private Launcher() {
    }

    /**
     * Main entry point. Starts a Jetty server.
     *
     * @param args
     *            ignored.
     * @throws Exception
     *             if anything goes wrong.
     */
    public static void main(final String[] args) throws Exception {
        // Configure logging to output to the console with default level of INFO
//        BasicConfigurator.configure();

        log.info("Started app...");
        // Configure server and its associated servlets
        Server server = new Server();
//        SslConnectionFactory sslConnectionFactory = new SslConnectionFactory();
//        SslContextFactory sslContextFactory = sslConnectionFactory.getSslContextFactory();
//        sslContextFactory.setKeyStorePath(System.getProperty("javax.net.ssl.keyStore"));
//        sslContextFactory.setKeyStorePassword(System.getProperty("javax.net.ssl.keyStorePassword"));
//        sslContextFactory.setIncludeCipherSuites(Sdk.SUPPORTED_CIPHER_SUITES);

        HttpConfiguration httpConf = new HttpConfiguration();
        httpConf.setSecurePort(PORT);
        httpConf.setSecureScheme(HTTPS_SCHEME);
        httpConf.addCustomizer(new SecureRequestCustomizer());
        HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConf);

        ServerConnector serverConnector =
                new ServerConnector(server, httpConnectionFactory);
        serverConnector.setPort(PORT);

        Connector[] connectors = new Connector[1];
        connectors[0] = serverConnector;
        server.setConnectors(connectors);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloServlet("hello world")), "/");
        context.addServlet(new ServletHolder(new HelloServlet("hello world")), "/hello");
        server.start();
        server.join();
    }
}