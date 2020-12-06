package http.verbs.service;

import http.verbs.entity.Food;
import http.verbs.entity.Nutrition;
import http.verbs.repository.NutritionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Suleyman Yildirim
 */

@Service
@AllArgsConstructor
public class NutritionService implements INutritionService {

    @Autowired
    private final NutritionRepository nutritionRepository;

    @Override
    public Optional<Food> getFood(long id) {
        Optional<Food> optionalFood = nutritionRepository.findById(id);
        if (!optionalFood.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return optionalFood;
    }

    @Override
    public Optional<Food> createFood(Food food) {
        Optional<Food> optionalFood = nutritionRepository.findById(food.getId());
        if (optionalFood.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT);
        }
        return Optional.of(nutritionRepository.save(food));
    }

    @Override
    public Optional<Food> updateFood(Food food) {
        Optional<Food> optionalFood = nutritionRepository.findById(food.getId());
        if (!optionalFood.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return Optional.of(nutritionRepository.save(food));
    }

    @Override
    public Optional<Food> updatePartialFood(Food food) throws Exception {
        Optional<Food> optionalFood = nutritionRepository.findById(food.getId());
        if (!optionalFood.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        if (Objects.isNull(food.getNutrition())) {
            throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
        }
        Optional.of(food)
                .map(Food::getName)
                .ifPresent(optionalFood.get()::setName);

        Food updatedFood = Optional.of(food)
                .map(Food::getNutrition)
                .map(nutrition -> setFoodFields(optionalFood.get(), nutrition))
                .orElseThrow(Exception::new);

        nutritionRepository.save(updatedFood);
        return Optional.of(nutritionRepository.save(updatedFood));
    }


}
