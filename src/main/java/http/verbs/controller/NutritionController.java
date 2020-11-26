package http.verbs.controller;

import http.verbs.entity.Food;
import http.verbs.repository.NutritionRepository;
import http.verbs.service.INutritionService;
import http.verbs.service.NutritionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * @author Suleyman Yildirim
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nutrition")
public class NutritionController {

    @Autowired
    private final NutritionRepository nutritionRepository;

    @Autowired
    private final NutritionService nutritionService;

    @GetMapping(value = "/get-food/{id}")
    public ResponseEntity<Food> getFood(@PathVariable long id) {
        Optional<Food> optionalFood = nutritionService.getFood(id);
        return ResponseEntity.ok(optionalFood.get());
    }

    @PostMapping(value = "/create-new-food")
    public ResponseEntity<Food> createFood(@RequestBody Food food) throws URISyntaxException {
        Optional<Food> optionalFood = nutritionService.createFood(food);
        Food createdFood = optionalFood.get();
        return ResponseEntity.created(new URI(String.valueOf(createdFood.getId()))).body(createdFood);
    }

    @PutMapping(value = "/update-existing-food/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable long id, @RequestBody Food food) {
        Optional<Food> optionalFood = nutritionService.updateFood(food);
        return ResponseEntity.ok(optionalFood.get());
    }

    @PatchMapping(value = "/update-partial-food/{id}")
    public ResponseEntity<Object> updatePartialFood(@PathVariable long id, Food food) throws Exception {
        Optional<Food> optionalFood = nutritionService.updatePartialFood(food);
        return ResponseEntity.ok(optionalFood);
    }

}
