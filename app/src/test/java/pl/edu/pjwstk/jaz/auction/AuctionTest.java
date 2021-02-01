package pl.edu.pjwstk.jaz.auction;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
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
public class AuctionTest {


    private final Map<String, String> testParameterMap = new HashMap<>();
    private final List<String> photoList = new ArrayList<>();

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

    public void addToListAndMap(){
        testParameterMap.put("key1", "val1");
        testParameterMap.put("key2","val2");
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
                .body(new RegisterRequest("user","user"))
                .contentType(ContentType.JSON)
                .post("/api/register");
    }



    @BeforeClass
    public static void create_section_to_edit(){

    }




    @Test
    public void creating_new_auction_as_user_should_return_200() {
        var response = loginUser("user","user");
        addToListAndMap();

        given().log().all()
                .cookies(response.getCookies())
                .body(new AuctionRequest("test", "test", "Pies", 200L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void creating_new_auction_as_user_with_unexisting_category_name_should_return_400() {
        var response = loginUser("admin","admin");
        addToListAndMap();


        List<String> categoryList = Arrays.asList("test1", "test2");

        var section = given().log().all()
                .cookies(response.getCookies())
                .body(new SectionRequest("Pies", categoryList))
                .contentType(ContentType.JSON)
                .post("/api/newSection");

        given().log().all()
                .cookies(section.getCookies())
                .body(new AuctionRequest("test", "test", "Kot", 200L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void creating_new_auction_as_user_without_title_should_return_400() {
        var response = loginUser("user","user");
        addToListAndMap();

        given().log().all()
                .cookies(response.getCookies())
                .body(new AuctionRequest(null, "test", "Pies", 200L, photoList, testParameterMap))
                .contentType(ContentType.JSON)
                .post("api/createAuction")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}