package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.zad2.request.LoginRequest;
import pl.edu.pjwstk.jaz.zad2.request.RegisterRequest;

import static io.restassured.RestAssured.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@IntegrationTest
public class AccessTest {

    @BeforeClass
    public static void register_user(){
        given()
                .body(new RegisterRequest("user","user"))
                .contentType(ContentType.JSON)
                .post("/api/register");
    }

    @Test
    public void logged_admin_should_be_allowed_to_enter_edit(){
        var response = given()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();

        given()
                .cookies(response.getCookies())
                .get("/api/edit")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void logged_admin_should_be_allowed_to_enter_explore(){
        var response = given()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();

        given()
                .cookies(response.getCookies())
                .get("/api/explore")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void user_should_access_explore(){
        var response = given()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();

        given()
                .cookies(response.getCookies())
                .get("/api/explore")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void user_should_not_access_edit(){
        var response = given()
                .body(new LoginRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();

        given()
                .cookies(response.getCookies())
                .get("/api/edit")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }






}
