package http.verbs.repository;

import http.verbs.entity.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Suleyman Yildirim
 */
@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {
}
