package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.edu.pjwstk.jaz.zad2.AuthenticationService;
import pl.edu.pjwstk.jaz.zad2.LoginRequest;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@IntegrationTest
//@WebMvcTest
public class LoginTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

//    @MockBean
//    private AuthenticationService authenticationService;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loginToAdminAccountShouldRespondIn200() throws Exception {

     // when(authenticationService.login("admin","admin")).thenReturn(true);

        mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\": \"admin\",\"password\" : \"admin\"}"))
                .andExpect(status().isOk());
    }



    @Test
    public void loginToNotExistingUserShouldReturnUnauthorized() throws Exception {

        // when(authenticationService.login("admin","admin")).thenReturn(true);

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user\",\"password\" : \"user\"}"))
                .andExpect(status().isUnauthorized());
    }
}
