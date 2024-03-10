package io.faascinator.test.integration;

import io.faascinator.service.MainResource;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class TestMainResource extends MainResource {


}
