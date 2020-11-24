package http.verbs.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Optional;

/**
 * @author Suleyman Yildirim
 */

@Data
@AllArgsConstructor
@Builder
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    public long id;

    private String name;
    private Nutrition nutrition;
    private Optional<String> description;
}
