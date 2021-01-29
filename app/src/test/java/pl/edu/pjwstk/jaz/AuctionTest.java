package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.zad2.request.AuctionRequest;
import pl.edu.pjwstk.jaz.zad2.request.LoginRequest;
import pl.edu.pjwstk.jaz.zad2.request.RegisterRequest;
import pl.edu.pjwstk.jaz.zad2.request.SectionRequest;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AuctionTest {

    @BeforeClass
    public static void register_admin(){
        given()
                .body(new RegisterRequest("admin","admin"))
                .contentType(ContentType.JSON)
                .post("/api/register");
    }



//
//    @Test
////    @WithMockUser(username = "admin", roles = {"user","admin"}, authorities = {"user","admin"})
//    public void creatingNewAuctionAsUserShouldReturn200(){
//        var response = given()
//                .body(new LoginRequest("admin", "admin"))
//                .contentType(ContentType.JSON)
//                .post("/api/login")
//                .thenReturn();
//
//        given()
//                .cookies(response.getCookies())
//                .body(new AuctionRequest("test","test","Pies",200,Arrays.asList("test","test"),Map))
//                .post("/api/newSection")
//                .then()
//                .statusCode(org.springframework.http.HttpStatus.OK.value());
//
//
//    }
}
