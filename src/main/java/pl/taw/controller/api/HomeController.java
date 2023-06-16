package pl.taw.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(HomeController.HOME)
@AllArgsConstructor
public class HomeController {

    public static final String HOME = "/";

    @GetMapping(HOME)
    public String homePage(Model model) {
        return "home";
    }

    @GetMapping(HOME + "2")
    public String homePage2(Model model) {
        model.addAttribute("message", "Welcome to the Medical Clinic!");
        return "home2";
    }
}

