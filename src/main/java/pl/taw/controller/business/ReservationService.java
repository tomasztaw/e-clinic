package pl.taw.controller.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.taw.controller.dao.ReservationDAO;
import pl.taw.controller.dto.ReservationDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationDAO reservationDAO;

    public void createReservation(ReservationDTO reservationDTO) {
        // Tutaj możesz umieścić logikę tworzenia rezerwacji
        // Na przykład, walidacja danych, sprawdzanie dostępności lekarza, itp.
        // Możesz również użyć repozytorium (np. ReservationRepository) do zapisu rezerwacji w bazie danych.
    }

    public void cancelReservation(Integer reservationId) {
        // Tutaj możesz umieścić logikę odwoływania rezerwacji
        // Możesz użyć repozytorium do pobrania rezerwacji na podstawie identyfikatora i zastosować odpowiednie operacje.
    }

    public List<LocalTime> getAvailableTimes(Integer doctorId, LocalDate day) {
        // Tutaj możesz umieścić logikę pobierania dostępnych terminów rezerwacji
        // Na podstawie identyfikatora lekarza i daty, możesz sprawdzić zajętość w danym dniu i zwrócić listę dostępnych godzin.
        // Możesz użyć repozytorium do pobrania rezerwacji dla danego lekarza i dnia, a następnie zastosować odpowiednie operacje.
        // Na przykład, możesz zwrócić listę godzin, które nie są zajęte na podstawie istniejących rezerwacji.
//        return availableTimes;
        return Collections.emptyList();
    }




}
