package pl.edu.pjwstk.jaz.auction;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
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
public class AuctionTest {




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

    }

    @Test
    public void creating_new_auction_as_admin_should_return_200() {
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();
        addTodMap(testParameterMap);
        addToList(photoList);

        given().log().all()
                .body(new AuctionRequest("test", "test", "Categoria1", 200L, photoList, testParameterMap))
                .contentType("application/json")
                .cookies(loginUser("admin", "admin").getCookies())
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void creating_new_auction_as_user_should_return_200() {
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();
        addTodMap(testParameterMap);
        addToList(photoList);

        given().log().all()
                .body(new AuctionRequest("test", "test", "Categoria1", 200L, photoList, testParameterMap))
                .contentType("application/json")
                .cookies(loginUser("user", "user").getCookies())
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void creating_new_auction_as_user_without_title_should_return_400() {
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();
        addTodMap(testParameterMap);
        addToList(photoList);

        given().log().all()
                .body(new AuctionRequest(null, "test", "Pies", 200L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .cookies(loginUser("user","user").getCookies())
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void creating_new_auction_as_user_without_description_should_return_400() {
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();
        addTodMap(testParameterMap);
        addToList(photoList);

        given().log().all()
                .body(new AuctionRequest("test", null, "Categoria1", 200L, photoList, testParameterMap))
                .contentType("application/json")
                .cookies(loginUser("user", "user").getCookies())
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void creating_new_auction_as_user_without_category_name_should_return_400() {
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();
        addTodMap(testParameterMap);
        addToList(photoList);

        given().log().all()
                .body(new AuctionRequest("test", "test", null, 200L, photoList, testParameterMap))
                .contentType("application/json")
                .cookies(loginUser("user", "user").getCookies())
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void creating_new_auction_as_user_without_price_should_return_400() {
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();
        addTodMap(testParameterMap);
        addToList(photoList);

        given().log().all()
                .body(new AuctionRequest("test", "test", "Categoria1", null, photoList, testParameterMap))
                .contentType("application/json")
                .cookies(loginUser("user", "user").getCookies())
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void creating_new_auction_as_user_with_empty_photo_list_should_return_400() {
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();
        addTodMap(testParameterMap);

        given().log().all()
                .body(new AuctionRequest("test", "test", "Categoria1", 200L, photoList, testParameterMap))
                .contentType("application/json")
                .cookies(loginUser("user", "user").getCookies())
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void creating_new_auction_as_user_with_empty_parameter_map_should_return_400() {
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();
        addToList(photoList);

        given().log().all()
                .body(new AuctionRequest("test", "test", "Categoria1", 200L, photoList, testParameterMap))
                .contentType("application/json")
                .cookies(loginUser("user", "user").getCookies())
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void creating_new_auction_as_user_with_everything_empty_should_return_400() {
        Map<String, String> testParameterMap = new HashMap<>();
        List<String> photoList = new ArrayList<>();


        given().log().all()
                .body(new AuctionRequest(null,null,null,null,photoList, testParameterMap))
                .contentType("application/json")
                .cookies(loginUser("user", "user").getCookies())
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }





}