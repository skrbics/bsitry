package mmpi.repository;

import mmpi.entity.TestCardCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCardCounterRepo extends JpaRepository<TestCardCounter, Integer> {
    TestCardCounter findByUserId(String s);
}
