package http.verbs.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Suleyman Yildirim
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Nutrition {
    private ServingSize servingSize;
    private int calories;
    private int fat;
    private int carbohydrate;
    private int protein;
}
