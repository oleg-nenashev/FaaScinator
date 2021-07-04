package io.faascinator.service;

import picocli.codegen.docgen.manpage.ManPageGenerator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Generates documentation, powered by {@link picocli.codegen.docgen.manpage.ManPageGenerator}.
 * @author Oleg Nenashev
 * @since 1.0
 */
@Path("/doc")
public class DocResource extends FaaScinatorResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String generateDoc() throws IOException {
        ManPageGenerator manGenerator = new ManPageGenerator();
        manGenerator.

        return "";
    }
}
