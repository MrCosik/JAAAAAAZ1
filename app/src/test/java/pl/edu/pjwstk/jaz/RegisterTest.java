package pl.edu.pjwstk.jaz;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void registerUserAndLoginShouldRespondWith200() throws Exception {
        mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\": \"user\",\"password\" : \"user\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\",\"password\" : \"user\"}"))
                .andExpect(status().isOk());
    }

}
