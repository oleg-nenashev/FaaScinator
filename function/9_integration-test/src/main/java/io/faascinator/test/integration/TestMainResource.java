package io.faascinator.test.integration;

import io.faascinator.service.MainResource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class TestMainResource extends MainResource {


}
