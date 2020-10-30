package pl.edu.pjwstk.jaz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AverageIT {
    @Test
    public void when_no_parameter_supplied_should_print_a_message() {
        var response = given()
                .when()
                .get("/api/average")
                .then()
                .body("message",equalTo("Please put parameters."));
    }

    @Test
    public void should_remove_trailing_zero_case_1() {
        var response = given()
                .when()
                .param("1,2,3,4")
                .get("/api/average")
                .then()
                .body("message",equalTo("Average equals: 1.5"));
    }
}
