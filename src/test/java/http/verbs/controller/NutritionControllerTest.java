package http.verbs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import http.verbs.entity.Food;
import http.verbs.entity.Nutrition;
import http.verbs.entity.ServingSize;
import http.verbs.repository.FoodRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Suleyman Yildirim
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = NutritionController.class)
public class NutritionControllerTest {

    @MockBean
    private FoodRepository foodRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        Food food1 = Food.builder()
                .id(1L)
                .name("egg")
                .nutrition(Nutrition.builder()
                        .calories(200)
                        .carbohydrate(55)
                        .fat(15)
                        .protein(25)
                        .servingSize(ServingSize.GRAM)
                        .build())
                .description(java.util.Optional.of("my favourite"))
                .build();

        Food food2 = Food.builder()
                .id(2L)
                .name("beef")
                .nutrition(Nutrition.builder()
                        .calories(300)
                        .carbohydrate(15)
                        .fat(30)
                        .protein(55)
                        .servingSize(ServingSize.GRAM)
                        .build())
                .build();
//        when(foodRepository.save(food1)).thenReturn(food1);
//        when(foodRepository.save(food2)).thenReturn(food2);
        when(foodRepository.findById(1L)).thenReturn(Optional.ofNullable(food1));
        when(foodRepository.findById(2L)).thenReturn(Optional.ofNullable(food2));
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @BeforeEach
    public void init() throws Exception {
        foodRepository.deleteAll();

    }

    @Test
    public void getFood() throws Exception {
        mockMvc.perform(get("/api/nutrition/get-food/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("egg"))
                .andExpect(jsonPath("$.nutrition.servingSize").value("GRAM"))
                .andExpect(jsonPath("$.nutrition.calories").value(200))
                .andExpect(jsonPath("$.nutrition.fat").value((15)))
                .andExpect(jsonPath("$.nutrition.carbohydrate").value((55)))
                .andExpect(jsonPath("$.nutrition.protein").value((25)))
                .andExpect(jsonPath("$.description").value("my favourite"))
                .andReturn();
    }

    @Test
    public void createFood() throws Exception {
        Food food = Food.builder()
                .id(3L)
                .name("rice")
                .nutrition(Nutrition.builder()
                        .calories(123)
                        .carbohydrate(45)
                        .fat(5)
                        .protein(15)
                        .servingSize(ServingSize.GRAM)
                        .build())
                .build();
        mockMvc.perform(post("/api/nutrition/create-new-food")
                .content(asJsonString(food))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.name").value("rice"))
                .andExpect(jsonPath("$.nutrition.calories").value(123))
                .andExpect(jsonPath("$.nutrition.carbohydrate").value((45)))
                .andExpect(jsonPath("$.nutrition.fat").value((5)))
                .andExpect(jsonPath("$.nutrition.protein").value((15)))
                .andExpect(jsonPath("$.nutrition.servingSize").value("GRAM"))
                .andReturn();
    }

    @Test
    public void updateFood() throws Exception {
        Food food2 = Food.builder()
                .id(2L)
                .name("beef-light")
                .nutrition(Nutrition.builder()
                        .calories(260)
                        .carbohydrate(10)
                        .fat(25)
                        .protein(50)
                        .servingSize(ServingSize.GRAM)
                        .build())
                .build();

        mockMvc.perform(put("/api/nutrition/update-existing-food/{id}", 2)
                .content(asJsonString(food2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("beef-light"))
                .andExpect(jsonPath("$.nutrition.calories").value(260))
                .andExpect(jsonPath("$.nutrition.carbohydrate").value((10)))
                .andExpect(jsonPath("$.nutrition.fat").value((25)))
                .andExpect(jsonPath("$.nutrition.protein").value((50)))
                .andExpect(jsonPath("$.nutrition.servingSize").value("GRAM"))
                .andReturn();
    }

    @Test
    public void updatePartialFood() {
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}