package academy.spring.sample.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleEndpointController {

    @GetMapping("/the-answer")
    long getTheAnswer() {
        return 42;
    }
}
