package pl.edu.pjwstk.jaz;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.edu.pjwstk.jaz.zad2.LoginRequest;
import pl.edu.pjwstk.jaz.zad2.RegisterRequest;

import static io.restassured.RestAssured.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@IntegrationTest
public class RegisterTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Before
    public void registerUser() throws Exception {
        mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\": \"user\",\"password\" : \"user\"}"))
                .andExpect(status().isOk());
    }

    @BeforeClass
    public static void beforeClass() {
        given()
                .body(new RegisterRequest("user","user"))
                .when()
                .post("/api/register");
    }

    @Test
    public void loginAdmin() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\",\"password\" : \"user\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void admin_login() {
        // @formatter:off
        var response = given()
                .body(new LoginRequest("user","user"))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/admin")
                .then()
                .statusCode(HttpStatus.SC_OK);
        // @formatter:on

    }
}
