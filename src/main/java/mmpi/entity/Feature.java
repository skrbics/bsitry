package mmpi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feature {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int idFeature;
    String featureMark;

    public Feature(int idFeature, String featureMark) {
        this.idFeature = idFeature;
        this.featureMark = featureMark;
    }



    public Feature() {

    }

    public int getIdFeature() {
        return idFeature;
    }

    public void setIdFeature(int idFeature) {
        this.idFeature = idFeature;
    }

    public String getFeatureMark() {
        return featureMark;
    }

    public void setFeatureMark(String featureMark) {
        this.featureMark = featureMark;
    }


}
