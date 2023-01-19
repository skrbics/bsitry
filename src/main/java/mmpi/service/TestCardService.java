package mmpi.service;

import mmpi.entity.TestCard;
import mmpi.entity.TestCardCounter;
import mmpi.repository.TestCardCounterRepo;
import mmpi.repository.TestCardRepo;
import org.springframework.stereotype.Service;

@Service
public class TestCardService {

    private TestCardCounterRepo tccr;
    private TestCardRepo tcr;
    public TestCardService(TestCardCounterRepo tccr, TestCardRepo tcr) {
        this.tccr = tccr;
        this.tcr = tcr;
        TestCard tc = new TestCard("Ovo je prva tvrdnja.",1);
        tcr.save(tc);
        tc = new TestCard("Ovo je druga tvrdnja.",2);
        tcr.save(tc);
        tc = new TestCard("Ovo je treca tvrdnja.",3);
        tcr.save(tc);
        tc = new TestCard("Ovo je cetvrta tvrdnja.",4);
        tcr.save(tc);
        tc = new TestCard("Ovo je peta tvrdnja.",5);
        tcr.save(tc);
    }

    public TestCard getNextTestCard(String userId){
        TestCardCounter tcc = tccr.findByUserId(userId);
        TestCard tempTC = null;
        if(tcc!=null){
            tcc.setCounter(tcc.getCounter()+1);
        }else{
            tcc = new TestCardCounter(userId,1);
        }
        tccr.save(tcc);
        tempTC = tcr.findByOrdinalNo(tcc.getCounter());
        if(tempTC!=null){
            return tempTC;
        }else{
            return null;
        }
    }

    public TestCard createTestCard(TestCard tC){
        try {
            tcr.save(tC);
            return tC;
        }
        catch(Exception e){
            return null;
        }

    }
}
