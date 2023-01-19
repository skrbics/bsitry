package mmpi.entity;

import java.io.Serializable;

public class TestCardFeatureNoId implements Serializable {
    int idTestCard;
    int idFeature;

    public int getIdTestCard() {
        return idTestCard;
    }

    public void setIdTestCard(int idTestCard) {
        this.idTestCard = idTestCard;
    }

    public int getIdFeature() {
        return idFeature;
    }

    public void setIdFeature(int idFeature) {
        this.idFeature = idFeature;
    }

    public TestCardFeatureNoId(int idTestCard, int idFeature) {
        this.idTestCard = idTestCard;
        this.idFeature = idFeature;
    }
}
