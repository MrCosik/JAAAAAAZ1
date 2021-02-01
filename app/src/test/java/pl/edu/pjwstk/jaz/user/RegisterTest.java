package pl.edu.pjwstk.jaz.user;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;
import pl.edu.pjwstk.jaz.zad2.request.RegisterRequest;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@IntegrationTest
public class RegisterTest {

    @BeforeClass
    public static void register_admin(){
        given()
                .body(new RegisterRequest("admin","admin"))
                .contentType(ContentType.JSON)
                .post("/api/register");
    }


    @Test
    public void register_user_should_respond_with_200(){
        var response = given()
                .body(new RegisterRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/register")
                .thenReturn();

        assertEquals(HttpStatus.SC_OK,response.getStatusCode());
    }

    @Test
    public void register_already_registered_user_should_return_400(){
        var response = given()
                .body(new RegisterRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/register")
                .thenReturn();

        assertEquals(HttpStatus.SC_BAD_REQUEST,response.getStatusCode());
    }

}
