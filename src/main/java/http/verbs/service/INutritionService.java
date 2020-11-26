package http.verbs.service;

import http.verbs.entity.Food;
import http.verbs.entity.Nutrition;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Suleyman Yildirim
 */
@Component
public interface INutritionService {
    Optional<Food> getFood(long id);

    Optional<Food> createFood(Food food);

    Optional<Food> updateFood(Food food);

    Optional<Food> updatePartialFood(Food food) throws Exception;

    default Food setFoodFields(Food food, Nutrition nutrition) {
        if (nutrition.getCalories() != 0 && food.getNutrition().getCalories() != nutrition.getCalories()) {
            food.getNutrition().setCalories(nutrition.getCalories());
        }

        if (nutrition.getCarbohydrate() != 0 && food.getNutrition().getCarbohydrate() != nutrition.getCarbohydrate()) {
            food.getNutrition().setCarbohydrate(nutrition.getCarbohydrate());
        }

        if (nutrition.getFat() != 0 && food.getNutrition().getFat() != nutrition.getFat()) {
            food.getNutrition().setFat(nutrition.getFat());
        }

        if (nutrition.getProtein() != 0 && food.getNutrition().getProtein() != nutrition.getProtein()) {
            food.getNutrition().setProtein(nutrition.getProtein());
        }

        if (Optional.ofNullable(nutrition.getServingSize()).isPresent() && food.getNutrition().getServingSize() != nutrition.getServingSize()) {
            food.getNutrition().setServingSize(nutrition.getServingSize());
        }

        return food;
    }
}
