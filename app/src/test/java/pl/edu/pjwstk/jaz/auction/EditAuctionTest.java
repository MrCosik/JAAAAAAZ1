package pl.edu.pjwstk.jaz.auction;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.IntegrationTest;
import pl.edu.pjwstk.jaz.zad2.request.LoginRequest;
import pl.edu.pjwstk.jaz.zad2.request.RegisterRequest;
import pl.edu.pjwstk.jaz.zad2.request.SectionRequest;

import java.util.*;

import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@IntegrationTest
public class EditAuctionTest {

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

    public void addTodMap(Map<String,String> testParameterMap) {
        testParameterMap.put("key1", "val1");
        testParameterMap.put("key2", "val2");
    }

    public void addToList(List<String> photoList){
        photoList.add("photo1");
        photoList.add("photo2");
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
    public void users_1st_auction_when_changing_title_should_return_200(){
        given()
                .body("{\n" +
                        "\t\"title\" : \"Test3\",\n" +
                        "\t\"description\" : \"Test3\",\n" +
                        "\t\"category\" : \"Categoria2\",\n" +
                        "\t\"price\" : 420,\n" +
                        "\t\"parameters\" : {\n" +
                        "\t\t\"testParameter1\" : \"10\",\n" +
                        "\t\t\"zmienione2\" : \"15\"\n" +
                        "\t}\n" +
                        "}")
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editAuction/1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void show_users_auction_with_first_image_should_return_200(){
        var response = given().log().all()
                .cookies(loginUser("user", "user").getCookies())
                .get("api/Auctions")
                .thenReturn();

        Assert.assertTrue(response.getBody().print().contains("\"miniaturePhotoLink\": \"" + "testParameter1" + "\""));

    }
}
