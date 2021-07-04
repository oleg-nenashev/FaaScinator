package io.faascinator.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MainResourceTest {

    @Test
    public void testRunEndpointWithoutParameter() {
        given()
            .when().get("/")
            .then()
            .statusCode(200)
            .body(containsString("Missing required parameter"));
    }

    // FIXME: NPE in picocli
    /* @Test
    public void testSubcommand() {
        given()
                .when().get("/run/help?arg=1")
                .then()
                .statusCode(200)
                .body(containsString("Missing required parameter"));
    } */

}
