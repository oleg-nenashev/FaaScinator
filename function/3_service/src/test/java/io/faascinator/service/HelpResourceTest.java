package io.faascinator.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class HelpResourceTest {

    @Test
    public void testHelpEndpoint() {
        given()
            .when().get("/help")
            .then()
            .statusCode(200)
            .body(containsString("Demonstrates usage of FaaScinator with embedded CLI app (CheckSum calculator)"));
    }

}
