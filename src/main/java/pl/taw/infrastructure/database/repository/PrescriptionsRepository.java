package pl.taw.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.taw.controller.dao.PrescriptionDAO;
import pl.taw.controller.exception.NotFoundException;
import pl.taw.infrastructure.database.entity.PrescriptionEntity;
import pl.taw.infrastructure.database.repository.jpa.PrescriptionJapRepository;
import pl.taw.infrastructure.database.repository.mapper.PrescriptionsEntityMapper;

@Repository
@AllArgsConstructor
public class PrescriptionsRepository implements PrescriptionDAO {

    private final PrescriptionJapRepository prescriptionJapRepository;
    private final PrescriptionsEntityMapper prescriptionsEntityMapper;

    @Override
    public PrescriptionEntity findById(Integer prescriptionId) {
        return prescriptionJapRepository.findById(prescriptionId)
                .orElseThrow(() -> new NotFoundException(
                        "Could not find PrescriptionEntity with id: [%s]".formatted(prescriptionId)
                ));
    }
}
