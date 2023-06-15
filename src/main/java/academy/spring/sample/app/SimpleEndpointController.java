package academy.spring.sample.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleEndpointController {

    @GetMapping("/theanswer")
    long getTheAnswer() {
        return 42;
    }
}
