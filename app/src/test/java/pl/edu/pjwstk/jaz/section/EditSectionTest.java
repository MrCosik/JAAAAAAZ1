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
public class EditSectionTest {

    private Response loginUser(String username, String password) {
        return given()
                .body(new LoginRequest(username, password))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
    }

    private static Response staticLoginUser(String username, String password) {
        return given()
                .body(new LoginRequest(username, password))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
    }

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

    @BeforeClass
    public static void create_section_to_edit(){
        var response = staticLoginUser("admin", "admin");
        List<String> categoryList = Arrays.asList("test1", "test2");

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest("Pies", categoryList))
                .contentType(ContentType.JSON)
                .post("/api/newSection")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());
    }

    @Test
    public void edit_section_under_1st_id_as_admin_should_return_200(){
        var response = loginUser("admin", "admin");
        List<String> categoryList = Arrays.asList("test3", "test4");

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest("Kot", categoryList))
                .contentType(ContentType.JSON)
                .post("/api/editSection/1")
                .then()
                .statusCode(org.springframework.http.HttpStatus.OK.value());

    }

    @Test
    public void edit_section_under_1st_id_as_user_should_return_400(){
        var response = loginUser("user", "user");
        List<String> categoryList = Arrays.asList("test3", "test4");

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest("Kot", categoryList))
                .contentType(ContentType.JSON)
                .post("/api/editSection/1")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void edit_section_under_2nd_id_as_admin_should_return_400(){
        var response = loginUser("admin", "admin");
        List<String> categoryList = Arrays.asList("test3", "test4");

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest("Kot", categoryList))
                .contentType(ContentType.JSON)
                .post("/api/editSection/2")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void edit_section_under_1nd_id_as_admin_without_title_should_return_400(){
        var response = loginUser("admin", "admin");
        List<String> categoryList = Arrays.asList("test3", "test4");

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest(null, categoryList))
                .contentType(ContentType.JSON)
                .post("/api/editSection/2")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void edit_section_under_1nd_id_as_admin_with_empty_title_should_return_400(){
        var response = loginUser("admin", "admin");
        List<String> categoryList = Arrays.asList("test3", "test4");

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest("", categoryList))
                .contentType(ContentType.JSON)
                .post("/api/editSection/2")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    public void edit_section_under_1nd_id_as_admin_with_empty_list_should_return_400(){
        var response = loginUser("admin", "admin");
        List<String> categoryList = Collections.emptyList();

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest("Dom", categoryList))
                .contentType(ContentType.JSON)
                .post("/api/editSection/2")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void edit_section_under_1nd_id_as_admin_with_all_parameters_empty_should_return_400(){
        var response = loginUser("admin", "admin");
        List<String> categoryList = Collections.emptyList();

        given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest(null, categoryList))
                .contentType(ContentType.JSON)
                .post("/api/editSection/2")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }



}
