package http.verbs.controller;

import http.verbs.entity.Food;
import http.verbs.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
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

    @PatchMapping(value = "/update-existing-food/{id}")
    public ResponseEntity<Object> updatePartialFood(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        Optional<Food> optionalFood = foodRepository.findById(id);
        if (!optionalFood.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Food food = optionalFood.get();
        fields.forEach(
                (k, v) -> {
                    Field field = ReflectionUtils.findRequiredField(Food.class, k);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, food, v);
                }
        );
        foodRepository.save(food);
        return ResponseEntity.ok().build();

    }

}
