package hexlet.code.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping(path = "/welcome")
    public final String index() {
        return "Welcome to Spring";
    }
}
