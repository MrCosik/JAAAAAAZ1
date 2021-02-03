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
import pl.edu.pjwstk.jaz.zad2.request.AuctionRequest;
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
    public static void register_admin_and_user_and_create_auction() {

        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        photoList.add("photo1");
        photoList.add("photo2");

        testParameterMap.put("key1", "val1");
        testParameterMap.put("key2", "val2");

        given()
                .body(new RegisterRequest("admin", "admin"))
                .contentType(ContentType.JSON)
                .post("/api/register");

        given()
                .body(new RegisterRequest("user", "user"))
                .contentType(ContentType.JSON)
                .post("/api/register");

        given()
                .body(new RegisterRequest("user2", "user2"))
                .contentType(ContentType.JSON)
                .post("/api/register");

        given()
                .body(new SectionRequest("Sekcja1", Arrays.asList("Categoria1", "Categoria2")))
                .contentType(ContentType.JSON)
                .cookies(staticLoginUser("admin", "admin").getCookies())
                .post("/api/newSection")
                .thenReturn();

        given().log().all()
                .body(new AuctionRequest("Test1", "Test1", "Categoria1", 200L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .cookies(staticLoginUser("user", "user").getCookies())
                .post("api/createAuction");

        given().log().all()
                .body(new AuctionRequest("Test2", "Test2", "Categoria2", 300L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .cookies(staticLoginUser("user2", "user2").getCookies())
                .post("/api/createAuction");



    }


    @Test
    public void users_1st_auction_when_changing_title_should_return_200(){
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        addTodMap(testParameterMap);
        addToList(photoList);

        given()
                .body(new AuctionRequest("test", "test", "Categoria2", 200L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editAuction/1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void user_tries_editing_not_existing_auction_should_return_400(){
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        addTodMap(testParameterMap);
        addToList(photoList);

        given()
                .body(new AuctionRequest("Test3", "Test3", "Categoria2", 420L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editAuction/14")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void user_tries_editing_not_his_auction_should_return_400(){
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        addTodMap(testParameterMap);
        addToList(photoList);

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
                .post("/api/editAuction/2")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void sending_empty_title_should_return_400(){
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        addTodMap(testParameterMap);
        addToList(photoList);

        given()
                .body(new AuctionRequest(null, "Test3", "Categoria2", 420L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editAuction/1")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void sending_empty_description_should_return_400(){
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        addTodMap(testParameterMap);
        addToList(photoList);

        given()
                .body(new AuctionRequest("Test3", null, "Categoria2", 420L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editAuction/1")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void setting_up_category_to_not_existing_should_return_400(){
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        addTodMap(testParameterMap);
        addToList(photoList);

        given()
                .body("{\n" +
                        "\t\"title\" : \"Test3\",\n" +
                        "\t\"description\" : \"Test3\",\n" +
                        "\t\"category\" : \"Krzesla\",\n" +
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
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void setting_up_empty_category_should_return_400(){
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        addTodMap(testParameterMap);
        addToList(photoList);

        given()
                .body(new AuctionRequest("Test3", "Test3", null, 420L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editAuction/1")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void setting_up_empty_price_should_return_400(){
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        addTodMap(testParameterMap);
        addToList(photoList);

        given()
                .body(new AuctionRequest("Test3", "Test3", "Categoria2", null, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editAuction/1")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void setting_empty_parameters_map_should_return_400(){
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        addTodMap(testParameterMap);
        addToList(photoList);

        given()
                .body("{\n" +
                        "\t\"title\" : \"Test3\",\n" +
                        "\t\"description\" : \"Test3\",\n" +
                        "\t\"category\" : \"Krzesla\",\n" +
                        "\t\"price\" : 420,\n" +
                        "\t\"parameters\" : {\n" +
                        "\t}\n" +
                        "}")
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editAuction/1")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void setting_up_new_parameters_should_return_200(){
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();

        testParameterMap.put("Dlugosc","200");
        testParameterMap.put("Szerokosc","400");
        addToList(photoList);

        given()
                .body(new AuctionRequest("Test3", "Test3", "Categoria2", 420L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("/api/editAuction/1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }




    @Test
    public void show_users_auction_with_first_image_should_return_200(){
//        var response =
                given().log().all()
                .cookies(loginUser("user", "user").getCookies())
                .get("/api/Auctions")
//                .thenReturn();
                .then()
                .statusCode(HttpStatus.OK.value());
//
//        Assert.assertTrue(response.getBody().print().contains("miniaturePhotoLink") &&
//                response.getBody().print().contains("testPhoto1"));

    }
}
