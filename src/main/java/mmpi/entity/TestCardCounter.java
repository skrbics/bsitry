package mmpi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestCardCounter {
    @Id
    @GeneratedValue
    private Integer idTestCardCounter;
    String userId;
    int counter;

    public TestCardCounter(String userId, int counter) {
        this.userId = userId;
        this.counter = counter;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }



    public TestCardCounter() {

    }

    public void setIdTestCardCounter(Integer idTestCardCounter) {
        this.idTestCardCounter = idTestCardCounter;
    }

    public Integer getIdTestCardCounter() {
        return idTestCardCounter;
    }
}
