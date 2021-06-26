package io.faascinator.service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.faascinator.service.util.FunctionConfig;
import io.faascinator.service.util.PicocliExtractor;
import picocli.CommandLine;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class MainResource {

    //TODO: fix it
    @SuppressFBWarnings(value = "DM_DEFAULT_ENCODING", justification = "hack")
    @GET
    public String run() throws IOException {
        // TODO: replace by streaming output
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        PrintStream pstream = new PrintStream(ostream);
        System.setOut(pstream);
        System.setErr(pstream);
        final CommandLine cmd = PicocliExtractor.extractCommandLine(FunctionConfig.DEMO_PICOCLI_CHECKSUM);
        cmd.execute();
        return new String(ostream.toByteArray(), StandardCharsets.UTF_8.name());
    }
}
