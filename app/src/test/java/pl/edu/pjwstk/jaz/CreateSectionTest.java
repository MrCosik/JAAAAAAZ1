package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.zad2.request.SectionRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class CreateSectionTest {

    private final List<String> categories = Arrays.asList("Chair","Dolphin");

//    @Test
//    public void createNewSectionWithCategoriesShouldReturm200(){
//        given()
//                .when()
//                .post()
//                .body(new SectionRequest("Ogrod", Collections.singletonList("A")))
//                .contentType(ContentType.JSON)
//                .
//    }
}
