package pl.taw.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PrescriptionController.PRESCRIPTIONS)
@AllArgsConstructor
public class PrescriptionController {

    public static final String PRESCRIPTIONS = "/prescriptions";

    @GetMapping
    public String prescriptionPage() {
        return "prescriptions";
    }
}
