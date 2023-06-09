package pl.taw.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ReservationController.RESERVATIONS)
@AllArgsConstructor
public class ReservationController {

    public static final String RESERVATIONS = "/reservations";
    public static final String KALENDARZ = "/kalendarz";
    public static final String CALENDAR = "/calendar";
    public static final String INDEX = "/index";

    @GetMapping
    public String reservationPage() {
        return "reservations";
    }

    @GetMapping(INDEX)
    public String calendar() {
        return "index";
    }

    @GetMapping(CALENDAR)
    public String index() {
        return "calendar";
    }
}
