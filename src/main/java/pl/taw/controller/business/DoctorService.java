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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
        return doctorDAO.findEntityById(id);
    }

    public void saveDoctor(DoctorEntity doctor) {
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
    public List<WorkingHours> getWorkingHours(Integer doctorId) {
        List<DoctorScheduleDTO> doctorSchedules = doctorScheduleDAO.findScheduleByDoctorId(doctorId);
        return convertToWorkingHoursList(doctorSchedules);
    }

//    private List<WorkingHours> convertToWorkingHoursList(List<DoctorScheduleDTO> doctorSchedules) {
//        List<WorkingHours> workingHoursList = new ArrayList<>();
//        for (DoctorScheduleDTO doctorSchedule : doctorSchedules) {
//            WorkingHours workingHours = new WorkingHours();
//            workingHours.setDayOfWeek(WorkingHours.DayOfWeek.fromInt(doctorSchedule.getDayOfWeek()));
//            workingHours.setStartTime(doctorSchedule.getStartTimeDs());
//            workingHours.setEndTime(doctorSchedule.getEndTimeDs());
//            workingHours.setAppointmentTimes(generateAppointmentTimes(
//                    doctorSchedule.getStartTimeDs(),
//                    doctorSchedule.getEndTimeDs(),
//                    workingHours.getIntervalMinutes()));
//            workingHoursList.add(workingHours);
//        }
//        return workingHoursList;
//    }

    private List<WorkingHours> convertToWorkingHoursList(List<DoctorScheduleDTO> doctorSchedules) {
        List<WorkingHours> workingHoursList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        int currentDayOfWeek = currentDate.getDayOfWeek().getValue();
        int additionalWeeks = 0;

        if (currentDayOfWeek >= DayOfWeek.THURSDAY.getValue()) {
            additionalWeeks = 1;
        }

        LocalDate endDate = currentDate.plusWeeks(additionalWeeks).with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        for (DoctorScheduleDTO doctorSchedule : doctorSchedules) {
            WorkingHours workingHours = new WorkingHours();
            workingHours.setDayOfWeek(WorkingHours.DayOfWeek.fromInt(doctorSchedule.getDayOfWeek()));
            workingHours.setStartTime(doctorSchedule.getStartTimeDs());
            workingHours.setEndTime(doctorSchedule.getEndTimeDs());

            // Jeśli dzisiejsza data odpowiada dniowi z harmonogramu, ustaw bieżący czas jako początek godzin pracy
            if (currentDate.getDayOfWeek().getValue() == doctorSchedule.getDayOfWeek()) {
                workingHours.setStartTime(currentTime);
            }

            workingHours.setAppointmentTimes(generateAppointmentTimes(
                    workingHours.getStartTime(),
                    workingHours.getEndTime(),
                    workingHours.getIntervalMinutes()));

            workingHoursList.add(workingHours);
        }

        // Filtrowanie harmonogramu do końca przyszłego tygodnia, jeśli dzisiaj jest czwartek
        if (currentDayOfWeek == DayOfWeek.THURSDAY.getValue()) {
            workingHoursList.removeIf(workingHours ->
                    workingHours.getDayOfWeek().getNumber() > DayOfWeek.SUNDAY.getValue() ||
                            workingHours.getDayOfWeek().getNumber() < currentDayOfWeek ||
                            (workingHours.getDayOfWeek().getNumber() == currentDayOfWeek &&
                                    workingHours.getStartTime().isBefore(currentTime))
            );
        }

        return workingHoursList;
    }


    private List<String> generateAppointmentTimes(LocalTime startTime, LocalTime endTime, int intervalMinutes) {
        List<String> appointmentTimes = new ArrayList<>();

        LocalTime currentTime = startTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        while (currentTime.isBefore(endTime)) {
            if (currentTime.getMinute() == 0 && currentTime != startTime) {
                // przerwa 10 minut co pełną godzinę
                currentTime = currentTime.plusMinutes(intervalMinutes);
            }
            appointmentTimes.add(currentTime.format(formatter));
            currentTime = currentTime.plusMinutes(intervalMinutes);
        }
        return appointmentTimes;
    }


}