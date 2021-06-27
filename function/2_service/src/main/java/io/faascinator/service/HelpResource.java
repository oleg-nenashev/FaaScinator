package io.faascinator.service;

/**
 * @author Oleg Nenashev
 * @since TODO
 */
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.faascinator.service.util.PicocliExtractor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Path("/help")
public class HelpResource extends FaaScinatorResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    //TODO: fix it
    @SuppressFBWarnings(value = "DM_DEFAULT_ENCODING", justification = "hack")
    public String printHelp() throws IOException {
        StringBuilder response = new StringBuilder();

        response.append(config.description);
        response.append("\n\n");
        response.append("CLI application main class: ");
        response.append(config.className);
        response.append("\n");
        response.append("CLI JAR file: ");
        response.append(Optional.ofNullable(config.getJarFile()).orElse("<embedded>"));
        response.append("\n\n");

        // PicoCLI help
        ByteArrayOutputStream wr = new ByteArrayOutputStream();
        PicocliExtractor.extractCommandLine(config).usage(new PrintWriter(wr));
        response.append(wr.toString(StandardCharsets.UTF_8.name()));

        return response.toString();
    }
}
