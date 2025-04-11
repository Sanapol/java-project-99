package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


@SpringBootTest
@AutoConfigureMockMvc
public class TestApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockWvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    private User user;

    @BeforeEach
    public void startUp() {
        user = Instancio.of(User.class)
                .ignore(Select.field(User::getId))
                .ignore(Select.field(User::getCreatedAt))
                .ignore(Select.field(User::getUpdatedAt))
                .supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
                .create();
        userRepository.save(user);
    }

    @AfterEach
    public void after() {
        userRepository.deleteAll();
    }

    @Test
    public void testWelcome() throws Exception {
        MvcResult result = mockWvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring");
    }

    @Test
    public void testIndex() throws Exception {
        MvcResult result = mockWvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    @Test
    public void testShow() throws Exception {

        MvcResult result = mockWvc.perform(get("/api/users/" + user.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        assertThatJson(body).and(u -> u.node("email").isEqualTo(user.getEmail()),
                u -> u.node("firstName").isEqualTo(user.getFirstName()));
    }

    @Test
    public void testCreate() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("email", "email@list.com");
        data.put("password", "ogo");
        data.put("firstName", "Alex");
        data.put("lastName", "Wang");

        MvcResult result = mockWvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(data)))
                .andExpect(status().isCreated())
                .andReturn();

        User user = userRepository.findByEmail("email@list.com").get();

        String body = result.getResponse().getContentAsString();

        assertThat(user.getLastName()).isNotNull();
        assertThat(user.getCreatedAt()).isNotNull();
        assertThatJson(body).and(u -> u.node("email").isEqualTo(user.getEmail()),
                u -> u.node("id").isEqualTo(user.getId()));
    }

    @Test
    public void testUpdate() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("email", "email@list.com");

        MvcResult result = mockWvc.perform(put("/api/users/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(data)))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        assertThatJson(body).and(u -> u.node("email").isEqualTo("email@list.com"));
    }

    @Test
    public void testDelete() throws Exception {
        ResultActions result = mockWvc.perform(delete("/api/users/" + user.getId()))
                .andExpect(status().isNoContent());

        assertThat(userRepository.existsById(user.getId())).isFalse();
    }
}
