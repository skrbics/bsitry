package http.verbs.controller;

import http.verbs.entity.Food;
import http.verbs.entity.Nutrition;
import http.verbs.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Suleyman Yildirim
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nutrition")
public class NutritionController {

    @Autowired
    private final FoodRepository foodRepository;

    @GetMapping(value = "/get-food/{id}")
    public ResponseEntity<Food> getFood(@PathVariable long id) {
        Optional<Food> optionalFood = foodRepository.findById(id);
        if (!optionalFood.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalFood.get());
    }

    @PostMapping(value = "/create-new-food")
    public ResponseEntity<Food> createFood(@RequestBody Food food) throws URISyntaxException {
        Optional<Food> optionalFood = foodRepository.findById(food.getId());
        if (optionalFood.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        foodRepository.save(food);
        return ResponseEntity.created(new URI(String.valueOf(food.getId()))).body(food);
    }

    @PutMapping(value = "/update-existing-food/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable long id, @RequestBody Food food) {
        Optional<Food> optionalFood = foodRepository.findById(food.getId());
        if (!optionalFood.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        foodRepository.save(food);
        return ResponseEntity.ok(food);
    }

    @PatchMapping(value = "/update-partial-food/{id}")
    public ResponseEntity<Object> updatePartialFood(@PathVariable long id, Food food) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(id);
        if (!optionalFood.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (Objects.isNull(food.getNutrition())) {
            return ResponseEntity.noContent().build();
        }
        Optional.of(food)
                .map(Food::getName)
                .ifPresent(optionalFood.get()::setName);

        Food updatedFood = Optional.of(food)
                .map(Food::getNutrition)
                .map(nutrition -> setFoodFields(optionalFood.get(), nutrition))
                .orElseThrow(Exception::new);

        foodRepository.save(updatedFood);
        return ResponseEntity.ok(updatedFood);

    }

    private Food setFoodFields(Food food, Nutrition nutrition) {
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
