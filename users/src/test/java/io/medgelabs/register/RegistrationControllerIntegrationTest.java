package io.medgelabs.register;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import io.medgelabs.RestHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class RegistrationControllerIntegrationTest implements RestHelper {

  @Autowired private MockMvc client;

  @Test
  public void itShould_RejectRequests_WithInvalidUsername() throws Exception {
    var req = new RegistrationController.RegistrationRequest("s", "abcdef12345");
    client
        .perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(asJson(req)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.error").isNotEmpty());
  }

  @Test
  public void itShould_AcceptRequests_WithValidInput() throws Exception {
    var req = new RegistrationController.RegistrationRequest("testUser1234", "abcdef12345");
    client
        .perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(asJson(req)))
        .andDo(print())
        .andExpect(status().isNotFound());
  }
}
