package pl.taw.controller.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.taw.controller.dao.PrescriptionDAO;

@Slf4j
@Service
@AllArgsConstructor
public class PrescriptionService {

    private final PrescriptionDAO prescriptionDAO;

}
