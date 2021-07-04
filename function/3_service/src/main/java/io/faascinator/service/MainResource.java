package io.faascinator.service;

import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.faascinator.service.util.PicocliExtractor;
import io.quarkus.runtime.util.StringUtil;
import picocli.CommandLine;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Handles run requests.
 * Format for parameters: <code>/?arg=343fqasdes</code>
 */
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class MainResource extends FaaScinatorResource {

    @GET
    @Path("/")
    public String run(@QueryParam("arg") List<String> args) throws IOException {
        return runSubcommand(null, args);
    }

        //TODO: fix it
    @SuppressFBWarnings(value = "DM_DEFAULT_ENCODING", justification = "hack")
    @GET
    @Path("/run/{subcommand}")
    public String runSubcommand(@PathParam("subcommand") @CheckForNull String subcommand, @QueryParam("arg") List<String> args) throws IOException {
        // TODO: replace by streaming output
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        PrintStream pstream = new PrintStream(ostream);
        System.setOut(pstream);
        System.setErr(pstream);

        final CommandLine rootCommand = PicocliExtractor.extractCommandLine(config);
        final CommandLine cmd;
        if (subcommand != null) {
            cmd = rootCommand.getSubcommands().get(subcommand);
            if (cmd == null) {
                // TODO: properly return errors
                throw new IOException("Unsupported subcommand: " + subcommand + ". " +
                        "Supported commands: " + StringUtil.join(", ", rootCommand.getSubcommands().keySet().iterator()));
            }
        } else {
            cmd = rootCommand;
        }

        cmd.setColorScheme(new CommandLine.Help.ColorScheme.Builder().ansi(CommandLine.Help.Ansi.OFF).build());
        cmd.execute(args.toArray(new String[0]));
        return new String(ostream.toByteArray(), StandardCharsets.UTF_8.name());
    }
}
