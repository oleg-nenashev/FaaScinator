package io.faascinator.service;

/**
 * @author Oleg Nenashev
 * @since TODO
 */
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.faascinator.service.util.FunctionConfig;
import io.faascinator.service.util.PicocliExtractor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Path("/help")
public class HelpResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    //TODO: fix it
    @SuppressFBWarnings(value = "DM_DEFAULT_ENCODING", justification = "hack")
    public String printHelp() throws IOException {
        ByteArrayOutputStream wr = new ByteArrayOutputStream();
        PicocliExtractor.extractCommandLine(FunctionConfig.DEMO_PICOCLI_CHECKSUM).usage(new PrintWriter(wr));
        return wr.toString(StandardCharsets.UTF_8.name());
    }
}
