package pl.taw.controller.dao;

import pl.taw.infrastructure.database.entity.PrescriptionEntity;

public interface PrescriptionDAO {

    PrescriptionEntity findById(Integer prescriptionId);

}
