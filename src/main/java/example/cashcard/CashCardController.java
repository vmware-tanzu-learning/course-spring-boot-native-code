package example.cashcard;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class CashCardController {
    private CashCardRepository cashCardRepository;

    public CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<CashCardDto> cashcards = cashCardRepository.findAll().stream()
                .map(c -> new CashCardDto(c.id(), c.amount(),  new UserDto(c.owner()))).toList();
        model.addAttribute("cashcards", cashcards);
        return "list.html";
    }

    @GetMapping("/banner")
    public String banner(Model model) throws IOException {
        ClassPathResource resource = new ClassPathResource("cashcard-banner.txt");
        String banner = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        model.addAttribute("banner", banner);
        return "banner.html";
    }

}