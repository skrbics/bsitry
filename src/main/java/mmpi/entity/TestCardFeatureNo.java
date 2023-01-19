package mmpi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(TestCardFeatureNoId.class)
public class TestCardFeatureNo {
    @Id
    int idTestCard;
    @Id
    int idFeature;

    public TestCardFeatureNo(int idTestCard, int idFeature) {
        this.idTestCard = idTestCard;
        this.idFeature = idFeature;
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



    public TestCardFeatureNo() {

    }
}
