package mmpi.repository;

import mmpi.entity.TestCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCardRepo extends JpaRepository<TestCard,Integer> {
    TestCard findByOrdinalNo(int ordinalNo);
}
