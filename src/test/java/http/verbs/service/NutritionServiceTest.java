package http.verbs.service;

import http.verbs.entity.Food;
import http.verbs.entity.Nutrition;
import http.verbs.entity.ServingSize;
import http.verbs.repository.NutritionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Suleyman Yildirim
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class NutritionServiceTest {

    @Mock
    private NutritionRepository nutritionRepository;

    @InjectMocks
    private NutritionService nutritionService;

    @Test
    public void getFood() {
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

        when(nutritionRepository.findById(food.getId())).thenReturn(Optional.of(food));
        Optional<Food> foodRetrieved = nutritionService.getFood(food.getId());
        assertTrue(foodRetrieved.isPresent());
        assertEquals(3L, foodRetrieved.get().getId());
    }

    @Test
    public void createFood() {
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

        when(nutritionRepository.save(any(Food.class))).thenReturn(food);

        Optional<Food> foodCreated = nutritionService.createFood(food);
        assertTrue(foodCreated.isPresent());
        assertEquals(3L, foodCreated.get().getId());
    }

    @Test
    public void updateFood() {
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

        Food foodUpdate = Food.builder()
                .id(3L)
                .name("rice-basmati")
                .nutrition(Nutrition.builder()
                        .calories(123)
                        .carbohydrate(45)
                        .fat(5)
                        .protein(15)
                        .servingSize(ServingSize.GRAM)
                        .build())
                .build();

        when(nutritionRepository.findById(food.getId())).thenReturn(Optional.of(food));
//        when(nutritionRepository.save(food)).thenReturn(food);
        when(nutritionRepository.findById(foodUpdate.getId())).thenReturn(Optional.of(foodUpdate));
        when(nutritionRepository.save(foodUpdate)).thenReturn(foodUpdate);

        Optional<Food> foodUpdated = nutritionService.updateFood(foodUpdate);
        assertTrue(foodUpdated.isPresent());
        assertEquals(3L, foodUpdated.get().getId());
        assertEquals("rice-basmati", foodUpdated.get().getName());

    }

    @Test
    public void updatePartialFood() throws Exception {
        Food food = Food.builder()
                .id(3L)
                .name("rice")
                .nutrition(Nutrition.builder()
                        .calories(123)
                        .carbohydrate(45)
                        .fat(15)
                        .protein(15)
                        .servingSize(ServingSize.GRAM)
                        .build())
                .build();

        Food foodUpdate = Food.builder()
                .id(3L)
                .nutrition(Nutrition.builder()
                        .calories(200)
                        .fat(10)
                        .servingSize(ServingSize.GRAM)
                        .build())
                .name("rice-basmati")
                .build();

        when(nutritionRepository.findById(food.getId())).thenReturn(Optional.of(food));
        when(nutritionRepository.save(food)).thenReturn(food);

        Optional<Food> foodUpdated = nutritionService.updatePartialFood(foodUpdate);
        assertTrue(foodUpdated.isPresent());
        assertEquals(3L, foodUpdated.get().getId());
        assertEquals("rice-basmati", foodUpdated.get().getName()); // updated
        assertEquals(200, foodUpdated.get().getNutrition().getCalories()); // updated
        assertEquals(10, foodUpdated.get().getNutrition().getFat()); // updated
        assertEquals(45, foodUpdated.get().getNutrition().getCarbohydrate()); // same
        assertEquals(15, foodUpdated.get().getNutrition().getProtein()); // same
        assertEquals(ServingSize.GRAM, foodUpdated.get().getNutrition().getServingSize()); // same
    }
}