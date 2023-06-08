package pl.taw.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(OpinionController.OPINIONS)
@AllArgsConstructor
public class OpinionController {

    public static final String OPINIONS = "/opinions";

    @GetMapping
    public String opinionsPage() {
        return "opinions";
    }

}
