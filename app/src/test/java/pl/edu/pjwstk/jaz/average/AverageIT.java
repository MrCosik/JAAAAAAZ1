package pl.edu.pjwstk.jaz.average;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AverageIT {

    @Test
    public void when_no_parameter_supplied_should_print_a_message() {
        // @formatter:off
        given()
                .when()
                .get("/api/average")
                .then()
                .body("message", equalTo("Please put parameters."));
        // @formatter:on
    }

    @Test
    public void should_remove_trailing_zero_case_1() {
        // @formatter:off
        given()
                .when()
                .param("numbers", "2,1")
                .get("/api/average")
                .then()
                .body("message", equalTo("Average equal: 1.5"));
        // @formatter:on
    }

    @Test
    public void should_remove_repeating_numbers(){
        // @formatter:off
        given()
                .when()
                .param("numbers","2,1,1")
                .get("/api/average")
                .then()
                .body("message",equalTo("Average equal: 1.33"));
        // @formatter:on
    }

    @Test
    public void should_cut_zeroes_and_leave_only_4(){
        given()
                .when()
                .param("numbers","4,3,1,7,5")
                .get("/api/average")
                .then()
                .body("message",equalTo("Average equal: 4"));
    }
}
