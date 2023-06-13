package pl.taw.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.taw.controller.dao.PrescriptionDAO;
import pl.taw.controller.dto.OpinionDTO;
import pl.taw.controller.dto.PrescriptionDTO;
import pl.taw.controller.dto.PrescriptionsDTO;
import pl.taw.controller.dto.VisitDTO;
import pl.taw.controller.exception.NotFoundException;
import pl.taw.infrastructure.database.entity.PrescriptionEntity;
import pl.taw.infrastructure.database.repository.jpa.PrescriptionJapRepository;
import pl.taw.infrastructure.database.repository.mapper.PrescriptionsEntityMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class PrescriptionsRepository implements PrescriptionDAO {

    private final PrescriptionJapRepository prescriptionJapRepository;
    private final PrescriptionsEntityMapper prescriptionsEntityMapper;

    private final VisitRepository visitRepository;

    @Override
    public PrescriptionEntity findById(Integer prescriptionId) {
        return prescriptionJapRepository.findById(prescriptionId)
                .orElseThrow(() -> new NotFoundException(
                        "Could not find PrescriptionEntity with id: [%s]".formatted(prescriptionId)));
    }

    @Override
    public PrescriptionDTO findDTOById(Integer prescriptionId) {
        return prescriptionJapRepository.findById(prescriptionId)
                .map(prescriptionsEntityMapper::mapFromEntity)
                .orElseThrow(() -> new NotFoundException(
                        "Could not find PrescriptionEntity with id: [%s]".formatted(prescriptionId)));
    }

    @Override
    public List<PrescriptionDTO> findByDoctorId(Integer doctorId) {
        return prescriptionJapRepository.findAll().stream()
                .filter(prescription -> prescription.getDoctorId().equals(doctorId))
                .map(prescriptionsEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<PrescriptionDTO> findByPatientId(Integer patientId) {
        return prescriptionJapRepository.findAll().stream()
                .filter(prescription -> prescription.getPatientId().equals(patientId))
                .map(prescriptionsEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public PrescriptionsDTO findByDate(LocalDate date) {
        return PrescriptionsDTO.of(prescriptionJapRepository.findAll().stream()
                .filter(item -> item.getCreatedAt().toLocalDate().equals(date))
                .map(prescriptionsEntityMapper::mapFromEntity)
                .toList());
    }

    @Override
    public List<PrescriptionDTO> findAll() {
        return prescriptionJapRepository.findAll().stream()
                .map(prescriptionsEntityMapper::mapFromEntity)
                .toList();
    }

    // nie wiem, czy to dobry pomysł, żeby recepta zwracała wizytę
    @Override
    public VisitDTO findVisitForPrescription(Integer visitId) {
        return visitRepository.findDTOById(visitId);
    }

    @Override
    public void savePrescription(PrescriptionEntity newPrescription) {
        prescriptionJapRepository.save(newPrescription);
    }

    @Override
    public void delete(PrescriptionEntity prescriptionForDelete) {
        prescriptionJapRepository.delete(prescriptionForDelete);
    }
}
