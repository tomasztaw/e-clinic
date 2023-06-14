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
    public List<WorkingHours> getWorkingHours(int doctorId) {
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


    // #################### wyświetlanie od obecnego czasu
    private List<WorkingHours> convertToWorkingHoursList(List<DoctorScheduleDTO> doctorSchedules) {
        List<WorkingHours> workingHoursList = new ArrayList<>();
        DayOfWeek currentDayOfWeek = DayOfWeek.from(LocalDate.now());

        for (DoctorScheduleDTO doctorSchedule : doctorSchedules) {
            WorkingHours workingHours = new WorkingHours();
            workingHours.setDayOfWeek(WorkingHours.DayOfWeek.fromInt(doctorSchedule.getDayOfWeek()));

            // Jeśli to jest dzisiejszy dzień, uwzględnij tylko godziny od chwili obecnej
            if (currentDayOfWeek.getValue() == workingHours.getDayOfWeek().getNumber()) {
                LocalTime currentTime = LocalTime.now();
                if (currentTime.isBefore(doctorSchedule.getStartTimeDs())) {
                    // Jeśli jeszcze nie rozpoczęto pracy, ustaw aktualny czas jako początek pracy
                    workingHours.setStartTime(currentTime);
                } else {
                    workingHours.setStartTime(doctorSchedule.getStartTimeDs());
                }
                workingHours.setEndTime(doctorSchedule.getEndTimeDs());
            } else {
                workingHours.setStartTime(doctorSchedule.getStartTimeDs());
                workingHours.setEndTime(doctorSchedule.getEndTimeDs());
            }

            workingHours.setAppointmentTimes(generateAppointmentTimes(
                    workingHours.getStartTime(),
                    workingHours.getEndTime(),
                    workingHours.getIntervalMinutes()));
            workingHoursList.add(workingHours);
        }

        return workingHoursList;
    }

    // ########################### wyświetlanie kolejnego tygodnia !!!!! nie działa

//    private List<WorkingHours> convertToWorkingHoursList(List<DoctorScheduleDTO> doctorSchedules) {
//        List<WorkingHours> workingHoursList = new ArrayList<>();
////        LocalDate currentDate = LocalDate.now();
////        DayOfWeek currentDayOfWeek = DayOfWeek.from(currentDate);
//
//        DayOfWeek currentDayOfWeek = DayOfWeek.from(LocalDate.now());
//
//
//        for (int i = 0; i < 7; i++) {
//            WorkingHours workingHours = new WorkingHours();
//            DayOfWeek dayOfWeek = currentDayOfWeek.plus(i);
//            workingHours.setDayOfWeek(WorkingHours.DayOfWeek.fromInt(dayOfWeek.getValue()));
//
//            // Sprawdź, czy to jest dzisiejszy dzień
//            if (i == 0) {
//                LocalTime currentTime = LocalTime.now();  // Inicjalizacja currentTime dla dzisiejszego dnia
//                boolean isToday = true;
//
//                // Jeśli jeszcze nie rozpoczęto pracy, ustaw aktualny czas jako początek pracy
//                for (DoctorScheduleDTO doctorSchedule : doctorSchedules) {
//                    if (doctorSchedule.getDayOfWeek() == dayOfWeek.getValue()
//                            && currentTime.isBefore(doctorSchedule.getStartTimeDs())) {
//                        workingHours.setStartTime(currentTime);
//                        workingHours.setEndTime(doctorSchedule.getEndTimeDs());
//                        isToday = false;
//                        break;
//                    }
//                }
//
//                if (isToday) {
//                    workingHours.setStartTime(doctorSchedules.get(0).getStartTimeDs());
//                    workingHours.setEndTime(doctorSchedules.get(0).getEndTimeDs());
//                }
//            } else {
//                // Sprawdź harmonogram lekarza dla danego dnia tygodnia
//                for (DoctorScheduleDTO doctorSchedule : doctorSchedules) {
//                    if (doctorSchedule.getDayOfWeek() == dayOfWeek.getValue()) {
//                        workingHours.setStartTime(doctorSchedule.getStartTimeDs());
//                        workingHours.setEndTime(doctorSchedule.getEndTimeDs());
//                        break;
//                    }
//                }
//            }
//
//            workingHours.setAppointmentTimes(generateAppointmentTimes(
//                    workingHours.getStartTime(),
//                    workingHours.getEndTime(),
//                    workingHours.getIntervalMinutes()));
//            workingHoursList.add(workingHours);
//        }
//
//        return workingHoursList;
//    }




    private List<String> generateAppointmentTimes(LocalTime startTime, LocalTime endTime, int intervalMinutes) {
        List<String> appointmentTimes = new ArrayList<>();

        LocalTime currentTime = startTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        while (currentTime.isBefore(endTime)) {
            if (currentTime.getMinute() == 0 && currentTime != startTime) {
                currentTime = currentTime.plusMinutes(intervalMinutes);
            }
            appointmentTimes.add(currentTime.format(formatter));
            currentTime = currentTime.plusMinutes(intervalMinutes);
        }
        return appointmentTimes;
    }


}