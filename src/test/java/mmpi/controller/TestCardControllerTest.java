package mmpi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mmpi.entity.TestCard;
import mmpi.repository.TestCardRepo;
import mmpi.service.TestCardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TestCardController.class)
public class TestCardControllerTest {
    public TestCardControllerTest() {
    }

    @MockBean
    TestCardRepo testCardRepo;
    @MockBean
    TestCardService testCardService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void hello() throws Exception
    {
        mockMvc.perform(get("/hello")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void nextTestCard() throws Exception {
        String userId = "Petar";
        TestCard tc = new TestCard();
        when(testCardService.getNextTestCard(any(String.class))).thenReturn(tc);

        mockMvc.perform(post("/nextTestCard")
                        .content(asJsonString(userId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.idTestCard").isNumber())
//                .andExpect(jsonPath("$.question").isString())
//                .andExpect(jsonPath("$.ordinalNo").isNumber())
                .andReturn();
    }

    @Test
    public void createTestCard() throws Exception {
        TestCard tc = new TestCard(15,"Ovo je testna tvrdnja.",15);

        when(testCardService.createTestCard(any(TestCard.class))).thenReturn(tc);

        mockMvc.perform(post("/createTestCard")
                        .content(asJsonString(tc))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idTestCard").value(15))
                .andExpect(jsonPath("$.question").value("Ovo je testna tvrdnja."))
                .andExpect(jsonPath("$.ordinalNo").value(15))
                .andReturn();
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
