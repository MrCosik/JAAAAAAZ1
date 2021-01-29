package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.zad2.request.LoginRequest;
import pl.edu.pjwstk.jaz.zad2.request.RegisterRequest;
import pl.edu.pjwstk.jaz.zad2.request.SectionRequest;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
//@ContextConfiguration
public class SectionTest {

    @BeforeClass
    public static void register_admin(){
        given()
                .body(new RegisterRequest("admin","admin"))
                .contentType(ContentType.JSON)
                .post("/api/register");
    }




    @Test
//    @WithMockUser(username = "admin", roles = {"user","admin"}, authorities = {"user","admin"})
    public void creatingNewSectionAsAdminShouldReturn200(){
        var response = given()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();

        given()
                .cookies(response.getCookies())
                .body(new SectionRequest("Pies", Arrays.asList("1","2")))
                .post("/api/newSection")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());


    }

    @Test
    public void creatingNewSectionAsUserShouldReturn403(){
        var response = given()
                .body(new LoginRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();

        given()
                .cookies(response.getCookies())
                .body(new SectionRequest("Pies", Arrays.asList("1","2")))
                .post("/api/newSection")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }
}
