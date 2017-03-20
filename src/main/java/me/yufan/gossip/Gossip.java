package me.yufan.gossip;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.exception.GossipInitializeException;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;

import java.net.BindException;

/**
 * Gossip Application
 * <p>
 * A comment engine
 * Using Jersey as JAX-RS implement and Grizzly as backend server
 */
@Slf4j
@NoArgsConstructor
public class Gossip {

    @Parameter(names = {"-h", "--help"}, description = "Show the help description", help = true)
    private boolean help; // NOSONAR

    @Parameter(names = {"-p", "--port"}, required = true, description = "Specific the comment server port")
    private Integer port; // NOSONAR

    @Parameter(names = {"-r", "--root"}, description = "The root resource path for comment server")
    private String rootResourcePath = ""; // NOSONAR

    @Parameter(names = {"-c", "--config"}, required = true, hidden = true, description = "Custom config path")
    private String configPath;

    @Parameter(names = {"-f", "--file"}, description = "Custom config file")
    private String configFile;

    private NettyJaxrsServer server;

    private void start() throws BindException {
        if (server == null) {
            server = new NettyJaxrsServer();
            server.setPort(port);
            server.setRootResourcePath(rootResourcePath);
            server.start();

            Bootstrap bootstrap = new Bootstrap(server.getDeployment(), configPath, configFile);
            bootstrap.start();
        } else {
            throw new GossipInitializeException("The gossip server is already started, check the author's shit logic code");
        }
    }

    public static void main(@NonNull String[] args) {
        Gossip gossip = new Gossip();
        try {
            final JCommander commander = new JCommander(gossip, args);
            if (gossip.help) {
                commander.usage();
                System.exit(0);
            }
            gossip.start();
        } catch (ParameterException e) { // NOSONAR ignore this exception
            log.error(e.getMessage());
            System.exit(0);
        } catch (BindException e) { // NOSONAR
            log.error("", e);
            log.error("The port: [{}] you specified is used, please change to another port", gossip.port);
        } catch (GossipInitializeException e) {
            log.error("", e);
            log.error(e.getMessage() + "\nOr you can copy the error log above and ask the author for help.");
        } catch (Exception e) {
            log.error("", e);
            log.error("Unexpected exception occur, copy the log above and submit a issue on " +
                    "https://github.com/syhily/gossip/issues");
        }
    }
}
