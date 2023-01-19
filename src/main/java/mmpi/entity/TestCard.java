package mmpi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestCard {
    @Id
    @GeneratedValue
    private Integer idTestCard;
    String question;
    int ordinalNo;

    public TestCard(String question, int ordinalNo) {
        this.question = question;
        this.ordinalNo = ordinalNo;
    }
    public TestCard(int idTestCard, String question, int ordinalNo) {
        this.idTestCard = idTestCard;
        this.question = question;
        this.ordinalNo = ordinalNo;
    }

    public TestCard() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getOrdinalNo() {
        return ordinalNo;
    }

    public void setOrdinalNo(int ordinalNo) {
        this.ordinalNo = ordinalNo;
    }

    public void setIdTestCard(Integer idTestCard) {
        this.idTestCard = idTestCard;
    }

    public Integer getIdTestCard() {
        return idTestCard;
    }
}
