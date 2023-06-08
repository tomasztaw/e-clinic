package pl.taw.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(VisitController.VISITS)
@AllArgsConstructor
public class VisitController {

    public static final String VISITS = "/visits";

    @GetMapping
    public String visitsPage() {
        return "visits";
    }


}
