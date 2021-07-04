package io.faascinator.service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.faascinator.quarkus.extension.runtime.AsciidoctorConfig;
import io.faascinator.service.util.PicocliExtractor;
import org.asciidoctor.Options;
import org.asciidoctor.jruby.AsciiDocDirectoryWalker;
import picocli.CommandLine;
import picocli.codegen.docgen.manpage.ManPageGenerator;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Generates documentation, powered by {@link picocli.codegen.docgen.manpage.ManPageGenerator}.
 * @author Oleg Nenashev
 * @since 1.0
 */
@Path("/doc")
public class DocResource extends FaaScinatorResource {

    @Inject
    public AsciidoctorConfig asciidoctorConfig;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("")
    public String generateRootDoc() throws IOException {
        return generateDoc(PicocliExtractor.extractCommandLine(config), false);
    }

    // TODO: Add support for nested sub-commands
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("{subcommand}")
    public String generateSubcommandDoc(@PathParam("subcommand") String subcommand) throws IOException {
        final CommandLine commandLine = PicocliExtractor.extractCommandLine(config);
        final CommandLine subcommandHandler = commandLine.getSubcommands().get(subcommand);
        if (subcommandHandler == null) {
            throw new NotFoundException("Subcommand does not exist: " + subcommand);
        }
        return generateDoc(subcommandHandler, true);
    }

    @SuppressFBWarnings(value = "PATH_TRAVERSAL_IN", justification = "Sanitized by CommandLine")
    public String generateDoc(CommandLine commandLine, boolean isSubcommand) throws IOException {
        final File tmpDir = Files.createTempDirectory("faascinator-docs").toFile();

        ManPageGenerator.generateManPage(tmpDir, null, new boolean[2], false, commandLine.getCommandSpec());

        System.out.println("Converting man pages to HTML...");
        String[] result = asciidoctorConfig.getAsciidoctor().convertDirectory(
                new AsciiDocDirectoryWalker(tmpDir.getAbsolutePath()),
                asciidoctorConfig.getOptionsBuilder().build());
        if (result.length != 0) {
            throw new BadRequestException("Generated empty HTML");
        }

        //TODO: Support nested subcommands
        final File helpFile = isSubcommand
                ? new File (tmpDir, commandLine.getParent().getCommandName() + "-" + commandLine.getCommandName() + ".html")
                : new File (tmpDir, commandLine.getCommandName() + ".html");
        //TODO: Switch to readString() in Java 11
        return new String(Files.readAllBytes(helpFile.toPath()), StandardCharsets.UTF_8);
    }

}
