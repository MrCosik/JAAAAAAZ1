package pl.edu.pjwstk.jaz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@IntegrationTest
public class ExampleTest {

    @Test
    public void should_respond_to_readiness_request() {
        // @formatter:off
        var response = given()
                .when()
                .get("/api/is-ready")
                .thenReturn();
        // @formatter:on
        var statusCode = response.getStatusCode();

        assertThat(statusCode).isEqualTo(200);
    }
}
