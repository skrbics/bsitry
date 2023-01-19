package mmpi.entity;

import java.io.Serializable;

public class TestCardFeatureYesId implements Serializable {
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

    public TestCardFeatureYesId(int idTestCard, int idFeature) {
        this.idTestCard = idTestCard;
        this.idFeature = idFeature;
    }


}
