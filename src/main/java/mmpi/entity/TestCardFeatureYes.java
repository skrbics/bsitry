package mmpi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(TestCardFeatureYesId.class)
public class TestCardFeatureYes {
    @Id
    int idTestCard;
    @Id
    int idFeature;
    public TestCardFeatureYes(int idTestCard, int idFeature) {
        this.idTestCard = idTestCard;
        this.idFeature = idFeature;
    }

    public TestCardFeatureYes() {

    }

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


}
