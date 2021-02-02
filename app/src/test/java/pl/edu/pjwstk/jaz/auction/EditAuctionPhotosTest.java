package pl.edu.pjwstk.jaz.auction;

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

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class EditAuctionPhotosTest {

    private Response loginUser(String username, String password) {
        return given()
                .body(new LoginRequest(username, password))
                .contentType(ContentType.JSON)
                .when()
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

        given()
                .body(new SectionRequest("Sekcja1", Arrays.asList("Categoria1", "Categoria2")))
                .contentType(ContentType.JSON)
                .cookies(staticLoginUser("admin", "admin").getCookies())
                .post("/api/newSection")
                .thenReturn();


        given().log().all()
                .body("{\n" +
                        "\t\"title\" : \"Test2\",\n" +
                        "\t\"description\" : \"Test2\",\n" +
                        "\t\"category\" : \"Categoria1\",\n" +
                        "\t\"photos\" : [\n" +
                        "\t\t\"testPhoto1\",\n" +
                        "\t\t\"testPhoto2\"\n" +
                        "\t],\n" +
                        "\t\"price\" : 120,\n" +
                        "\t\"parameters\" : {\n" +
                        "\t\t\"testParameter1\" : \"1000\",\n" +
                        "\t\t\"testParameter2\" : \"2500\"\n" +
                        "\t}\n" +
                        "\t\n" +
                        "}")
                .contentType("application/json")
                .cookies(staticLoginUser("user", "user").getCookies())
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void add_photo_to_users_auction_should_return_200(){
        given()
                .body("{\n" +
                        "\"newPhotos\":[\n" +
                        "\t\"Nowe1\"\n" +
                        "]\n" +
                        "}")
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editPhoto/1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void add_photo_to_users_auction_that_doesnt_exists_should_return_400(){
        given()
                .body("{\n" +
                        "\"newPhotos\":[\n" +
                        "\t\"Nowe1\"\n" +
                        "]\n" +
                        "}")
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editPhoto/2")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    public void add_photo_to_users_auction_as_admin_should_return_400(){
        given()
                .body("{\n" +
                        "\"newPhotos\":[\n" +
                        "\t\"Nowe1\"\n" +
                        "]\n" +
                        "}")
                .contentType(ContentType.JSON)
                .cookies(loginUser("admin","admin").getCookies())
                .post("/api/editPhoto/1")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void add_photo_to_users_with_empty_photo_list_should_return_400(){
        given()
                .body("{\n" +
                        "\"newPhotos\":[\n" +
                        "]\n" +
                        "}")
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editPhoto/1")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


}
