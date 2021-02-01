package pl.edu.pjwstk.jaz.section;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;
import pl.edu.pjwstk.jaz.zad2.request.LoginRequest;
import pl.edu.pjwstk.jaz.zad2.request.RegisterRequest;
import pl.edu.pjwstk.jaz.zad2.request.SectionRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class CreateSectionTest {

    @BeforeClass
    public static void register_admin_and_user() {
        given()
                .body(new RegisterRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/register");

        given()
                .body(new RegisterRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/register");
    }

    private Response loginUser(String username, String password) {
        return given()
                .body(new LoginRequest(username, password))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
    }

    @Test
    public void creating_new_section_as_admin_should_return_200() {
        List<String> categoryList = Arrays.asList("test1", "test2");

        var response = loginUser("admin", "admin");

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest("Pies", categoryList))
                .contentType(ContentType.JSON)
                .post("/api/newSection")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
    }

    @Test
    public void creating_new_section_as_user_should_return_403() {
        var response = loginUser("user", "user");
        List<String> categoryList = Arrays.asList("test1", "test2");

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest("Pies", categoryList))
                .contentType(ContentType.JSON)
                .post("/api/newSection")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void creating_new_section_without_categories_names_should_return_400() {
        var response = loginUser("admin", "admin");
        List<String> categoryList = Collections.emptyList();

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest("Pies", categoryList))
                .contentType(ContentType.JSON)
                .post("/api/newSection")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void creating_new_section_without_title_should_return_400() {
        var response = loginUser("admin", "admin");
        List<String> categoryList = Arrays.asList("test1", "test2");

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest(null, categoryList))
                .contentType(ContentType.JSON)
                .post("/api/newSection")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void creating_new_section_without_title_and_categories_names_should_return_400() {
        var response = loginUser("admin", "admin");
        List<String> categoryList = Collections.emptyList();

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest(null, categoryList))
                .contentType(ContentType.JSON)
                .post("/api/newSection")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
