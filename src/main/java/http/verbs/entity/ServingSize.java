package http.verbs.entity;

import lombok.AllArgsConstructor;

/**
 * @author Suleyman Yildirim
 */
@AllArgsConstructor
public enum ServingSize {

    GRAM("G"), MILLILITRE("ML");
    private String serving;

}
