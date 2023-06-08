package pl.taw.controller.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.dao.DoctorScheduleDAO;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.controller.dto.DoctorScheduleDTO;
import pl.taw.infrastructure.database.entity.DoctorEntity;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorDAO doctorDAO;

    // pobieranie godzi pracy lekarza
    private final DoctorScheduleDAO doctorScheduleDAO;


    public DoctorEntity getDoctorById(int id) {
        return doctorDAO.findById(id);
    }

    public void saveDoctor(DoctorEntity doctor) {
        // Walidacja, logika biznesowa, itp.
        doctorDAO.saveDoctor(doctor);
    }


    @Transactional
    public List<DoctorDTO> findAvailable() {
        List<DoctorDTO> availableDoctors = doctorDAO.findAvailable();
        log.info("Available doctors: [{}]", availableDoctors.size());
        return availableDoctors;
    }

    public List<DoctorDTO> getDoctorsBySpecialization(String specialization) {
        return doctorDAO.findBySpecialization(specialization);
    }

    public List<String> getDoctorSpecializations() {
        return doctorDAO.findAll().stream()
                .map(DoctorEntity::getTitle)
                .distinct()
                .toList();
    }

    // pobieranie godzin pracy lekarza
    public List<WorkingHours> getWorkingHours(int doctorId) {
        List<DoctorScheduleDTO> doctorSchedules = doctorScheduleDAO.findScheduleByDoctorId(doctorId);
        return convertToWorkingHoursList(doctorSchedules);
    }

    private List<WorkingHours> convertToWorkingHoursList(List<DoctorScheduleDTO> doctorSchedules) {
        List<WorkingHours> workingHoursList = new ArrayList<>();
        for (DoctorScheduleDTO doctorSchedule : doctorSchedules) {
            WorkingHours workingHours = new WorkingHours();
            workingHours.setDayOfWeek(WorkingHours.DayOfWeek.fromInt(doctorSchedule.getDayOfWeek()));
            workingHours.setStartTime(doctorSchedule.getStartTimeDs());
            workingHours.setEndTime(doctorSchedule.getEndTimeDs());
            workingHoursList.add(workingHours);
        }
        return workingHoursList;
    }
}