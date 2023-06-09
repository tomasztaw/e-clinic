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

    @GetMapping
    public String reservationPage() {
        return "reservations";
    }
}
