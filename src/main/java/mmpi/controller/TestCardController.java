package mmpi.controller;

import mmpi.entity.TestCard;
import mmpi.service.TestCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class TestCardController {
    @Autowired
    private TestCardService tcs;

    public TestCardController() {

    }

    @PostMapping(path ="/nextTestCard", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TestCard> getNextTestCard(@RequestBody String userId) throws URISyntaxException {
        TestCard testCard = tcs.getNextTestCard(userId);
        return ResponseEntity.created(new URI(String.valueOf(testCard.getIdTestCard()))).body(testCard);
    }

    @PostMapping(path = "/createTestCard", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TestCard> createTestCard(@RequestBody TestCard testCard) throws URISyntaxException {
        tcs.createTestCard(testCard);
        return ResponseEntity.created(new URI(String.valueOf(testCard.getIdTestCard()))).body(testCard);
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello!";
    }
}
