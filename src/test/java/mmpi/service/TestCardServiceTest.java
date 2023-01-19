package mmpi.service;

import mmpi.entity.TestCard;
import mmpi.repository.TestCardRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestCardServiceTest {

    @Mock
    private TestCardRepo tcr;

    @InjectMocks
    private TestCardService tcs;

    @Test
    public void createTestCard() {
        TestCard tc = new TestCard(15,"Ovo je testna tvrdnja.",15);

        when(tcr.save(any(TestCard.class))).thenReturn(tc);

        TestCard tcCreated = tcs.createTestCard(tc);
        assertNotNull(tcCreated);
        assertEquals(15, tcCreated.getOrdinalNo());
    }

}
